package com.holis.san01.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "vw_titulo_ap")
public class VwTituloAp {
    @Id
    private Long id;

    private Integer codFilial;

    private Integer codEntd;

    private String nome;

    private Integer tituloId;

    private Integer numDoc;

    private String codEspDoc;

    private Long docId;

    private boolean parcela;

    private Integer parcelas;

    private String descricao;

    private LocalDate dtVencto;

    private LocalDate dtPrevPag;

    private BigDecimal vlTitulo;

    private BigDecimal vlJuros;

    private BigDecimal vlMulta;

    private BigDecimal vlPago;

    private BigDecimal vlSaldo;

    private LocalDate dtLiquidac;

    private BigDecimal vlOriginal;

    private LocalDate dtVenctoOrigin;

    private Integer tipoFinId;

    private boolean logEmisBoleto;

    private boolean logAvisoDbEmitid;

    private boolean logReciboEmitid;

    private boolean logNpromisEmitid;

    private boolean logDbAutom;

    private String observacao;

    private String situacao;

    private boolean archive;
}
