package com.holis.san01.repository;

import com.holis.san01.model.VwUsuario;
import org.springframework.data.annotation.Immutable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * View de USUARIOS
 */
@Immutable
public interface VwUsuarioRepository extends JpaRepository<VwUsuario, Integer>, JpaSpecificationExecutor<VwUsuario> {
}