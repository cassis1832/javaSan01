package com.holis.san01.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private final JwtProperties props;

    public JwtService(JwtProperties props) {
        this.props = props;
    }

    public String generateToken(AuthenticatedUser user) {

        return Jwts.builder()
                .setSubject(user.email())                     // subject = email
                .claim("email", user.email())
                .claim("nome", user.nome())
                .claim("roles", String.join(",", user.roles()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + props.getExpiration()))
                .signWith(
                        Keys.hmacShaKeyFor(props.getSecret().getBytes()),
                        SignatureAlgorithm.HS256
                )
                .compact();
    }

    public String generateRefreshToken(AuthenticatedUser user) {

        return Jwts.builder()
                .setSubject(user.email())
                .claim("type", "refresh")
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + props.getRefreshExpiration())
                )
                .signWith(
                        Keys.hmacShaKeyFor(props.getSecret().getBytes()),
                        SignatureAlgorithm.HS256
                )
                .compact();
    }

    public Claims getClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(props.getSecret().getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
