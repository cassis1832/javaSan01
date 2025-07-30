package com.holis.san01.services;

import com.holis.san01.model.TipoItem;
import com.holis.san01.repository.TipoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service para tratamento da tabela de tipos de itens
 */
@Service
public class TipoItemService {
    @Autowired
    private TipoItemRepository tipoItemRepository;

    public List<TipoItem> listar() {
        return tipoItemRepository.listTipos();
    }
}
