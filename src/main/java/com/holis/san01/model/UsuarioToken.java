package com.holis.san01.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Entity
@Data
@Table(name = "usuario_token")
public class UsuarioToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;

    @Column(name = "token_hash", nullable = false, unique = true, columnDefinition = "TEXT")
    private String tokenHash;

    @Column(name = "expires_at", nullable = false)
    private OffsetDateTime expiresAt;

    @Column(name = "issued_at", insertable = false, updatable = false)
    private OffsetDateTime issuedAt;

    @Column(name = "revoked_at")
    private OffsetDateTime revokedAt;

    @Column(name = "replaced_by_token_id")
    private Integer replacedByTokenId;

    @Column(name = "created_by_ip", length = 45)
    private String createdByIp;
}

