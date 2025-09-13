package com.holis.san01.services;

import com.holis.san01.exceptions.NotFoundRequestException;
import com.holis.san01.model.ApiResponse;
import com.holis.san01.model.EspDoc;
import com.holis.san01.repository.EspDocRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service para tratamento da tabela de especie de documento
 */
@Service
@RequiredArgsConstructor
public class EspDocService {

    private final EspDocRepository espDocRepository;

    public ApiResponse pageEspDoc(final boolean tipoMovto, final Pageable pageable) {

        Page<EspDoc> espDocs = espDocRepository.pageEspDoc(tipoMovto, pageable);
        return new ApiResponse(true, espDocs);
    }

    public ApiResponse getEspDoc(final String codEspDoc) {

        EspDoc espDoc = espDocRepository.findEspDocByCodEspDoc(codEspDoc)
                .orElseThrow(() -> new NotFoundRequestException("Espécie de documento não encontrada"));
        return new ApiResponse(true, espDoc);
    }
}

