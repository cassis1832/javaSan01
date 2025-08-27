package com.holis.san01.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ItemDTO {
    @NotBlank(message = "Código do item é obrigatório")
    @Size(max = 15, message = "O código deve ter entre 1 e 15 caracteres")
    private String codItem;

    @NotBlank(message = "Descrição do item é obrigatória")
    @Size(max = 60, message = "A descrição deve ter entre 1 e 60 caracteres")
    private String descricao;

    @NotBlank(message = "Unidade de medida é obrigatória")
    private String unimed;

    @NotBlank(message = "Tipo fiscal de item é obrigatório")
    private String codTipoItem;

    private BigDecimal precoVenda;

    private LocalDate dtPrecoVenda;

    private BigDecimal precoCompra;

    private LocalDate dtPrecoCompra;

    private String codFamilia;

    private Boolean indItemFat;

    private BigDecimal precoUltEnt;

    private LocalDate dtUltEnt;

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

    private LocalDate dtLiberac;

    private LocalDate dtObsol;

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

    private LocalDate dtCriacao;

    @NotNull
    private boolean archive;
}
