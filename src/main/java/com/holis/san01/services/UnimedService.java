package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.model.ApiResponse;
import com.holis.san01.model.Unimed;
import com.holis.san01.repository.UnimedRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service para tratamento da tabela de unidade de medida
 */
@Service
public class UnimedService {
    @Autowired
    private UnimedRepository unimedRepository;

    public Unimed ler(final String codUnimed) {
        return unimedRepository.findUnimed(codUnimed)
                .orElseThrow(() -> new ApiRequestException("Unidade de medida não encontrada"));
    }

    public ApiResponse listar(final int status) {
        return new ApiResponse(true, unimedRepository.listUnimed(status));
    }

    @Transactional
    public Unimed incluir(final Unimed unimed) {
        Optional<Unimed> opt = unimedRepository.findUnimed(unimed.getCodUnimed());

        if (opt.isPresent()) {
            throw new ApiRequestException("Esta unidade de medida já existe!");
        }

        return unimedRepository.saveAndFlush(unimed);
    }

    @Transactional
    public Unimed alterar(final Unimed dto) {
        Unimed unimed = unimedRepository.findUnimed(dto.getCodUnimed())
                .orElseThrow(() -> new ApiRequestException("Unidade de medida não encontrada!"));

        unimed.setCodUnimed(dto.getCodUnimed());
        unimed.setDescricao(dto.getDescricao());
        unimed.setSequencia(dto.getSequencia());
        unimed.setFraciona(dto.getFraciona());
        unimed.setStatus(dto.getStatus());
        return unimedRepository.saveAndFlush(unimed);
    }

    @Transactional
    public void excluir(final String codUnimed) {
        Unimed unimed = unimedRepository.findUnimed(codUnimed)
                .orElseThrow(() -> new ApiRequestException("Unidade de medida não encontrada!"));
        unimedRepository.deleteById(unimed.getCodUnimed());
    }
}
