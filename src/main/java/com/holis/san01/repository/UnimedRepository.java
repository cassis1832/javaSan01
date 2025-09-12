package com.holis.san01.repository;

import com.holis.san01.model.Unimed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnimedRepository extends JpaRepository<Unimed, String> {
    @Query("select u from Unimed u Where upper(u.codUnimed) =  upper(?1)")
    Optional<Unimed> findUnimed(String codUnimed);

    @Query("select u from Unimed u Where u.status =  ?1 order by u.sequencia, u.codUnimed")
    List<Unimed> listUnimed(int status);
}
