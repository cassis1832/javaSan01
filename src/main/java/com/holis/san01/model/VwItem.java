package com.holis.san01.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "vw_item")
public class VwItem {
    @Id
    private String codItem;

    private String descricao;

    private String codTipoItem;

    private String unimed;

    private BigDecimal precoVenda;

    private Date dtPrecoVenda;

    private BigDecimal precoCompra;

    private Date dtPrecoCompra;

    private String codFamilia;

    private Boolean indItemFat;

    private String comprFabric;

    private Integer resCompra;

    private Integer resFabric;

    private BigDecimal aliquotaIpi;

    private Integer codOrigem;

    private Integer codTribIcm;

    private Integer codTribIpi;

    private Boolean fraciona;

    private Integer codTribIss;

    private BigDecimal aliquotaIss;

    private Boolean indIpiDife;

    private BigDecimal loteMulven;

    private BigDecimal pesoLiquido;

    private BigDecimal pesoBruto;

    private BigDecimal comprim;

    private BigDecimal largura;

    private BigDecimal altura;

    private BigDecimal volume;

    private BigDecimal perRestIcms;

    private String nivRestIcms;

    private BigDecimal quantPacote;

    private String codLocaliz;

    private Date dtLiberac;

    private Date dtObsol;

    private String usuarioObsol;

    private Integer tipoFinEntId;

    private Integer tipoFinSaiId;

    private String narrativa;

    private BigDecimal loteEcon;

    private BigDecimal loteMinVda;

    private BigDecimal loteMinCpa;

    private String codNcm;

    private String origem;

    private Integer tempoRessup;

    private String gtin;

    private Integer prazoEntrega;

    private String situacao;

    private Date dtCriacao;

    private String archive;

    private String descricaoTipoItem;

    private String tpProd;
}
