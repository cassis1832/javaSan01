package com.holis.san01.model;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ItemDTO {
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    @Pattern(regexp = "[SN]", message = "O campo 'archive' deve ser 'S' ou 'N'.")
    private String archive;
    private String situacao;
    private Date dtCriacao;
    @NotNull(message = "Código do item é obrigatório")
    private String codItem;
    @NotNull(message = "Descrição do item é obrigatória")
    private String descricao;
    @NotNull(message = "Unidade de medida é obrigatória")
    private String unimed;
    @NotNull(message = "Tipo de item é obrigatório")
    private String tipoItem;
    @NotNull(message = "Tipo fiscal de item é obrigatório")
    private String codTipoItem;
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
}
