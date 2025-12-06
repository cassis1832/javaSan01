package com.holis.san01.repository;

import com.holis.san01.model.TipoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoItemRepository extends JpaRepository<TipoItem, Integer> {

    @Query("SELECT t FROM TipoItem t WHERE t.empresa = :empresa AND t.status = 0 ORDE BY t.codTipoItem")
    List<TipoItem> listTipoItem(@Param("empresa") Integer empresa);
}
