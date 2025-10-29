package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.exceptions.NotFoundRequestException;
import com.holis.san01.model.CondPag;
import com.holis.san01.model.local.ApiResponse;
import com.holis.san01.model.local.FiltroPesquisa;
import com.holis.san01.repository.CondPagRepository;
import com.holis.san01.specs.CondPagSpecifications;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service para tratamento da tabela de condição de pagamento
 */
@Service
@RequiredArgsConstructor
public class CondPagService {

    private final CondPagRepository condPagRepository;

    public ApiResponse listCondPag() {
        List<CondPag> condPags = condPagRepository.listCondPag();
        return new ApiResponse(true, condPags);
    }

    public ApiResponse findCondPag(final String condCondPag) {
        CondPag condPag = condPagRepository.findCondPagByCodCondPag(condCondPag)
                .orElseThrow(() -> new NotFoundRequestException("Condição de pagamento não encontrada"));
        return new ApiResponse(true, condPag);
    }

    public Page<CondPag> pageCondPag(FiltroPesquisa filtro) {

        Specification<CondPag> spec = Specification.where(null);

        if (filtro.getStatus() != null)
            spec = spec.and(CondPagSpecifications.hasStatus(filtro.getStatus()));

        if (!StringUtils.isBlank(filtro.getFilterText()))
            spec = spec.and(CondPagSpecifications.hasFiltro(filtro.getFilterText()));

        if (filtro.getTipo().equalsIgnoreCase("nenhum"))
            spec = spec.and(CondPagSpecifications.nenhumTipo());

        if (filtro.getTipo().equalsIgnoreCase("todos"))
            spec = spec.and(CondPagSpecifications.ambos());

        if (filtro.getTipo().equalsIgnoreCase("pagar"))
            spec = spec.and(CondPagSpecifications.pagar());

        if (filtro.getTipo().equalsIgnoreCase("receber"))
            spec = spec.and(CondPagSpecifications.receber());

        Sort sort = Sort.by(Sort.Direction.fromString(filtro.getSortDirection()), filtro.getSortField());

        Pageable pageable = PageRequest.of(filtro.getPageIndex(), filtro.getSize(), sort);
        return condPagRepository.findAll(spec, pageable);
    }

    @Transactional
    public CondPag create(@Valid CondPag condPagNew) {

        if (condPagRepository.existsByCodCondPag(condPagNew.getCodCondPag())) {
            throw new ApiRequestException("Este código de condição de pagamento já existe!");
        }

        condPagNew.setStatus(0);
        return condPagRepository.saveAndFlush(condPagNew);
    }

    @Transactional
    public CondPag update(@Valid CondPag condPagNew) {
        CondPag condPag = condPagRepository.findCondPagByCodCondPag(condPagNew.getCodCondPag())
                .orElseThrow(() -> new ApiRequestException("Condição de pagamento não encontrada"));

        condPag.setDescricao(condPagNew.getDescricao());
        condPag.setCtPag(condPagNew.getCtPag());
        condPag.setCtRec(condPagNew.getCtRec());
        condPag.setStatus(condPagNew.getStatus());
        return condPagRepository.saveAndFlush(condPag);
    }

    @Transactional
    public void delete(String codCondPag) {
        checkDelete(codCondPag);
        condPagRepository.deleteById(codCondPag);
    }

    public void checkDelete(String codCondPag) {
        if (!condPagRepository.existsByCodCondPag(codCondPag)) {
            throw new ApiRequestException("Condição de pagamento não encontrada para exclusão");
        }
    }
}

