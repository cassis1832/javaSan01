package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.model.Param;
import com.holis.san01.repository.ParamRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service para tratamento da tabela de parametros
 */
@Service
public class ParamService {
    @Autowired
    private ParamRepository paramRepository;

    public Param getParam(final String codParam) {
        return paramRepository.getParam(codParam)
                .orElseThrow(() -> new ApiRequestException("Parâmetro não encontrado"));
    }

    @Transactional
    public Param getNextSequence(final String codParam) {
        Param param = getParam((codParam));
        param.setCpoInteiro(param.getCpoInteiro() + 1);
        param = paramRepository.saveAndFlush(param);
        return param;
    }
}
