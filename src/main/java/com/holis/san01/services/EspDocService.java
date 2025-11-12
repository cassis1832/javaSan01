package com.holis.san01.services;

import com.holis.san01.exceptions.NotFoundRequestException;
import com.holis.san01.model.EspDoc;
import com.holis.san01.model.local.ApiResponse;
import com.holis.san01.repository.EspDocRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service para tratamento da tabela de especie de documento
 */
@Service
@RequiredArgsConstructor
public class EspDocService {

    private final EspDocRepository espDocRepository;

    public ApiResponse findById(final String codEspDoc) {
        EspDoc espDoc = espDocRepository.findByCodEspDoc(codEspDoc)
                .orElseThrow(() -> new NotFoundRequestException("Espécie de documento não encontrada"));
        return new ApiResponse(true, espDoc);
    }
}

