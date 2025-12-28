package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.model.Ncm;
import com.holis.san01.repository.NcmRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service para tratamento da tabela de NCM
 */
@Service
@RequiredArgsConstructor
public class NcmService {

    private final NcmRepository ncmRepository;

    public Ncm findById(final String codNcm) {
        return ncmRepository.findById(codNcm)
                .orElseThrow(() -> new ApiRequestException("NCM não encontrada"));
    }

    public Page<Ncm> listarPaging(final int status, final String filterText, final Pageable pageable) {
        Page<Ncm> ncms;

        if (StringUtils.isBlank(filterText)) {
            ncms = ncmRepository.pageNcm(status, pageable);
        } else {
            ncms = ncmRepository.pageNcm(status, filterText, pageable);
        }

        return ncms;
    }

    @Transactional
    public Ncm incluir(final Ncm dto) {

        Optional<Ncm> opt = ncmRepository.findById(dto.getCodNcm());

        if (opt.isPresent()) {
            throw new ApiRequestException("Este código de ncm já existe!");
        }

        return ncmRepository.saveAndFlush(dto);
    }

    @Transactional
    public Ncm alterar(final Ncm dto) {

        Ncm ncm = ncmRepository.findById(dto.getCodNcm())
                .orElseThrow(() -> new ApiRequestException("Ncm não encontrado"));

        ncm.setCodNcm(dto.getCodNcm());
        ncm.setDescricao(dto.getDescricao());
        ncm.setStatus(dto.getStatus());
        ncm = ncmRepository.saveAndFlush(ncm);
        return ncm;
    }

    @Transactional
    public void excluir(final String codNcm) {

        Ncm ncm = ncmRepository.findById(codNcm)
                .orElseThrow(() -> new ApiRequestException("Ncm não encontrado"));
        ncmRepository.deleteById(ncm.getCodNcm());
    }
}
