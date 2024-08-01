package com.SafraFacil.projeto.service;

import com.SafraFacil.projeto.dto.UsuarioDTO;
import com.SafraFacil.projeto.entity.UsuarioEntity;
import com.SafraFacil.projeto.entity.UsuarioVerificadorEntity;
import com.SafraFacil.projeto.entity.enums.TipoSituacaoUsuario;
import com.SafraFacil.projeto.repository.UsuarioRepository;
import com.SafraFacil.projeto.repository.UsuarioVerificadorRepository;
import com.SafraFacil.projeto.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioVerificadorRepository usuarioVerificadorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtUtils jwtUtils;

    @Value("${app.frontend.url}")
    private String frontendUrl;

    private final Random random = new Random();

    public List<UsuarioDTO> listarTodos (){
        List<UsuarioEntity> usuarios =  usuarioRepository.findAll();
        return usuarios.stream().map(UsuarioDTO::new).toList();
    }

    public UsuarioDTO buscarPorId(Long id) {
        return new UsuarioDTO(usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado")));
    }

    public void inserir(UsuarioDTO usuario) {
        UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
        usuarioEntity.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuarioEntity);
    }
    public UsuarioDTO alterar(UsuarioDTO usuario) {
        // Busca o usuário existente no banco de dados
        UsuarioEntity usuarioExistente = usuarioRepository.findById(usuario.getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Atualiza apenas os campos que foram modificados
        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setLogin(usuario.getLogin());
        usuarioExistente.setTelefone(usuario.getTelefone());
        usuarioExistente.setSituacao(usuario.getSituacao());

        // Atualiza a senha apenas se ela for fornecida
        if (usuario.getSenha() != null && !usuario.getSenha().isEmpty()) {
            usuarioExistente.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }

        // Salva as alterações
        usuarioRepository.save(usuarioExistente);
        return new UsuarioDTO(usuarioExistente);
    }

    public void inserirNovoUsuario(UsuarioDTO usuario) {
        UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
        usuarioEntity.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuarioEntity.setSituacao(TipoSituacaoUsuario.PENDENTE);
        usuarioEntity.setId(null);
        usuarioRepository.save(usuarioEntity);

        UsuarioVerificadorEntity verificador = new UsuarioVerificadorEntity();
        verificador.setUsuario(usuarioEntity);
        verificador.setUuid(UUID.randomUUID());
        verificador.setDataExpiracao(Instant.now().plusMillis(900000));
        usuarioVerificadorRepository.save(verificador);

        String verificationLink = "https://safrafacilapi.onrender.com/auth/verificarCadastro/" + verificador.getUuid();
        String message = "<html><body>" +
                "<p>Clique no botão para verificar seu cadastro:</p>" +
                "<a href='" + verificationLink + "' style='display: inline-block; padding: 10px 20px; font-size: 16px; color: #fff; background-color: #007bff; text-decoration: none; border-radius: 5px; cursor: pointer;'>Verificar Cadastro</a>" +
                "</body></html>";

        emailService.enviarEmailHtml(usuario.getLogin(), "Novo usuário cadastrado", message);
    }


    public String verificarCadastro(String uuid) {

        UsuarioVerificadorEntity usuarioVerificacao = usuarioVerificadorRepository.findByUuid(UUID.fromString(uuid)).get();

        if(usuarioVerificacao != null) {
            if(usuarioVerificacao.getDataExpiracao().compareTo(Instant.now()) >= 0) {

                UsuarioEntity u = usuarioVerificacao.getUsuario();
                u.setSituacao(TipoSituacaoUsuario.ATIVO);

                usuarioRepository.save(u);

                return "Usuário Verificado";
            }else {
                usuarioVerificadorRepository.delete(usuarioVerificacao);
                return "Tempo de verificação expirado";
            }
        }else {
            return "Usuario não verificado";
        }

    }

    public void excluir(Long id) {
        UsuarioEntity usuario = usuarioRepository.findById(id).get();
        usuarioRepository.delete(usuario);
    }

    public UsuarioDTO alterarCampos(UsuarioDTO usuario) {
        UsuarioEntity usuarioExistente = usuarioRepository.findById(usuario.getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setLogin(usuario.getLogin());
        usuarioExistente.setTelefone(usuario.getTelefone());
        usuarioExistente.setSituacao(usuario.getSituacao());

        usuarioRepository.save(usuarioExistente);
        return new UsuarioDTO(usuarioExistente);
    }

    public UsuarioDTO alterarSenha(Long id, String novaSenha) {
        UsuarioEntity usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        System.out.println("Senha antes de codificar: " + novaSenha); // Log para verificar a senha recebida
        usuarioExistente.setSenha(passwordEncoder.encode(novaSenha));
        usuarioRepository.save(usuarioExistente);
        return new UsuarioDTO(usuarioExistente);
    }


    public void solicitarRedefinicaoSenha(String login) {
        Optional<UsuarioEntity> usuarioOpt = usuarioRepository.findByLogin(login);
        if (usuarioOpt.isPresent()) {
            UsuarioEntity usuario = usuarioOpt.get();
            int codigoVerificacao = random.nextInt(90000) + 10000; // Gera um número aleatório de 5 dígitos
            usuario.setCodigoVerificacao(codigoVerificacao);
            usuario.setDataExpiracaoCodigo(Instant.now().plusSeconds(900)); // 900 segundos = 15 minutos
            usuarioRepository.save(usuario);

            String message = "<html><body>Código para redefinição de senha: " + codigoVerificacao + "</body></html>";
            emailService.enviarEmailHtml(usuario.getLogin(), "Redefinição de Senha", message);
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
    }

    public boolean verificarCodigo(String login, int codigo) {
        Optional<UsuarioEntity> usuarioOpt = usuarioRepository.findByLogin(login);
        if (usuarioOpt.isPresent()) {
            UsuarioEntity usuario = usuarioOpt.get();
            if (usuario.getCodigoVerificacao() == codigo && usuario.getDataExpiracaoCodigo().compareTo(Instant.now()) >= 0) {
                return true;
            } else {
                return false;
            }
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
    }

    public void redefinirSenha(String login, String novaSenha) {
        Optional<UsuarioEntity> usuarioOpt = usuarioRepository.findByLogin(login);
        if (usuarioOpt.isPresent()) {
            UsuarioEntity usuario = usuarioOpt.get();
            usuario.setSenha(passwordEncoder.encode(novaSenha));
            usuario.setCodigoVerificacao(null); // Limpa o código de verificação
            usuario.setDataExpiracaoCodigo(null); // Limpa a data de expiração do código
            usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
    }
}