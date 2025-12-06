package com.holis.san01.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CondPagDto {

    private Integer id;

    @NotNull(message = "Empresa é obrigatória")
    private Integer empresa;

    @NotBlank(message = "O código da condição de pagamento é obrigatório")
    private String codCondPag;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(max = 60, message = "A descrição deve ter até 60 caracteres")
    private String descricao;

    private Boolean ctPag;
    private Boolean ctRec;
    private Boolean geraPag;
    private Boolean geraRec;
    private String diasDesconto;
    private Integer diasData;
    private Integer intervalo;
    private String diaFixoMes;
    private Integer diaFixoSemana;
    private String venctos;
    private String prazos;
    private String percentuais;

    @NotNull(message = "O status é obrigatório")
    private Integer status;
}
