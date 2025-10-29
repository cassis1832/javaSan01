package com.holis.san01.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "vw_titulo_ap")
public class VwTituloAp {

    @Id
    private Integer id;

    private Integer codFilial;

    private Integer codEntd;

    private String nome;

    private Integer numDoc;

    private String codEspDoc;

    private Integer docId;

    private Integer parcela;

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

    private Boolean logEmisBoleto;

    private Boolean logAvisoDbEmitid;

    private Boolean logReciboEmitid;

    private Boolean logNpromisEmitid;

    private Boolean logDbAutom;

    private String observacao;

    private String situacao;

    private int status;
}
