package com.holis.san01.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode e @RequiredArgsConstructor
@Entity
@Table(name = "cond_pagto")
public class CondPag {

    @Id
    @Column(nullable = false)
    private String codCondPag;

    @Column(nullable = false)
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
    private Integer status;
}
