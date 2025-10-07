package com.holis.san01.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "vw_ped_venda")
public class VwPedVenda {

    @Id
    private Integer nrPedido;

    private Integer codEntd;

    private Integer codFilial;

    private String descricao;

    private Boolean tpPedido;

    private BigDecimal percDesconto;

    private BigDecimal vlDesconto;

    private BigDecimal vlFrete;

    private BigDecimal vlSeguro;

    private BigDecimal vlEmbal;

    private BigDecimal vlTotPed;

    private BigDecimal vlTotItens;

    private String codCondPag;

    private Integer parcelas;

    private String contato;

    private String telefone;

    private String email;

    private String observacao;

    private LocalDate dtEmissao;

    private LocalDate dtEntrega;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private String localidade;

    private String estado;

    private String cep;

    private String pais;

    private String cgc;

    private BigDecimal vlLiqPed;

    private BigDecimal vlLiqAbe;

    private BigDecimal vlDescontoTotal;

    private BigDecimal descValorPed;

    private String condPag;

    private String situacao;

    private LocalDate dtSituacao;

    private Integer status;

    private String nome;
}