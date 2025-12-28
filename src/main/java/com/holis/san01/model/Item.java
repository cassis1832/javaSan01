package com.holis.san01.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "item")
public class Item {

    @Id
    @Column(length = 15, nullable = false)
    private String codItem;

    @Column(length = 60, nullable = false)
    private String descricao;

    @Column(length = 2, nullable = false)
    private String codTipoItem;

    @Column(length = 6, nullable = false)
    private String codUniMed;

    private BigDecimal precoVenda;

    private LocalDate dtPrecoVenda;

    private BigDecimal precoCompra;

    private LocalDate dtPrecoCompra;

    @Column(length = 20)
    private String codFamilia;

    private Boolean indItemFat;

    private BigDecimal precoUltEnt;

    private LocalDate dtUltEnt;

    private Boolean indComprado;

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

    @Column(length = 10)
    private String codNcm;

    private String origem;

    private Integer tempoRessup;

    @Column(length = 14)
    private String gtin;

    private Integer prazoEntrega;

    private String situacao;

    @Column(nullable = false)
    private Boolean libCompra;

    @Column(nullable = false)
    private Boolean libVenda;

    @Column(nullable = false)
    private Boolean libProducao;

    @CreatedDate
    private Instant dtCriacao;

    @Column(nullable = false)
    private int status;
}
