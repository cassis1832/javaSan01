package com.holis.san01.model;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String email;
    private Integer codFilial;
    private String senha;
    private String roles;
    @Column(nullable = false)
    private LocalDate dtInicio;
    private LocalDate dtTermino;
    private LocalDate dtUltLogin;
    private String novaSenha;
    private String codRecuperacao;
    private LocalDate dtRecuperacao;
    private LocalDate dtAltSenha;
    private String tipoMenu;

    private String situacao;
    private LocalDate dtCriacao;
    @Column(nullable = false)
    private boolean archive;
}
