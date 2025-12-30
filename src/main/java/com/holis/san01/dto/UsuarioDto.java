package com.holis.san01.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data
public class UsuarioDto {
    private Integer id;

    @NotBlank(message = "Nome do usuário é obrigatório")
    @Size(max = 60, message = "O nome deve ter até 60 caracteres")
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email com formato inválido")
    @Size(max = 100, message = "O email deve ter até 100 caracteres")
    private String email;

    @Size(max = 200, message = "Roles deve ter até 200 caracteres")
    private String roles; // ADMIN,USER

    @NotNull(message = "Data de inicio é obrigatória")
    @FutureOrPresent
    private Date dtInicio;

    @NotNull(message = "Data de inicio é obrigatória")
    @FutureOrPresent
    private Date dtTermino;

    @NotNull(message = "O status é obrigatório")
    private Integer status;

    @NotNull(message = "Filial é obrigatória")
    private Integer codFilial;

    private Date dtCriacao;
    private String ipUltimaAlteracao;

    @Size(max = 20, message = "A situação deve ter até 20 caracteres")
    private String situacao;

}