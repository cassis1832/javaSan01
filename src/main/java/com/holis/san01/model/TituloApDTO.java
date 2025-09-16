package com.holis.san01.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode e @RequiredArgsConstructor
public class TituloApDTO {

    private Integer id;

    @NotBlank(message = "Filial é obrigatório")
    private Integer codFilial;

    @NotBlank(message = "Fornecedor é obrigatório")
    private Integer codEntd;

    private Integer tpTitulo;

    @NotBlank(message = "Espécie de documento é obrigatório")
    private String codEspDoc;

    private Integer numDoc;

    private Integer parcela = 1;

    private Integer docId;

    @Size(max = 30, message = "Descrição deve ter até 30 caracteres")
    private String descricao;

    private LocalDate dtVencto;

    private LocalDate dtPrevPag;

    private BigDecimal vlTitulo = BigDecimal.ZERO;

    private BigDecimal vlJuros = BigDecimal.ZERO;

    private BigDecimal vlMulta = BigDecimal.ZERO;

    private BigDecimal vlPago = BigDecimal.ZERO;

    private BigDecimal vlSaldo = BigDecimal.ZERO;

    private LocalDate dtLiquidac;

    private BigDecimal vlOriginal = BigDecimal.ZERO;

    private LocalDate dtVenctoOrigin;

    private Integer tipoFinId;

    private boolean logDuplEmitid = false;

    private boolean logEmisBoleto = false;

    private boolean logAvisoDbEmitid = false;

    private boolean logReciboEmitid = false;

    private boolean logNpromisEmitid = false;

    private boolean logDbAutom = false;

    private Integer parcelas = 1;

    private String obsPagto;

    private BigDecimal vlBaixaAntec = BigDecimal.ZERO;

    private String observacao;

    private int status;

    private String situacao;
}