package com.holis.san01.repository;

import com.holis.san01.model.TituloAp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TituloApRepository extends JpaRepository<TituloAp, Integer> {
    @Query("select t from TituloAp t where id = ?1 and deleted = false")
    Optional<TituloAp> getTituloAp(Integer id);
}