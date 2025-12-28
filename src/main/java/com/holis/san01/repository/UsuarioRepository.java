package com.holis.san01.repository;

import com.holis.san01.model.Usuario;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Nonnull
    @Query("SELECT u FROM Usuario u WHERE  u.id = :id AND status <> 9 ")
    Optional<Usuario> findById(@Nonnull @Param("id") Integer id);

    @Query("SELECT u FROM Usuario u WHERE LOWER(u.email) = LOWER(:email) ")
    Optional<Usuario> findByEmail(@Param("email") String email);

    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END " +
            "FROM Usuario i WHERE LOWER(i.email) = LOWER(:email) ")
    boolean existsByEmail(@Param("email") String email);

    @Query("UPDATE Usuario SET status = :status WHERE id = :id")
    void archive(@Param("id") Integer id, @Param("status") Integer status);
}
