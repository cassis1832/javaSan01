package com.holis.san01.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "vw_ped_item")
public class VwPedItem {

    @Id
    private Integer id;
    private Integer nrPedido;
    private Boolean tpPedido;
    private Integer codEntd;
    private String nome;
    private Integer nrSequencia;
    private String codItem;
    private String descricao;
    private String codUniMed;
    private LocalDate dtEntrega;
    private BigDecimal vlPreuni;
    private BigDecimal qtPedida;
    private BigDecimal qtAtendida;
    private BigDecimal qtPendente;
    private BigDecimal vlTotitem;
    private Integer status;
    private Integer statusPedVenda;

}
