package com.holis.san01.services;

import com.holis.san01.model.ApiResponse;
import com.holis.san01.repository.TipoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service para tratamento da tabela de tipos de itens
 */
@Service
public class TipoItemService {
    @Autowired
    private TipoItemRepository tipoItemRepository;

    public ApiResponse listar() {
        return new ApiResponse(true, tipoItemRepository.listTipos());
    }
}
