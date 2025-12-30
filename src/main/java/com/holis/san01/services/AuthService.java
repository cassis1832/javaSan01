package com.holis.san01.services;

import com.holis.san01.dto.TokenResponse;
import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.model.Usuario;
import com.holis.san01.model.UsuarioToken;
import com.holis.san01.repository.UsuarioRepository;
import com.holis.san01.repository.UsuarioTokenRepository;
import com.holis.san01.security.AuthenticatedUser;
import com.holis.san01.security.JwtProperties;
import com.holis.san01.security.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtProperties props;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioTokenRepository usuarioTokenRepository;

    @Transactional
    public TokenResponse login(String email, String senha) {

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ApiRequestException("Usuário inválido"));

        if (!encoder.matches(senha, usuario.getSenha())) {
            throw new ApiRequestException("Senha inválida");
        }

        if (usuario.getDtInicio().isAfter(LocalDate.now()) ||
                usuario.getDtTermino().isBefore(LocalDate.now()) ||
                usuario.getStatus() == 1)
            throw new ApiRequestException("Usuário não habilitado");

        AuthenticatedUser authenticatedUser =
                new AuthenticatedUser(
                        usuario.getEmail(),
                        usuario.getNome(),
                        Arrays.asList(usuario.getRoles().split(",")),
                        null // IP não é necessário no token
                );

        String token = jwtService.generateToken(authenticatedUser);
        String refreshToken = jwtService.generateRefreshToken(authenticatedUser);

        UsuarioToken rt = new UsuarioToken();
        rt.setTokenHash(DigestUtils.sha256Hex(refreshToken));
        rt.setUsuarioId(usuario.getId());
        rt.setExpiresAt(
                OffsetDateTime.from(Instant.now()
                        .plusMillis(props.getRefreshExpiration()))
        );

        usuarioTokenRepository.save(rt);

        return new TokenResponse(token, refreshToken);
    }

    public TokenResponse refresh(String refreshToken) {
        return null;
    }

//    public TokenResponse logoff(String refreshToken) {
//    }
}
