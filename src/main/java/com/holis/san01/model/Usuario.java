package com.holis.san01.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 60, nullable = false)
    private String nome;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(nullable = false)
    private Integer codFilial;

    @Column(length = 100)
    private String senha;

    @Column(length = 100)
    private String roles;

    @Column(nullable = false)
    private LocalDate dtInicio;

    private LocalDate dtTermino;

    private LocalDate dtUltLogin;

    @Column(length = 100)
    private String novaSenha;

    @Column(length = 6)
    private String codRecuperacao;

    private LocalDate dtRecuperacao;

    private LocalDate dtAltSenha;

    @Column(length = 1)
    private String tipoMenu;

    @Column(length = 20)
    private String situacao;

    @CreatedDate
    private LocalDate dtCriacao;

    @Column(nullable = false)
    private int status;

    @Column(nullable = false)
    private boolean deleted;
}
