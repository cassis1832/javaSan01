package com.holis.san01.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class JwtTokenUtil {

    private String nomeUsuario;

    // Obter o nome do usuário a partir do Token
    public void loadTokenData(String token) {
        Integer usuarioId = JWT.decode(token).getClaim("uid").asInt();
        this.nomeUsuario = JWT.decode(token).getClaim("usr").toString().replaceAll("\"", "");
    }

    // Obter o nome do usuário a partir do Header (que contém o accessToken)
    public String getUsuario(String authHeader) {
        Claim usuario = JWT.decode(authHeader.substring(SecurityConstants.TOKEN_INDEX)).getClaim("usr");
        return usuario.toString().replaceAll("\"", "");
    }
}
