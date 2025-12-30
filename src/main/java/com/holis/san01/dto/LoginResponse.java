package com.holis.san01.dto;

public record LoginResponse(
        String token,
        String refreshToken,
        String type,
        long expiresInSeconds
) {
    public static LoginResponse bearer(TokenResponse tokenResponse) {
        return new LoginResponse(
                tokenResponse.accessToken(),
                tokenResponse.refreshToken(),
                "Bearer",
                30 * 60);
    }
}