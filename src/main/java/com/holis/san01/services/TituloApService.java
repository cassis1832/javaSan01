package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.exceptions.NotFoundRequestException;
import com.holis.san01.model.TituloAp;
import com.holis.san01.model.VwTituloAp;
import com.holis.san01.repository.EntidadeRepository;
import com.holis.san01.repository.EspDocRepository;
import com.holis.san01.repository.TituloApRepository;
import com.holis.san01.repository.VwTituloApRepository;
import com.holis.san01.utils.SpecificationUtils;
import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.holis.san01.utils.Constantes.STATUS_ATIVO;
import static com.holis.san01.utils.Constantes.STATUS_DELETADO;

@Service
@RequiredArgsConstructor
public class TituloApService implements BaseService<TituloAp, Integer, VwTituloAp> {

    private final TituloApRepository tituloApRepository;
    private final VwTituloApRepository vwTituloApRepository;
    private final EntidadeRepository entidadeRepository;
    private final EspDocRepository espDocRepository;
    private final ParamService paramService;

    @Override
    public TituloAp findById(@Nonnull Integer id) {
        return tituloApRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException("Título não cadastrado"));

    }

    @Override
    @Transactional
    public TituloAp create(@Nonnull TituloAp tituloAp) {
        if (tituloApRepository.existsById(tituloAp.getId())) {
            throw new ApiRequestException("Este id já existe!");
        }

        entidadeRepository.findById(tituloAp.getCodEntd())
                .orElseThrow(() -> new NotFoundRequestException("Fornecedor não encontrado"));

        if (tituloAp.getNumDoc() == null || tituloAp.getNumDoc().equals(0)) {
            var numDoc = 0;

            do {
                numDoc = paramService.getNextSequence("seq_titulo_ap");
            } while (tituloApRepository.existsByNumDoc(numDoc));

            tituloAp.setNumDoc(numDoc);
        }

        checkEspDoc(tituloAp.getCodEspDoc());

        tituloAp.setId(null);
        tituloAp.setStatus(STATUS_ATIVO);
        tituloAp.setDtCriacao(Instant.now());
        return tituloApRepository.saveAndFlush(tituloAp);
    }

    @Override
    @Transactional
    public TituloAp update(@Nonnull TituloAp novoTitulo) {
        TituloAp tituloAp = tituloApRepository.findById(novoTitulo.getId())
                .orElseThrow(() -> new NotFoundRequestException("Título não encontrado"));

        // Verificar o emitente do titulo
        if (!novoTitulo.getCodEntd().equals(tituloAp.getCodEntd())) {
            entidadeRepository.findById(novoTitulo.getCodEntd())
                    .orElseThrow(() -> new NotFoundRequestException("Fornecedor não encontrado"));
        }

        if ((!tituloAp.getVlTitulo().equals(novoTitulo.getVlTitulo())) &&
                (tituloAp.getVlTitulo().equals(BigDecimal.ZERO))) {
            tituloAp.setVlOriginal(novoTitulo.getVlTitulo());
        }

        checkEspDoc(tituloAp.getCodEspDoc());

        tituloAp.setNumDoc(novoTitulo.getNumDoc());
        tituloAp.setVlTitulo(novoTitulo.getVlTitulo());
        tituloAp.setDescricao(novoTitulo.getDescricao());
        tituloAp.setStatus(novoTitulo.getStatus());
        tituloAp.setDtLiquidac(novoTitulo.getDtLiquidac());
        tituloAp.setDtPrevPag(novoTitulo.getDtPrevPag());
        tituloAp.setDtVencto(novoTitulo.getDtVencto());
        tituloAp.setCodEntd(novoTitulo.getCodEntd());
        tituloAp.setDtVenctoOrigin(novoTitulo.getDtVenctoOrigin());
        tituloAp.setCodEspDoc(novoTitulo.getCodEspDoc());
        tituloAp.setObservacao(novoTitulo.getObservacao());
        tituloAp.setParcelas(novoTitulo.getParcelas());
        tituloAp.setParcela(novoTitulo.getParcela());
        tituloAp.setDocId(novoTitulo.getDocId());
        tituloAp.setSituacao(novoTitulo.getSituacao());
        tituloAp.setTipoFinId(novoTitulo.getTipoFinId());
        tituloAp.setVlPago(novoTitulo.getVlPago());
        tituloAp.setDtAlteracao(Instant.now());

        if (tituloAp.getVlTitulo().compareTo(tituloAp.getVlPago()) >= 0) {
            tituloAp.setVlSaldo(tituloAp.getVlTitulo().subtract(tituloAp.getVlPago()));
        } else {
            tituloAp.setVlSaldo(BigDecimal.ZERO);
        }
        return tituloApRepository.saveAndFlush(tituloAp);
    }

    @Override
    public void delete(@Nonnull Integer id) {
        TituloAp tituloAp = tituloApRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException("Título não encontrado"));

        if (!tituloAp.getCodEspDoc().equals("DA")) {
            if (!tituloAp.getVlPago().equals(BigDecimal.ZERO))
                throw new ApiRequestException("Título possui pagamento, exclusão não permitida!");
        }

        tituloAp.setStatus(STATUS_DELETADO);
        tituloAp.setDtDeleted(LocalDate.now());
        tituloApRepository.saveAndFlush(tituloAp);
    }

    @Override
    public List<VwTituloAp> findAll(Map<String, String> filters) {
        Specification<VwTituloAp> spec = SpecificationUtils.createSpecification(filters);
        return vwTituloApRepository.findAll(spec);
    }

    @Override
    public Page<VwTituloAp> findPage(Pageable pageable, Map<String, String> filtros) {
        Specification<VwTituloAp> spec = SpecificationUtils.createSpecification(
                filtros,                                    // Map com parâmetros da requisição
                "nome", "descricao"     // campos que serão usados no OR do filterText
        );
        return vwTituloApRepository.findAll(spec, pageable);
    }

    @Override
    public void arquivar(@Nonnull Integer integer) {

    }

    @Override
    public void desarquivar(@Nonnull Integer integer) {

    }

    private void checkEspDoc(@NotNull String codEspDoc) {
        espDocRepository.findById(codEspDoc)
                .orElseThrow(() -> new NotFoundRequestException("Espécie de documento não encontrada"));
    }
}
