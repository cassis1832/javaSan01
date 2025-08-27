package com.holis.san01.model;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;

@Data
public class UsuarioDTO {
    @NotNull
    private Long id;

    @NotBlank(message = "Nome do usuário é obrigatório")
    private String nome;
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email com formato inválido")
    private String email;
    @NotNull(message = "Data de inicio é obrigatória")
    private LocalDate dtInicio;
    private LocalDate dtTermino;
    private LocalDate dtUltLogin;
    private LocalDate dtAltSenha;
    private LocalDate dtRecuperacao;
    private String tipoMenu;
    private Integer codFilial;
    private String situacao;
    private LocalDate dtCriacao;
    @NotNull
    private boolean archive;
}