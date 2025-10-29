package com.holis.san01.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode e @RequiredArgsConstructor
@Entity
@Table(name = "cond_pagto")
public class CondPag {

    @Id
    @Column(nullable = false)
    @NotNull(message = "O código da condição de pagamento é obrigatório")
    private String codCondPag;

    @Column(nullable = false)
    @NotNull(message = "A descrição é obrigatória")
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

    @Column(nullable = false)
    @NotNull(message = "O status é obrigatório")
    private Integer status;
}
