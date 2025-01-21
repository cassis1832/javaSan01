package com.holis.san01.model;

import jakarta.persistence.Column;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class UsuarioDTO {
    private Long id;
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
    private String situacao;
    private Date dtCriacao;
    @Column(nullable = false)
    @Pattern(regexp = "[AI]", message = "O campo código da situação deve ser 'A' ou 'I'.")
    private String status;
}