package com.holis.san01.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode e @RequiredArgsConstructor
@Entity
@Table(name = "titulo_ap")
public class TituloAp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer codFilial;

    @Column(nullable = false)
    private Integer codEntd;

    @Column(nullable = false, length = 3)
    private String codEspDoc;

    private Integer numDoc;

    private Integer parcela = 1;

    private Integer docId;

    @Column(length = 60)
    private String descricao;

    @Column(nullable = false)
    private LocalDate dtVencto;

    @Column(nullable = false)
    private LocalDate dtPrevPag;

    @Column(precision = 11, scale = 2)
    private BigDecimal vlTitulo = BigDecimal.ZERO;

    @Column(precision = 12, scale = 2)
    private BigDecimal vlJuros = BigDecimal.ZERO;

    @Column(precision = 11, scale = 2)
    private BigDecimal vlMulta = BigDecimal.ZERO;

    @Column(precision = 11, scale = 2)
    private BigDecimal vlPago = BigDecimal.ZERO;

    @Column(precision = 11, scale = 2)
    private BigDecimal vlSaldo = BigDecimal.ZERO;

    private LocalDate dtLiquidac;

    @Column(precision = 11, scale = 2)
    private BigDecimal vlOriginal = BigDecimal.ZERO;

    private LocalDate dtVenctoOrigin;

    private Integer tipoFinId;

    private Boolean logDuplEmitid = false;

    private Boolean logEmisBoleto = false;

    private Boolean logAvisoDbEmitid = false;

    private Boolean logReciboEmitid = false;

    private Boolean logNpromisEmitid = false;

    private Boolean logDbAutom = false;

    private Integer parcelas = 1;

    @Column(length = 200)
    private String obsPagto;

    @Column(precision = 11, scale = 2)
    private BigDecimal vlBaixaAntec = BigDecimal.ZERO;

    @Column(length = 2000)
    private String observacao;

    @Column(nullable = false)
    private Instant dtCriacao;

    private String usrCriacao;

    private Instant dtAlteracao;

    private String usrAlteracao;

    private int status;

    private LocalDate dtDeleted;

    private String situacao;
}