package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.model.Situacao;
import com.holis.san01.repository.SituacaoRepository;
import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service para tratamento da tabela de situações
 */
@Service
@RequiredArgsConstructor
public class SituacaoService {

    private final SituacaoRepository situacaoRepository;

    public Situacao findSituacaoBySituacao(@NotNull final String objeto, @NotNull final Integer codSit) {

        return situacaoRepository.findSituacaoBySituacao(objeto, codSit)
                .orElseThrow(() -> new ApiRequestException("Situação não encontrada"));
    }

    public List<Situacao> listSituacao(@Nonnull final String objeto) {
        return situacaoRepository.listSituacao(objeto);
    }

    @Transactional
    public Situacao create(@NotNull final Situacao situacao) {
        if (situacaoRepository.existsByObjetoAndCodSit(situacao.getObjeto(), situacao.getCodSit()))
            throw new ApiRequestException("Situação já existe!");

        return situacaoRepository.saveAndFlush(situacao);
    }

    @Transactional
    public Situacao update(@NotNull final Situacao dto) {
        Situacao situacao = situacaoRepository.findById(dto.getId())
                .orElseThrow(() -> new ApiRequestException("Situação não encontrada!"));

        situacao.setDescricao(dto.getDescricao());
        situacao.setSequencia(dto.getSequencia());
        return situacaoRepository.saveAndFlush(situacao);
    }
}
