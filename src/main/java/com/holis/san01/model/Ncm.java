package com.holis.san01.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode e @RequiredArgsConstructor
@Entity
@Table(name = "ncm")
public class Ncm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer empresa;

    @Column(nullable = false)
    private String codNcm;

    @Column(nullable = false)
    private String descricao;

    private BigDecimal aliquota;

    private int status;
}
