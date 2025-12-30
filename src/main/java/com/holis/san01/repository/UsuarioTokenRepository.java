package com.holis.san01.repository;

import com.holis.san01.model.UsuarioToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioTokenRepository extends JpaRepository<UsuarioToken, Long> {

    Optional<UsuarioToken> findByTokenHashAndRevokedAtFalse(String tokenHash);
}