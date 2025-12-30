package com.holis.san01.dto;

public record TokenResponse(
        String accessToken,
        String refreshToken
) {
}
