package com.holis.san01.model;

import lombok.Data;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
public class UsuarioDTO {
    private Integer id;

    @NotBlank(message = "Nome do usuário é obrigatório")
    @Size(max = 60, message = "O nome deve ter até 60 caracteres")
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email com formato inválido")
    @Size(max = 100, message = "O email deve ter até 100 caracteres")
    private String email;

    @NotNull(message = "Data de inicio é obrigatória")
    @FutureOrPresent
    private LocalDate dtInicio;

    @FutureOrPresent
    private LocalDate dtTermino;

    @PastOrPresent
    private LocalDate dtUltLogin;

    @PastOrPresent
    private LocalDate dtAltSenha;

    @PastOrPresent
    private LocalDate dtRecuperacao;

    @Size(max = 1, message = "O tipo de meun deve ter 1 caracter")
    private String tipoMenu;

    @NotNull(message = "Filial é obrigatória")
    private Integer codFilial;

    @Size(max = 20, message = "A situação deve ter até 20 caracteres")
    private String situacao;

    private LocalDate dtCriacao;

    @NotNull(message = "O status é obrigatório")
    private int status;
}