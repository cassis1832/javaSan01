package com.holis.san01.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "ped_venda")
public class PedVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long nrPedido;

    @Column(nullable = false)
    private Long codEntd;

    @Column(nullable = false)
    private Long codFilial;

    private String descricao;

    private Integer tpPedido;

    private Integer nrCotacao;

    private Integer nrContrato;

    private String nrPedcli;

    private Double percDesconto;

    private Double vlDesconto;

    private Double vlFrete;

    private Double vlSeguro;

    private Double vlEmbal;

    private Double vlTotPed;

    private Double vlTotItens;

    private String codCondPag;

    private Integer parcelas;

    private String prazoEntrega;

    private String natOperacao;

    private String contato;

    private String telefone;

    private String email;

    private String observacao;

    private LocalDate dtEmissao;

    private LocalDate dtEntrega;

    private Byte indAprov;

    private LocalDate dtAprovacao;

    private LocalDate dtValCot;

    private String prazoValCot;

    private LocalDate dtMinfat;

    private LocalDate dtLimFat;

    private LocalDate dtDevolucao;

    private LocalDate dtSuspensao;

    private Integer codPriori;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private String localidade;

    private String estado;

    private String cep;

    private String pais;

    private String cgc;

    private String insEstadual;

    private Double percDesco2;

    private String condRedespa;

    private String cidadeCif;

    private Integer codPortador;

    private Integer modalidade;

    private Integer codMensagem;

    private String condEspec;

    private Integer codDesMerc;

    private String nomeTransp;

    private Integer tpPreco;

    private Byte indFatPar;

    private Double vlLiqPed;

    private Double vlLiqAbe;

    private Byte indAntecip;

    private Double distancia;

    private Double vlMerAbe;

    private String descSuspend;

    private String descBloqCr;

    private String descForcCr;

    private String nomeTrRed;

    private Integer tipCobDesp;

    private Integer codSitPre;

    private Double perDesIcms;

    private Double vlCredLib;

    private Byte incDescTxt;

    private LocalDate dtBaseFt;

    private Byte indEntCompleta;

    private Byte completo;

    private Double vlDescontoTotal;

    private Double descValorPed;

    private Integer tipoFinId;

    private String condPag;

    private String situacao;

    private LocalDate dtSituacao;

    private String archive;
}