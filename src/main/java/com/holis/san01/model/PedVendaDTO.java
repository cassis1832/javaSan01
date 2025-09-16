package com.holis.san01.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PedVendaDTO {

        private Integer nrPedido;

        private Integer codFilial;

        @Positive(message = "O código deve ser maior que zero")
        @NotNull(message = "Cliente é obrigatório")
        private Integer codEntd;

        @Size(max = 30, message = "A descrição deve ter até 30 caracteres")
        private String descricao;

        private boolean tpPedido;
        private Integer nrContrato;
        private String nrPedcli;
        private BigDecimal percDesconto;
        private BigDecimal vlDesconto;
        private BigDecimal vlFrete;
        private BigDecimal vlSeguro;
        private BigDecimal vlEmbal;
        private BigDecimal vlTotPed;
        private BigDecimal vlTotItens;
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
        private boolean indAprov;
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
        private BigDecimal percDesco2;
        private String condRedespa;
        private String cidadeCif;
        private Integer codPortador;
        private Integer modalidade;
        private Integer codMensagem;
        private String condEspec;
        private Integer codDesMerc;
        private String nomeTransp;
        private Integer tpPreco;
        private boolean indFatPar;
        private BigDecimal vlLiqPed;
        private BigDecimal vlLiqAbe;
        private boolean indAntecip;
        private BigDecimal distancia;
        private BigDecimal vlMerAbe;
        private String descSuspend;
        private String descBloqCr;
        private String descForcCr;
        private String nomeTrRed;
        private Integer tipCobDesp;
        private Integer codSitPre;
        private BigDecimal perDesIcms;
        private BigDecimal vlCredLib;
        private boolean incDescTxt;
        private LocalDate dtBaseFt;
        private boolean indEntCompleta;
        private boolean completo;
        private BigDecimal vlDescontoTotal;
        private BigDecimal descValorPed;
        private Integer tipoFinId;
        private String condPag;
        private LocalDate dtSituacao;
        private String situacao;
        private LocalDate dtCriacao;
        private int status;
    }