package com.holis.san01.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "vw_usuario")
public class VwUsuario {

    @Id
    private Integer id;
    private Integer codFilial;
    private String nome;
    private String email;
    private LocalDate dtInicio;
    private LocalDate dtTermino;
    private Instant dtCriacao;
    private String situacao;
    private Integer status;
}
