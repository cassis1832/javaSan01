package com.holis.san01.repository;

import com.holis.san01.model.TipoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoItemRepository extends JpaRepository<TipoItem, String> {

    @Query("select t from TipoItem t where status = 0 order by codTipoItem")
    List<TipoItem> listTipoItem();
}
