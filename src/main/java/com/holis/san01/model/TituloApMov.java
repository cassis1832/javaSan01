package com.holis.san01.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode e @RequiredArgsConstructor
@Entity
@Table(name = "titulo_ap_mov")
public class TituloApMov {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer tituloApId;

    @Column(length = 10)
    private String tpMovto;

    @Column(length = 10)
    private String codRefer;

    @Column(length = 40)
    private String formaPagto;

    @Column(length = 3)
    private String codEspDocto;

    @Column(length = 10)
    private String numDocto;

    private Integer parcela;

    private LocalDate dtMovto;

    @Column(precision = 11, scale = 2)
    private BigDecimal vlMovto = BigDecimal.ZERO;

    private LocalDate dtCrMovto;

    private LocalDate dtPrevPag;

    private LocalDate dtLiquidac;

    @Column(precision = 11, scale = 2)
    private BigDecimal vlTitulo;

    @Column(precision = 11, scale = 2)
    private BigDecimal vlDesconto = BigDecimal.ZERO;

    @Column(precision = 12, scale = 2)
    private BigDecimal vlAbat = BigDecimal.ZERO;

    @Column(precision = 12, scale = 2)
    private BigDecimal vlMulta = BigDecimal.ZERO;

    @Column(precision = 12, scale = 2)
    private BigDecimal vlJuros = BigDecimal.ZERO;

    @Column(precision = 11, scale = 2)
    private BigDecimal vlDespesBcia = BigDecimal.ZERO;

    @Column(precision = 12, scale = 2)
    private BigDecimal vlCm = BigDecimal.ZERO;

    @Column(precision = 12, scale = 2)
    private BigDecimal vlImptoOperacFinanc = BigDecimal.ZERO;

    @Column(precision = 11, scale = 2)
    private BigDecimal vlMotivMovto = BigDecimal.ZERO;

    @Column(precision = 13, scale = 2)
    private BigDecimal vlDespesFinanc = BigDecimal.ZERO;

    private boolean logMovtoEstordo = false;

    private boolean logNdebitoGerad = false;

    private boolean logAntecipGerad = false;

    private boolean logLiquidacContraAntecip = false;

    private boolean logMovtoComisEstordo = false;

    private boolean logMovtoEnvdoBco = false;

    @Column(length = 29)
    private String indTransAr;

    @Column(precision = 11, scale = 2)
    private BigDecimal vlSdoTit = BigDecimal.ZERO;

    @Column(precision = 12, scale = 2)
    private BigDecimal vlMultaTit = BigDecimal.ZERO;

    @Column(precision = 12, scale = 2)
    private BigDecimal vlJurosCalc = BigDecimal.ZERO;

    private Boolean logRecuperPerda = false;

    private Boolean logRetencImptoLiq = false;

    @Column(precision = 11, scale = 2)
    private BigDecimal vlRetencPis = BigDecimal.ZERO;

    @Column(precision = 11, scale = 2)
    private BigDecimal vlRetencCofins = BigDecimal.ZERO;

    @Column(precision = 11, scale = 2)
    private BigDecimal vlRetencCsll = BigDecimal.ZERO;

    @Column(precision = 11, scale = 2)
    private BigDecimal vlIvaRetid = BigDecimal.ZERO;

    @Column(length = 200)
    private String observacao;

    private Integer antecTituloId;

    @Column(nullable = false)
    private Instant dtCriacao;
}