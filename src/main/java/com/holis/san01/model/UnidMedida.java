package com.holis.san01.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode e @RequiredArgsConstructor
@Entity
@Table(name = "unid_medida")
public class UnidMedida {

    @Id
    @Column(nullable = false)
    private String codUniMed;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Integer sequencia;

    @Column(nullable = false)
    private Boolean fraciona;

    @Column(nullable = false)
    private Integer status;
}
