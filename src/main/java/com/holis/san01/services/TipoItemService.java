package com.holis.san01.services;

import com.holis.san01.model.TipoItem;
import com.holis.san01.repository.TipoItemRepository;
import com.holis.san01.security.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service para tratamento da tabela de tipos de itens
 */
@Service
@RequiredArgsConstructor
public class TipoItemService {

    private final JwtToken jwtToken;
    private final TipoItemRepository tipoItemRepository;

    public List<TipoItem> listTipoItem() {
        return tipoItemRepository.listTipoItem(jwtToken.getEmpresa());
    }
}
