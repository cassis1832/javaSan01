package com.holis.san01.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Informações para o login
 */
@Data
public class LoginDTO {
    @NotBlank(message = "Username é obrigatório")
    private String username;

    private String password;
}
