package com.holis.san01.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
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

    private Instant dtUltLogin;

    @Column(length = 100)
    private String novaSenha;

    @Column(length = 6)
    private String codRecup;

    private Instant dtRecup;

    private Instant dtAltSenha;

    @Column(length = 20)
    private String situacao;

    @CreatedDate
    private Instant dtCriacao;

    @Column(nullable = false)
    private Integer status;
}
