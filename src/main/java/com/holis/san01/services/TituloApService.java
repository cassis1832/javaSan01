package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.exceptions.NotFoundRequestException;
import com.holis.san01.model.*;
import com.holis.san01.repository.EntidadeRepository;
import com.holis.san01.repository.EspDocRepository;
import com.holis.san01.repository.TituloApRepository;
import com.holis.san01.repository.VwTituloApRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TituloApService {
    @Autowired
    private final TituloApRepository tituloApRepository;

    @Autowired
    private final VwTituloApRepository vwTituloApRepository;

    @Autowired
    private final EntidadeRepository entidadeRepository;

    @Autowired
    private final ParamService paramService;

    @Autowired
    private final EspDocRepository espDocRepository;

    public ApiResponse getTituloAp(Integer id) {
        TituloAp tituloAp = tituloApRepository.getTituloAp(id)
                .orElseThrow(() -> new NotFoundRequestException("Título não encontrado"));
        return new ApiResponse(true, tituloAp);
    }

    public ApiResponse listVwTituloAp(boolean archive) {
        List<VwTituloAp> vwTituloAp = vwTituloApRepository.ListVwTituloAp(archive);
        return new ApiResponse(true, vwTituloAp);
    }

    public ApiResponse listVwTituloAp(boolean archive, String codEspDoc) {
        List<VwTituloAp> vwTituloAp = vwTituloApRepository.ListVwTituloAp(archive, codEspDoc);
        return new ApiResponse(true, vwTituloAp);
    }

    public ApiResponse listVwTituloAp(String codEspDoc, Integer docId) {
        List<VwTituloAp> vwTituloAp = vwTituloApRepository.ListVwTituloAp(codEspDoc, docId);
        return new ApiResponse(true, vwTituloAp);
    }

    public ApiResponse pageVwTituloAp(final String criteria, final boolean archive, final String filterText, final Pageable pageable) {
        Page<VwTituloAp> vwTituloAp = null;

        if (criteria.equalsIgnoreCase("Todos")) {
            if (StringUtils.isBlank(filterText)) {
                vwTituloAp = vwTituloApRepository.PageVwTituloAp(archive, pageable);
            } else {
                vwTituloAp = vwTituloApRepository.PageVwTituloAp(archive, filterText, pageable);
            }
        }

        LocalDate hoje = LocalDate.now();

        if (criteria.equalsIgnoreCase("Vencidos")) {
            if (StringUtils.isBlank(filterText)) {
                vwTituloAp = vwTituloApRepository.PageVwTituloApVencidos(archive, hoje, pageable);
            } else {
                vwTituloAp = vwTituloApRepository.PageVwTituloApVencidos(archive, hoje, filterText, pageable);
            }
        }

        if (criteria.equalsIgnoreCase("A Vencer")) {
            if (StringUtils.isBlank(filterText)) {
                vwTituloAp = vwTituloApRepository.PageVwTituloApVencer(archive, hoje, pageable);
            } else {
                vwTituloAp = vwTituloApRepository.PageVwTituloApVencer(archive, hoje, filterText, pageable);
            }
        }

        return new ApiResponse(true, vwTituloAp);
    }

    /**
     * Incluir novo titulo
     */
    public ApiResponse create(TituloAp tituloAp) {
        entidadeRepository.getEntidade(tituloAp.getCodEntd())
                .orElseThrow(() -> new NotFoundRequestException("Fornecedor não encontrado"));

        if (tituloAp.getNumDoc() == null || tituloAp.getNumDoc().equals(0)) {
            Param param = paramService.getNextParam("seq_titulo_ap");
            tituloAp.setNumDoc(param.getCpoInteiro());
        }

        checkEspDoc(tituloAp.getCodEspDoc());

        tituloAp.setId(null);
        tituloAp.setDeleted(false);
        tituloAp.setDtCriacao(LocalDate.now());
        TituloAp tituloAp1 = tituloApRepository.saveAndFlush(tituloAp);
        return new ApiResponse(true, tituloAp1);
    }

    /**
     * Alterar titulo existente
     */
    public ApiResponse update(TituloAp tituloApDto) {
        TituloAp tituloAp = tituloApRepository.getTituloAp(tituloApDto.getId())
                .orElseThrow(() -> new NotFoundRequestException("Título não encontrado"));

        // Verificar o emitente do titulo
        if (!tituloApDto.getCodEntd().equals(tituloAp.getCodEntd())) {
            entidadeRepository.getEntidade(tituloApDto.getCodEntd())
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
        tituloAp.setArchive(tituloApDto.isArchive());
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

        tituloAp = tituloApRepository.saveAndFlush(tituloAp);

        return new ApiResponse(true, tituloAp);
    }

    private void checkEspDoc(String codEspDoc) {
        EspDoc espDoc = espDocRepository.getEspDoc(codEspDoc)
                .orElseThrow(() -> new NotFoundRequestException("Espécie de documento não encontrada"));
    }

    /**
     * Excluir titulo existente - soft delete
     * Titulo de despesa avulsa pode ser eliminado mesmo com pagamento (não possui
     * movto)
     */
    public ApiResponse delete(Integer id) {
        TituloAp tituloAp = tituloApRepository.getTituloAp(id)
                .orElseThrow(() -> new NotFoundRequestException("Título não encontrado"));

        if (!tituloAp.getCodEspDoc().equals("DA")) {
            if (!tituloAp.getVlPago().equals(BigDecimal.ZERO))
                throw new ApiRequestException("Título possui pagamento, exclusão não permitida!");
        }

        tituloAp.setDeleted(true);
        tituloAp.setDtAlteracao(LocalDate.now());
        //tituloAp.setUsrAlteracao(tokenUtil.getNomeUsuario());
        tituloApRepository.saveAndFlush(tituloAp);

        return new ApiResponse(true, "Exclusão efetuada com sucesso");
    }
}
