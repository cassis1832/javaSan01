package com.holis.san01.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "item")
public class Item {
    @Id
    @Column(nullable = false)
    private String codItem;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String tipoItem;

    @Column(nullable = false)
    private String codTipoItem;

    @Column(nullable = false)
    private String unimed;

    private BigDecimal precoBase;

    private Date dtBase;

    private BigDecimal custoRep;

    private Date dtUltRep;

    private String codFamilia;

    private Boolean indItemFat;

    private BigDecimal precoUltEnt;

    private Date dtUltEnt;

    private String comprFabric;

    private Integer resCompra;

    private Integer resFabric;

    private String classFiscal;

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

    private String ncm;

    private String origem;

    private Integer tempoRessup;

    private String gtin;

    private Integer prazoEntrega;

    private String situacao;

    private Date dtCriacao;

    @Column(nullable = false)
    @Pattern(regexp = "[SN]", message = "O campo 'archive' deve ser 'S' ou 'N'.")
    private String archive;
}
