package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.exceptions.NotFoundRequestException;
import com.holis.san01.model.CondPag;
import com.holis.san01.repository.CondPagRepository;
import com.holis.san01.security.JwtToken;
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
import java.util.Optional;

import static com.holis.san01.model.local.Constantes.STATUS_ARQUIVADO;
import static com.holis.san01.model.local.Constantes.STATUS_ATIVO;

/**
 * Service para tratamento da tabela de condição de pagamento
 */
@Service
@RequiredArgsConstructor
public class CondPagService implements BaseService<CondPag, Integer, CondPag> {

    private final JwtToken jwtToken;
    private final CondPagRepository condPagRepository;

    @Override
    public Optional<CondPag> findById(Integer id) {
        return condPagRepository.findById(jwtToken.getEmpresa(), id);
    }

    @Override
    @Transactional
    public CondPag save(@Nonnull CondPag condPag) {
        if (condPagRepository.existsByCodCondPag(jwtToken.getEmpresa(), condPag.getCodCondPag()))
            throw new ApiRequestException("Este código de condição de pagamento já existe!");

        condPag.setStatus(0);
        return condPagRepository.saveAndFlush(condPag);
    }

    @Override
    @Transactional
    public CondPag update(@Nonnull CondPag condPagNew) {
        CondPag condPag = condPagRepository.findByCodCondPag(jwtToken.getEmpresa(), condPagNew.getCodCondPag())
                .orElseThrow(() -> new ApiRequestException("Condição de pagamento não encontrada"));

        condPag.setDescricao(condPagNew.getDescricao());
        condPag.setCtPag(condPagNew.getCtPag());
        condPag.setCtRec(condPagNew.getCtRec());
        condPag.setStatus(condPagNew.getStatus());
        return condPagRepository.saveAndFlush(condPag);
    }

    @Override
    public void delete(@Nonnull Integer id) {
        if (!condPagRepository.existsById(jwtToken.getEmpresa(), id))
            throw new ApiRequestException("Condição de pagamento não encontrada para exclusão");

        condPagRepository.deleteById(jwtToken.getEmpresa(), id);
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
    @Transactional
    public void archive(@Nonnull Integer id, Boolean status) {
        CondPag condPag = condPagRepository.findById(jwtToken.getEmpresa(), id)
                .orElseThrow(() -> new NotFoundRequestException("Condição não cadastrado"));

        if (status)
            condPag.setStatus(STATUS_ARQUIVADO);
        else
            condPag.setStatus(STATUS_ATIVO);

        condPagRepository.saveAndFlush(condPag);
    }
}
