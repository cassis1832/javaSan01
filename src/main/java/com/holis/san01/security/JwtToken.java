package com.holis.san01.security;

import com.auth0.jwt.JWT;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class JwtToken {

    private Integer empresa = 1;
    private Integer usuarioId = 1;
    private String nomeUsuario = "Usuario de testes";

    // Carregar os dados do token
    public void loadTokenData(String token) {
        empresa = JWT.decode(token).getClaim("emp").asInt();
        usuarioId = JWT.decode(token).getClaim("uid").asInt();
        nomeUsuario = JWT.decode(token).getClaim("usr").toString().replaceAll("\"", "");
    }
}
