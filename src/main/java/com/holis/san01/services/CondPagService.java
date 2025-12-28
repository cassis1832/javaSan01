package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.exceptions.NotFoundRequestException;
import com.holis.san01.model.CondPag;
import com.holis.san01.repository.CondPagRepository;
import com.holis.san01.utils.SpecificationUtils;
import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service para tratamento da tabela de condição de pagamento
 */
@Service
@RequiredArgsConstructor
public class CondPagService implements BaseService<CondPag, String, CondPag> {

    private final CondPagRepository condPagRepository;

    @Override
    public CondPag findById(@Nonnull String codCondPag) {

        return condPagRepository.findById(codCondPag)
                .orElseThrow(() -> new NotFoundRequestException("Condição de pagamento não cadastrada"));
    }

    @Override
    @Transactional
    public CondPag save(@Nonnull CondPag condPag) {
        if (condPagRepository.existsById(condPag.getCodCondPag())) {
            throw new ApiRequestException("Este código de condição de pagamento já existe!");
        }

        condPag.setStatus(0);
        return condPagRepository.saveAndFlush(condPag);
    }

    @Override
    @Transactional
    public CondPag update(@Nonnull CondPag condPagNew) {
        CondPag condPag = condPagRepository.findById(condPagNew.getCodCondPag())
                .orElseThrow(() -> new ApiRequestException("Condição de pagamento não encontrada"));

        condPag.setDescricao(condPagNew.getDescricao());
        condPag.setCtPag(condPagNew.getCtPag());
        condPag.setCtRec(condPagNew.getCtRec());
        condPag.setStatus(condPagNew.getStatus());
        return condPagRepository.saveAndFlush(condPag);
    }

    @Override
    public void deleteById(@Nonnull String codCondPag) {
        checkDelete(codCondPag);
        condPagRepository.deleteById(codCondPag);
    }

    @Override
    public List<CondPag> findList(Map<String, String> filters) {
        Specification<CondPag> spec = SpecificationUtils.createSpecification(filters);
        return condPagRepository.findAll(spec);
    }

    @Override
    public Page<CondPag> findPage(Pageable pageable, Map<String, String> filtros) {
        Specification<CondPag> spec = SpecificationUtils.createSpecification(
                filtros,                                    // Map com parâmetros da requisição
                "descricao", "codCondPag"     // campos que serão usados no OR do filterText
        );
        return condPagRepository.findAll(spec, pageable);
    }

    @Override
    public void archive(@Nonnull String s) {

    }

    @Override
    public void unarchive(@Nonnull String s) {

    }

    public void checkDelete(String codCondPag) {
        if (!condPagRepository.existsById(codCondPag)) {
            throw new ApiRequestException("Condição de pagamento não encontrada para exclusão");
        }
    }
}
