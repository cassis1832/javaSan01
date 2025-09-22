package com.holis.san01.security;

import com.holis.san01.model.local.TokenResponse;
import com.holis.san01.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtGenerator {

    private final JwtTokenUtil jwtTokenUtil;

    public TokenResponse generateToken(Authentication authentication, Usuario usuario) {

        Claims claims = Jwts.claims().setSubject(usuario.getEmail());
        claims.put("uid", usuario.getId());
        claims.put("usr", usuario.getNome());
        claims.put("roles", authentication.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()));

        LocalDateTime currentTime = LocalDateTime.now();

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer("Holis")
                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(currentTime
                        .plusMinutes(SecurityConstants.JWT_EXPIRATION)
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.JWT_SECRET)
                .compact();

        jwtTokenUtil.loadTokenData(token);

        String userRoles = "";

        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (!userRoles.isEmpty()) {
                userRoles = userRoles + ",";
            }
            userRoles = userRoles + authority.getAuthority();
        }

        return new TokenResponse(usuario.getNome(), usuario.getEmail(), userRoles, token);
    }

    public String getEmailFromJWT(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {

        try {
            Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
        }
    }
}
