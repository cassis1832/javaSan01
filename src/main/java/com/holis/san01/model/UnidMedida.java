package com.holis.san01.model;

import jakarta.persistence.*;
import lombok.Data;

@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode e @RequiredArgsConstructor
@Entity
@Table(name = "unid_medida")
public class UnidMedida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer empresa;

    @Column(nullable = false)
    private String codUniMed;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Integer sequencia;

    @Column(nullable = false)
    private String fraciona;

    @Column(nullable = false)
    private int status;
}
