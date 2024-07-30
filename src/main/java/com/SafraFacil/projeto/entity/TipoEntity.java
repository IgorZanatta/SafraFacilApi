package com.SafraFacil.projeto.entity;

import com.SafraFacil.projeto.dto.TipoDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Entity
@Table(name = "SF_TIPO")
@Getter
@Setter
@NoArgsConstructor
public class TipoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tipo_atividade;

    @Column(nullable = false)
    private String gasto;

    @Column(nullable = false)
    private String lucro;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data;

    @Column(nullable = false)
    private String observacao;

    @Lob
    private byte[] anexos;

    @ManyToOne
    @JoinColumn(name = "setor_id", nullable = false)
    private SetorEntity setor;

    public TipoEntity(TipoDTO tipo) {
        BeanUtils.copyProperties(tipo, this);
        if (tipo.getSetor() != null) {
            this.setor = new SetorEntity(tipo.getSetor());
        }
    }
}
