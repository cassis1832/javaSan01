package com.holis.san01.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ItemDTO {

    @NotBlank(message = "Código do item é obrigatório")
    @Size(max = 15, message = "O código deve ter até 15 caracteres")
    private String codItem;

    @NotBlank(message = "Descrição do item é obrigatória")
    @Size(max = 60, message = "A descrição deve ter até 60 caracteres")
    private String descricao;

    @NotBlank(message = "Tipo fiscal de item é obrigatório")
    @Size(max = 2, message = "O tipo deve ter até 2 caracteres")
    private String codTipoItem;

    @NotBlank(message = "Unidade de medida é obrigatória")
    @Size(max = 6, message = "A unidade de medida deve ter até 6 caracteres")
    private String codUniMed;

    private BigDecimal precoVenda;

    private LocalDate dtPrecoVenda;

    private BigDecimal precoCompra;

    private LocalDate dtPrecoCompra;

    @Size(max = 15, message = "A familia deve ter até 15 caracteres")
    private String codFamilia;

    private Boolean indItemFat;

    private BigDecimal precoUltEnt;

    private LocalDate dtUltEnt;

    private boolean indComprado;

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

    @Size(max = 10, message = "O NCM deve ter até 10 caracteres")
    private String codNcm;

    private String origem;

    private Integer tempoRessup;

    @Size(max = 14, message = "O GTIN deve ter até 14 caracteres")
    private String gtin;

    private Integer prazoEntrega;

    @Size(max = 20, message = "A situação deve ter até 60 caracteres")
    private String situacao;

    private LocalDate dtCriacao;

    @NotNull(message = "O status é obrigatório")
    private int status;
}
