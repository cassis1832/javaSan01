package com.holis.san01.repository;

import com.holis.san01.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u WHERE LOWER(u.email) = LOWER(:email) AND status = 0 ")
    Optional<Usuario> findByEmail(@Param("email") String email);

    @Query("SELECT u FROM Usuario u WHERE u.empresa = :empresa AND LOWER(u.email) = LOWER(:email) AND status = 0 ")
    Optional<Usuario> findByEmpresaEmail(@Param("empresa") Integer empresa, @Param("email") String email);
}
