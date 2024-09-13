package com.SafraFacil.projeto.controller;

import com.SafraFacil.projeto.dto.SetorDTO;
import com.SafraFacil.projeto.dto.TipoDTO;
import com.SafraFacil.projeto.service.SetorService;
import com.SafraFacil.projeto.service.TipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Date;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping(value = "/tipo")
@CrossOrigin
public class TipoController {

    @Autowired
    private TipoService tipoService;

    @Autowired
    private SetorService setorService;

    @GetMapping
    public List<TipoDTO> listarTodos(){
        return tipoService.listarTodos();
    }

    @GetMapping("/setor/{setorId}")
    public ResponseEntity<List<TipoDTO>> listarPorSetor(@PathVariable Long setorId) {
        return ResponseEntity.ok(tipoService.listarPorSetor(setorId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoDTO> buscarPorId(@PathVariable Long id) {
        TipoDTO tipoDTO = tipoService.buscarPorId(id);
        return ResponseEntity.ok(tipoDTO);
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void inserir(@RequestParam("tipo_atividade") String tipoAtividade,
                        @RequestParam("gasto") String gasto,
                        @RequestParam("lucro") String lucro,
                        @RequestParam("data") String data,
                        @RequestParam("observacao") String observacao,
                        @RequestParam("setor") Long setorId,
                        @RequestPart("anexos") MultipartFile anexos) throws Exception {

        SetorDTO setorDTO = setorService.buscarPorId(setorId);

        TipoDTO tipoDTO = new TipoDTO();
        tipoDTO.setTipo_atividade(tipoAtividade);
        tipoDTO.setGasto(gasto);
        tipoDTO.setLucro(lucro);
        try {
            tipoDTO.setData(convertStringToDate(data));  // Convertendo a string para Date
        } catch (ParseException e) {
            throw new RuntimeException("Formato de data inválido", e);
        }
        tipoDTO.setObservacao(observacao);
        tipoDTO.setAnexos(anexos.getBytes());
        tipoDTO.setSetor(setorDTO);

        tipoService.inserir(tipoDTO);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<TipoDTO> alterar(@PathVariable Long id,
                                           @RequestParam("tipo_atividade") String tipoAtividade,
                                           @RequestParam("gasto") String gasto,
                                           @RequestParam("lucro") String lucro,
                                           @RequestParam("data") String data,
                                           @RequestParam("observacao") String observacao,
                                           @RequestParam("setor") Long setorId,
                                           @RequestPart(value = "anexos", required = false) MultipartFile anexos) throws IOException {
        SetorDTO setorDTO = setorService.buscarPorId(setorId);

        TipoDTO tipoDTO = new TipoDTO();
        tipoDTO.setId(id);
        tipoDTO.setTipo_atividade(tipoAtividade);
        tipoDTO.setGasto(gasto);
        tipoDTO.setLucro(lucro);
        try {
            tipoDTO.setData(convertStringToDate(data));  // Convertendo a string para Date
        } catch (ParseException e) {
            throw new RuntimeException("Formato de data inválido", e);
        }
        tipoDTO.setObservacao(observacao);

        if (anexos != null) {
            tipoDTO.setAnexos(anexos.getBytes());
        }

        tipoDTO.setSetor(setorDTO);

        TipoDTO tipoAtualizado = tipoService.alterar(tipoDTO);
        return ResponseEntity.ok(tipoAtualizado);
    }

    @GetMapping("/data/{data}") // Novo endpoint para listar por data
    public ResponseEntity<List<TipoDTO>> listarPorData(@PathVariable String data) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(data);
        return ResponseEntity.ok(tipoService.listarPorData(date));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id){
        tipoService.excluir(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<TipoDTO> listarPorUsuario(@PathVariable Long usuarioId) {
        return tipoService.listarPorUsuario(usuarioId);
    }

    private Date convertStringToDate(String dateString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(dateString);
    }
}
