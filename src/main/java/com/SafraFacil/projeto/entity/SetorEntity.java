package com.SafraFacil.projeto.entity;

import com.SafraFacil.projeto.dto.SetorDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.Set;

@Entity
@Table(name = "SF_SETOR")
@Getter
@Setter
@NoArgsConstructor
public class SetorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String tipo_setor;

    @Column(nullable = false)
    private String tamanho;

    @ManyToOne
    @JoinColumn(name = "fazenda_id", nullable = false)
    private FazendaEntity fazenda;

    // Adicionando relacionamento com TipoEntity
    @OneToMany(mappedBy = "setor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TipoEntity> tipos;

    public SetorEntity(SetorDTO setor) {
        BeanUtils.copyProperties(setor, this);
        if (setor != null && setor.getFazenda() != null) {
            this.fazenda = new FazendaEntity(setor.getFazenda());
        }
    }
}
