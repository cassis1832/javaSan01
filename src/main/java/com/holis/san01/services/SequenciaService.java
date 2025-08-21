package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.model.Sequencia;
import com.holis.san01.repository.SequenciaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Próximo numero automatico de item, emitente, pedidos, etc.
 */
@Service
public class SequenciaService {
    @Autowired
    private SequenciaRepository sequenciaRepository;

    /**
     * Obter o proximo numero
     */
    public Long proximoNumero(final String codSeq) {
        Sequencia seq = sequenciaRepository.findSequencia(codSeq)
                .orElseThrow(() -> new ApiRequestException("Numeração não encontrada"));
        return seq.getNumeroSeguinte();
    }

    /**
     * Obtem o último numero usado
     */
    public Long ultimoNumero(final String codSeq) {
        Sequencia seq = sequenciaRepository.findSequencia(codSeq)
                .orElseThrow(() -> new ApiRequestException("Numeração não encontrada"));
        return seq.getNumeroAtual();
    }

    @Transactional
    public void salvaSequencia(final String codSeq, final Long numero) {
        Sequencia seq = sequenciaRepository.findSequencia(codSeq)
                .orElseThrow(() -> new ApiRequestException("Numeração não encontrada"));

        if (numero > seq.getNumeroAtual()) {
            seq.setNumeroAtual(numero);
            seq.setNumeroSeguinte(numero + 1);
            sequenciaRepository.saveAndFlush(seq);
        }
    }
}