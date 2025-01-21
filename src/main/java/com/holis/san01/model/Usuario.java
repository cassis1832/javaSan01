package com.holis.san01.model;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
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
    private Date dtInicio;
    private Date dtTermino;
    private Date dtUltLogin;
    private String novaSenha;
    private String codRecuperacao;
    private Date dtRecuperacao;
    private Date dtAltSenha;
    private String tipoMenu;
    private String situacao;
    private Date dtCriacao;
    @Column(nullable = false)
    @Pattern(regexp = "[AI]", message = "O campo código da situação deve ser 'A' ou 'I'.")
    private String status;
}