package com.holis.san01.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "ped_item")
public class PedItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private Integer nrPedido;

    private int nrSequencia;

    @Column(nullable = false)
    private String codItem;

    private String descricao;

    @Column(nullable = false)
    private String unimed;

    private LocalDate dtEntrega;
    private BigDecimal vlPretab;
    private BigDecimal vlPreuni;
    private BigDecimal qtPedida;
    private BigDecimal qtAtendida;
    private BigDecimal qtPendente;
    private String observacao;
    private LocalDate dtAprovacao;
    private int nrOrdem;
    private LocalDate dtEntorig;
    private LocalDate dtCanseq;
    private LocalDate dtReativ;
    private LocalDate dtSuspensao;
    private BigDecimal qtDevolvida;
    private LocalDate dtDevolucao;
    private String descDevol;
    private BigDecimal vlPreori;
    private BigDecimal perDesItem;
    private BigDecimal perMinfat;
    private int codSitItem;
    private BigDecimal aliquotaIpi;
    private boolean indIcmRet;
    private BigDecimal vlMercAbe;
    private BigDecimal vlLiqIt;
    private BigDecimal vlLiqAbe;
    private BigDecimal vlTotitem;
    private String nrTabpre;
    private BigDecimal perDesIcms;
    private String natOperacao;
    private int tipoAtend;
    private int indComponen;
    private BigDecimal qtFatenf;
    private String codRefer;
    private BigDecimal qtTransfer;
    private String descTxt;
    private BigDecimal qtAlocada;
    private int codSitPre;
    private LocalDate dtMaxFat;
    private int tpAlocLote;
    private LocalDate dtMinFat;
    private int espPed;
    private BigDecimal percFornec;
    private BigDecimal qtLoteMin;
    private String codEntrega;
    private int cdOrigem;
    private int nrOrdProdu;
    private int nrPrograma;
    private BigDecimal qtTransMp;
    private int codIsencao;
    private BigDecimal qtOrdens;
    private BigDecimal vlDesconto;
    private int codCondEsp;
    private int codSitCom;
    private String motivoAltSitQuota;
    private boolean logUsaTabelaDesconto;
    private BigDecimal valPctDescontoTabPreco;
    private String desPctDescontoInform;
    private BigDecimal valDescontoInform;
    private BigDecimal valPctDescontoTotal;
    private boolean logConcedeBonifQtd;
    private BigDecimal valPctBonif;
    private BigDecimal valPctDescontoPeriodo;
    private BigDecimal valPctDescontoPrazo;
    private BigDecimal valDescontoTotal;
    private BigDecimal valDescontoBonif;
    private int numSequenciaBonif;
    private BigDecimal qtUnFat;
    private BigDecimal vlPreoriUnFat;
    private int codMotCancCot;
    private String userAprovCot;
    private String descLibPreco;
    private String enderecoText;
    private String desUnMedida;
    private BigDecimal decFtconvUnest;
    private String codClassFis;
    private String codUn;
    private boolean logCancSaldo;
    private BigDecimal valAliqIss;
    private BigDecimal valIpi;
    private BigDecimal valFrete;
    private BigDecimal valSeguro;
    private BigDecimal valEmbal;
    private String codUnidNegoc;
    private BigDecimal valTaxPtlidad;
    private BigDecimal valTaxApldo;
    private BigDecimal valPrecoSuger;
    private String codOrdCompra;
    private int tpPreco;
    private String situacao;
    private int status;
}
