package com.holis.san01.services;

import com.holis.san01.dto.ApiResponse;
import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.model.UnidMedida;
import com.holis.san01.repository.UnidMedidaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service para tratamento da tabela de unidade de medida
 */
@Service
@RequiredArgsConstructor
public class UnidMedidaService {

    private final UnidMedidaRepository unidMedidaRepository;

    public UnidMedida findByCodUnimed(@NotNull final String codUniMed) {
        return unidMedidaRepository.findById(codUniMed)
                .orElseThrow(() -> new ApiRequestException("Unidade de medida não encontrada"));
    }

    public ApiResponse listUnidMedida(final int status) {
        return new ApiResponse(true, unidMedidaRepository.listUniMed(status));
    }

    @Transactional
    public UnidMedida create(@NotNull final UnidMedida unidMedida) {

        if (unidMedidaRepository.existsBycodUniMed(unidMedida.getCodUniMed()))
            throw new ApiRequestException("Unidade de medida já existe!");

        return unidMedidaRepository.saveAndFlush(unidMedida);
    }

    @Transactional
    public UnidMedida update(@NotNull final UnidMedida dto) {

        UnidMedida unidMedida = unidMedidaRepository.findById(dto.getCodUniMed())
                .orElseThrow(() -> new ApiRequestException("Unidade de medida não encontrada!"));

        unidMedida.setDescricao(dto.getDescricao());
        unidMedida.setSequencia(dto.getSequencia());
        unidMedida.setFraciona(dto.getFraciona());
        unidMedida.setStatus(dto.getStatus());
        return unidMedidaRepository.saveAndFlush(unidMedida);
    }

    @Transactional
    public void delete(@NotNull final String codUniMed) {

        if (!unidMedidaRepository.existsBycodUniMed(codUniMed))
            throw new ApiRequestException("Unidade de medida não encontrada!");

        unidMedidaRepository.deleteById(codUniMed);
    }
}
