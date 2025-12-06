package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.model.Parametro;
import com.holis.san01.repository.ParamRepository;
import com.holis.san01.security.JwtToken;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service para tratamento da tabela de parametros
 */
@Service
@RequiredArgsConstructor
public class ParamService {

    private final JwtToken jwtToken;
    private final ParamRepository paramRepository;

    @Transactional
    public int getNextSequence(final String codParam) {

        Parametro parametro = paramRepository.findByCodParam(jwtToken.getEmpresa(), codParam)
                .orElseThrow(() -> new ApiRequestException("Parâmetro " + codParam + " não encontrado"));

        parametro.setSequencia(parametro.getSequencia() + 1);
        parametro = paramRepository.saveAndFlush(parametro);
        return parametro.getSequencia();
    }
}
