package com.holis.san01.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {
    private String secret;
    private long expiration;
    private long refreshExpiration;
}
