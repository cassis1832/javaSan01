package com.holis.san01.repository;

import com.holis.san01.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("select u from Usuario u where " +
            "upper(email) = upper(?1) and " +
            "archive = 'N' ")
    Optional<Usuario> findByEmail(String email);
}
