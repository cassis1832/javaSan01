package com.holis.san01.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {
    private static Long usuarioId;
    private static String nomeUsuario;

    public void loadTokenData(String token) {
        this.usuarioId = JWT.decode(token).getClaim("uid").asLong();
        this.nomeUsuario = JWT.decode(token).getClaim("usr").toString().replaceAll("\"", "");
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getUsuario(String authHeader) {
        Claim usuario = JWT.decode(authHeader.substring(SecurityConstants.TOKEN_INDEX)).getClaim("usr");
        return usuario.toString().replaceAll("\"", "");
    }
}
