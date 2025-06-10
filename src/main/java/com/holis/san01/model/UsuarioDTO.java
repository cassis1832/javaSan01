package com.holis.san01.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class UsuarioDTO {
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    @Pattern(regexp = "[SN]", message = "O campo 'archive' deve ser 'S' ou 'N'.")
    private String archive;
    private String situacao;
    private Date dtCriacao;
    @NotNull(message = "Email é obrigatório")
    @Email(message = "Email com formato inválido")
    private String email;
    @NotNull(message = "Nome do usuário é obrigatório")
    private String nome;
    @NotNull(message = "Data de inicio é obrigatória")
    private Integer codFilial;
    private Date dtInicio;
    private Date dtTermino;
    private Date dtUltLogin;
    private Date dtAltSenha;
    private Date dtRecuperacao;
    private String tipoMenu;
}