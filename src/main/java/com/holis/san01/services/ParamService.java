package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.model.Param;
import com.holis.san01.repository.ParamRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service para tratamento da tabela de parametros
 */
@Service
@RequiredArgsConstructor
public class ParamService {

    private final ParamRepository paramRepository;

    @Transactional
    public int getNextSequence(final String codParam) {

        Param param = paramRepository.findById(codParam)
                .orElseThrow(() -> new ApiRequestException("Parâmetro " + codParam + " não encontrado"));

        param.setSequencia(param.getSequencia() + 1);
        param = paramRepository.saveAndFlush(param);
        return param.getSequencia();
    }
}
