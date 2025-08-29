package com.holis.san01.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
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

    @Column(nullable = false)
    private Integer tpTitulo;

    @Column(nullable = false, length = 3)
    private String codEspDoc;

    @Column(length = 10)
    private String numDoc;

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

    private boolean logDuplEmitid = false;

    private boolean logEmisBoleto = false;

    private boolean logAvisoDbEmitid = false;

    private boolean logReciboEmitid = false;

    private boolean logNpromisEmitid = false;

    private boolean logDbAutom = false;

    private Integer parcelas = 1;

    @Column(length = 200)
    private String obsPagto;

    @Column(precision = 11, scale = 2)
    private BigDecimal vlBaixaAntec = BigDecimal.ZERO;

    @Column(length = 2000)
    private String observacao;

    @Column(nullable = false)
    private LocalDate dtCriacao;

    private String usrCriacao;

    private LocalDate dtAlteracao;

    private String usrAlteracao;

    private boolean archive = false;

    private boolean deleted = false;

    private LocalDate dtDeleted;

    private String situacao;
}