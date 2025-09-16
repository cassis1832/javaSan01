package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.exceptions.NotFoundRequestException;
import com.holis.san01.model.TituloAp;
import com.holis.san01.model.VwTituloAp;
import com.holis.san01.repository.EntidadeRepository;
import com.holis.san01.repository.EspDocRepository;
import com.holis.san01.repository.TituloApRepository;
import com.holis.san01.repository.VwTituloApRepository;
import com.holis.san01.specs.VwTituloApSpecifications;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.holis.san01.model.Constantes.STATUS_ATIVO;
import static com.holis.san01.model.Constantes.STATUS_DELETADO;

@Service
@Transactional
@RequiredArgsConstructor
public class TituloApService {

    private final TituloApRepository tituloApRepository;
    private final VwTituloApRepository vwTituloApRepository;
    private final EntidadeRepository entidadeRepository;
    private final EspDocRepository espDocRepository;
    private final ParamService paramService;

    public TituloAp findTituloAp(int id) {

        return tituloApRepository.findTituloApById(id)
                .orElseThrow(() -> new NotFoundRequestException("Título não encontrado"));
    }

    public List<VwTituloAp> listVwTituloAp(
            final Integer status, final Integer codEntd,
            final String codEspDoc, final Integer docId,
            final String vencto, final String filterText) {

        Specification<VwTituloAp> spec = prepareSpec(status, codEntd, codEspDoc, docId, vencto, filterText);
        return vwTituloApRepository.findAll(spec);
    }

    public Page<VwTituloAp> pageVwTituloAp(
            final Integer status, final Integer codEntd,
            final String codEspDoc, final Integer docId,
            final String vencto, final String filterText,
            final Pageable pageable) {

        Specification<VwTituloAp> spec = prepareSpec(status, codEntd, codEspDoc, docId, vencto, filterText);
        return vwTituloApRepository.findAll(spec, pageable);
    }

    private Specification<VwTituloAp> prepareSpec(
            final Integer status, final Integer codEntd,
            final String codEspDoc, final Integer docId,
            final String vencto, final String filterText) {

        Specification<VwTituloAp> spec = Specification.where(null);

        if (status != null)
            spec = spec.and(VwTituloApSpecifications.hasStatus(status));

        if (codEntd != null)
            spec = spec.and(VwTituloApSpecifications.hasCodEntd(codEntd));

        if (codEspDoc != null)
            spec = spec.and(VwTituloApSpecifications.hasCodEspDoc(codEspDoc));

        if (docId != null)
            spec = spec.and(VwTituloApSpecifications.hashasDocId(docId));

        if (!StringUtils.isBlank(filterText))
            spec = spec.and(VwTituloApSpecifications.hasFiltro(filterText));

        if (vencto.equalsIgnoreCase("vencidos"))
            spec = spec.and(VwTituloApSpecifications.vencidos());

        if (vencto.equalsIgnoreCase("avencer"))
            spec = spec.and(VwTituloApSpecifications.aVencer());

        return spec;
    }

    public TituloAp create(@NotNull TituloAp tituloAp) {

        entidadeRepository.findEntidadeByCodEntd(tituloAp.getCodEntd())
                .orElseThrow(() -> new NotFoundRequestException("Fornecedor não encontrado"));

        if (tituloAp.getNumDoc() == null || tituloAp.getNumDoc().equals(0)) {
            tituloAp.setNumDoc(paramService.getNextSequence("seq_titulo_ap"));
        }

        checkEspDoc(tituloAp.getCodEspDoc());

        tituloAp.setId(null);
        tituloAp.setStatus(STATUS_ATIVO);
        tituloAp.setDtCriacao(LocalDate.now());
        return tituloApRepository.saveAndFlush(tituloAp);
    }

    /**
     * Alterar titulo existente
     */
    public TituloAp update(@NotNull TituloAp tituloApDto) {

        TituloAp tituloAp = tituloApRepository.findTituloApById(tituloApDto.getId())
                .orElseThrow(() -> new NotFoundRequestException("Título não encontrado"));

        // Verificar o emitente do titulo
        if (!tituloApDto.getCodEntd().equals(tituloAp.getCodEntd())) {
            entidadeRepository.findEntidadeByCodEntd(tituloApDto.getCodEntd())
                    .orElseThrow(() -> new NotFoundRequestException("Fornecedor não encontrado"));
        }

        if ((!tituloAp.getVlTitulo().equals(tituloApDto.getVlTitulo())) &&
                (tituloAp.getVlTitulo().equals(BigDecimal.ZERO))) {
            tituloAp.setVlOriginal(tituloApDto.getVlTitulo());
        }

        checkEspDoc(tituloAp.getCodEspDoc());

        tituloAp.setNumDoc(tituloApDto.getNumDoc());
        tituloAp.setVlTitulo(tituloApDto.getVlTitulo());
        tituloAp.setDescricao(tituloApDto.getDescricao());
        tituloAp.setStatus(tituloApDto.getStatus());
        tituloAp.setDtLiquidac(tituloApDto.getDtLiquidac());
        tituloAp.setDtPrevPag(tituloApDto.getDtPrevPag());
        tituloAp.setDtVencto(tituloApDto.getDtVencto());
        tituloAp.setCodEntd(tituloApDto.getCodEntd());
        tituloAp.setDtVenctoOrigin(tituloApDto.getDtVenctoOrigin());
        tituloAp.setCodEspDoc(tituloApDto.getCodEspDoc());
        tituloAp.setObservacao(tituloApDto.getObservacao());
        tituloAp.setParcelas(tituloApDto.getParcelas());
        tituloAp.setParcela(tituloApDto.getParcela());
        tituloAp.setDocId(tituloApDto.getDocId());
        tituloAp.setSituacao(tituloApDto.getSituacao());
        tituloAp.setTipoFinId(tituloApDto.getTipoFinId());
        tituloAp.setVlPago(tituloApDto.getVlPago());
        tituloAp.setDtAlteracao(LocalDate.now());

        if (tituloAp.getVlTitulo().compareTo(tituloAp.getVlPago()) >= 0) {
            tituloAp.setVlSaldo(tituloAp.getVlTitulo().subtract(tituloAp.getVlPago()));
        } else {
            tituloAp.setVlSaldo(BigDecimal.ZERO);
        }

        return tituloApRepository.saveAndFlush(tituloAp);
    }

    private void checkEspDoc(@NotNull String codEspDoc) {

        espDocRepository.findEspDocByCodEspDoc(codEspDoc)
                .orElseThrow(() -> new NotFoundRequestException("Espécie de documento não encontrada"));
    }

    /**
     * Excluir titulo existente - soft delete
     * Titulo de despesa avulsa pode ser eliminado mesmo com pagamento (não possui
     * movto)
     */
    public void delete(int id) {

        TituloAp tituloAp = tituloApRepository.findTituloApById(id)
                .orElseThrow(() -> new NotFoundRequestException("Título não encontrado"));

        if (!tituloAp.getCodEspDoc().equals("DA")) {
            if (!tituloAp.getVlPago().equals(BigDecimal.ZERO))
                throw new ApiRequestException("Título possui pagamento, exclusão não permitida!");
        }

        tituloAp.setStatus(STATUS_DELETADO);
        tituloAp.setDtDeleted(LocalDate.now());
        tituloApRepository.saveAndFlush(tituloAp);
    }
}
