package com.holis.san01.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode e @RequiredArgsConstructor
@Entity
@Table(name = "ncm")
public class Ncm {
    @Id
    @Column(nullable = false)
    private String codNcm;

    @Column(nullable = false)
    private String descricao;

    private BigDecimal aliquota;

    private int status;
}
