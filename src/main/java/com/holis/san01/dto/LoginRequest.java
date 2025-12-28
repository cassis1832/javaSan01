package com.holis.san01.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Informações para o login
 */
@Data
public class LoginRequest {
    @NotBlank(message = "Username é obrigatório")
    private String username;

    private String password;
}
