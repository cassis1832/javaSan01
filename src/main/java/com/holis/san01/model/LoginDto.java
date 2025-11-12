package com.holis.san01.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Informações para o login
 */
@Data
public class LoginDto {
    @NotBlank(message = "Username é obrigatório")
    private String username;

    private String password;
}
