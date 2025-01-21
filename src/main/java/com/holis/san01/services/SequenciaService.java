package com.holis.san01.services;

import com.holis.san01.exceptions.NotFoundRequestException;
import com.holis.san01.model.Sequencia;
import com.holis.san01.repository.SequenciaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Próximo numero automatico de item, emitente, pedidos, etc.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class SequenciaService {

    private final SequenciaRepository sequenciaRepository;

    /**
     * Obter o proximo numero
     */
    public Integer proximoNumero(String codSeq) {

        Sequencia seq = sequenciaRepository.findSequencia(codSeq)
                .orElseThrow(() -> new NotFoundRequestException("Numeração não encontrada"));

        return seq.getNumeroSeguinte();
    }

    /**
     * Obtem o último numero usado
     */
    public Integer ultimoNumero(String codSeq) {

        Sequencia seq = sequenciaRepository.findSequencia(codSeq)
                .orElseThrow(() -> new NotFoundRequestException("Numeração não encontrada"));

        return seq.getNumeroAtual();
    }

    public void salvaSequencia(String codSeq, Integer numero) {

        Sequencia seq = sequenciaRepository.findSequencia(codSeq)
                .orElseThrow(() -> new NotFoundRequestException("Numeração não encontrada"));

        if (numero > seq.getNumeroAtual()) {
            seq.setNumeroAtual(numero);
            seq.setNumeroSeguinte(numero + 1);
            sequenciaRepository.saveAndFlush(seq);
        }
    }
}