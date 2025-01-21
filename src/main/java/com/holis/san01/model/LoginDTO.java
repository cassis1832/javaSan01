package com.holis.san01.model;

import lombok.Data;

/**
 * Informações para o login
 */
@Data
public class LoginDTO {
    private String username;
    private String password;
}
