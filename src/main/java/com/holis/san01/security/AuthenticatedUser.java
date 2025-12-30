package com.holis.san01.security;

import java.util.List;

public record AuthenticatedUser(
        String email,
        String nome,
        List<String> roles,
        String ip) {
}
