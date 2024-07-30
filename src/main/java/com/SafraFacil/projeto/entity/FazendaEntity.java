package com.SafraFacil.projeto.entity;

import com.SafraFacil.projeto.dto.FazendaDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "SF_FAZENDA")
@Getter
@Setter
@NoArgsConstructor
public class FazendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String tamanho;

    @ManyToOne
    @JoinColumn(name = "safra_id")
    private SafraEntity safra;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    public FazendaEntity(FazendaDTO fazenda) {
        BeanUtils.copyProperties(fazenda, this);
        if (fazenda.getSafra() != null) {
            this.safra = new SafraEntity(fazenda.getSafra());
        }
        if (fazenda.getUsuario() != null) {
            this.usuario = new UsuarioEntity(fazenda.getUsuario());
        }
    }
}
