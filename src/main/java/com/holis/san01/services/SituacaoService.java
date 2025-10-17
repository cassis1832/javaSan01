package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.model.Situacao;
import com.holis.san01.model.local.ApiResponse;
import com.holis.san01.model.local.FiltroPesquisa;
import com.holis.san01.repository.SituacaoRepository;
import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service para tratamento da tabela de situações
 */
@Service
@RequiredArgsConstructor
public class SituacaoService {

    private final SituacaoRepository situacaoRepository;

    public Situacao findSituacaoById(@NotNull final Integer id) {

        return situacaoRepository.findSituacaoById(id)
                .orElseThrow(() -> new ApiRequestException("Situação não encontrada"));
    }

    public ApiResponse listSituacao(@Nonnull final FiltroPesquisa filtro) {

        return new ApiResponse(true, situacaoRepository.listSituacao(filtro.getCodigo()));
    }

    @Transactional
    public Situacao create(@NotNull final Situacao situacao) {

        if (situacaoRepository.existsByObjetoAndDescricao(situacao.getObjeto(), situacao.getDescricao()))
            throw new ApiRequestException("Situação já existe!");

        return situacaoRepository.saveAndFlush(situacao);
    }

    @Transactional
    public Situacao update(@NotNull final Situacao dto) {

        Situacao situacao = situacaoRepository.findById(dto.getId())
                .orElseThrow(() -> new ApiRequestException("Situação não encontrada!"));

        situacao.setDescricao(dto.getDescricao());
        situacao.setSequencia(dto.getSequencia());
        situacao.setSituacao(dto.getSituacao());
        return situacaoRepository.saveAndFlush(situacao);
    }

    @Transactional
    public void delete(@NotNull final Integer id) {

        if (!situacaoRepository.existsById(id))
            throw new ApiRequestException("Situação não encontrada!");

        situacaoRepository.deleteById(id);
    }
}
