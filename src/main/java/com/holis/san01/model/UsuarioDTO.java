package com.holis.san01.model;

import lombok.Data;

import javax.validation.constraints.*;
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
    private Date dtInicio;
    private Date dtTermino;
    private Date dtUltLogin;
    private Date dtAltSenha;
    private Date dtRecuperacao;
    private String tipoMenu;
    private Integer codFilial;

    private String situacao;
    private Date dtCriacao;
    @NotBlank
    @Size(min = 1, max = 1)
    @Pattern(regexp = "[SN]", message = "O campo 'archive' deve ser 'S' ou 'N'.")
    private String archive;
}