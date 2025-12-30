package com.holis.san01.security;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtils {

    private SecurityUtils() {
    }

    public static AuthenticatedUser authenticatedUser() {
        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();

        return (AuthenticatedUser) auth.getPrincipal();
    }

    public static Claims jwtClaims() {
        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();

        return (Claims) auth.getDetails();
    }
}
