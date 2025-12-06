package com.holis.san01.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode e @RequiredArgsConstructor
@Entity
@Table(name = "parametro")
public class Parametro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer empresa;

    @Column(nullable = false)
    private String codParam;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private int sequencia;

    private String cpoTexto;

    @Column(nullable = false)
    private int cpoInteiro;

    @Column(nullable = false)
    private BigDecimal cpoDecimal;

    private LocalDate cpoData;
}
