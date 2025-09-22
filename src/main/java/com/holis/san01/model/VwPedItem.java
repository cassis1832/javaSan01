package com.holis.san01.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "vw_ped_venda_item")
public class VwPedItem {

    @Id
    private BigInteger id;
    private Integer nrPedido;
    private int codEntd;
    private String nome;
    private Integer nrSequencia;
    private String codItem;
    private String descricao;
    private String unimed;
    private LocalDate dtEntrega;
    private BigDecimal vlPreuni;
    private BigDecimal qtPedida;
    private BigDecimal qtAtendida;
    private BigDecimal qtPendente;
    private BigDecimal vlTotitem;
    private String situacao;
    private int status;
    private String descricaoItem;
    private String nomeCliente;
    private int statusPedVenda;
    private String situacaoPedVenda;
}
