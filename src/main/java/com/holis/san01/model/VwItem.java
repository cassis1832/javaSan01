package com.holis.san01.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "vw_item")
public class VwItem {

    @Id
    private String codItem;
    private String descricao;
    private String codTipoItem;
    private String codUniMed;
    private String codFamilia;
    private BigDecimal preco_venda;
    private LocalDate dt_preco_venda;
    private String situacao;
    private String descricaoTipoItem;
    private String tpProd;
    private int status;
}
