package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.model.Ncm;
import com.holis.san01.repository.NcmRepository;
import com.holis.san01.util.Mapper;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service para tratamento da tabela de NCM
 */
@Service
public class NcmService {
    @Autowired
    private NcmRepository ncmRepository;

    public Ncm ler(final String codNcm) {
        return ncmRepository.findNcm(codNcm)
                .orElseThrow(() -> new ApiRequestException("NCM não encontrada"));
    }

    public Page<Ncm> listarPaging(final String archive, final String filterText, final Pageable pageable) {
        Page<Ncm> ncms;

        if (StringUtils.isBlank(filterText)) {
            ncms = ncmRepository.listNcm(archive, pageable);
        } else {
            ncms = ncmRepository.listNcm(archive, filterText, pageable);
        }

        return ncms;
    }

    @Transactional
    public Ncm incluir(final Ncm dto) {
        Optional<Ncm> opt = ncmRepository.findNcm(dto.getCodNcm());

        if (opt.isPresent()) {
            throw new ApiRequestException("Este código de ncm já existe!");
        }

        Ncm ncm = Mapper.mapTo(dto, Ncm.class);
        ncm = ncmRepository.saveAndFlush(ncm);
        return ncm;
    }

    @Transactional
    public Ncm alterar(final Ncm dto) {
        Ncm ncm = ncmRepository.findNcm(dto.getCodNcm())
                .orElseThrow(() -> new ApiRequestException("Ncm não encontrado"));

        ncm.setCodNcm(dto.getCodNcm());
        ncm.setDescricao(dto.getDescricao());
        ncm.setArchive(dto.getArchive());
        ncm = ncmRepository.saveAndFlush(ncm);
        return ncm;
    }

    @Transactional
    public void excluir(final String codNcm) {
        Ncm ncm = ncmRepository.findNcm(codNcm)
                .orElseThrow(() -> new ApiRequestException("Ncm não encontrado"));
        ncmRepository.deleteById(ncm.getCodNcm());
    }
}
