package com.holis.san01.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Retorno do login com o token
 */
@Data
@AllArgsConstructor
public class TokenResponse {
    private String nomeUsuario;
    private String email;
    private String roles;
    private String token;
}
