package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.exceptions.NotFoundRequestException;
import com.holis.san01.model.UnidMedida;
import com.holis.san01.repository.UnidMedidaRepository;
import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service para tratamento da tabela de unidade de medida
 */
@Service
@RequiredArgsConstructor
public class UnidMedidaService implements BaseService<UnidMedida, String, UnidMedida> {

    private final UnidMedidaRepository unidMedidaRepository;

    @Override
    public UnidMedida findById(@Nonnull String id) {
        return unidMedidaRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException("Condição de pagamento não cadastrada"));
    }

    @Override
    @Transactional
    public UnidMedida create(@NotNull final UnidMedida unidMedida) {

        if (unidMedidaRepository.existsBycodUniMed(unidMedida.getCodUniMed()))
            throw new ApiRequestException("Unidade de medida já existe!");

        return unidMedidaRepository.saveAndFlush(unidMedida);
    }

    @Override
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
    public void deleteById(@Nonnull final String codUniMed) {
        if (!unidMedidaRepository.existsBycodUniMed(codUniMed))
            throw new ApiRequestException("Unidade de medida não encontrada!");

        unidMedidaRepository.deleteById(codUniMed);
    }

    @Override
    public List<UnidMedida> findList(Map<String, String> filters) {
        return List.of();
    }

    @Override
    public Page<UnidMedida> findPage(Pageable pageable, Map<String, String> filtros) {
        return null;
    }

    @Override
    public void archive(@Nonnull String s) {

    }

    @Override
    public void unarchive(@Nonnull String s) {

    }
}
