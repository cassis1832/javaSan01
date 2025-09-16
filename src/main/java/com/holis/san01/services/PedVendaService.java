package com.holis.san01.services;

import com.holis.san01.enums.SituacaoPedidoEnum;
import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.exceptions.NotFoundRequestException;
import com.holis.san01.model.PedVenda;
import com.holis.san01.model.VwPedVenda;
import com.holis.san01.repository.PedVendaRepository;
import com.holis.san01.repository.VwPedVendaRepository;
import com.holis.san01.specs.VwPedVendaSpecifications;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static com.holis.san01.model.Constantes.*;

/**
 * Service para tratamento de pedidos de vendas
 */
@Service
@RequiredArgsConstructor
public class PedVendaService {

    private final EntidadeService entidadeService;
    private final ParamService paramService;

    private final PedVendaRepository pedVRepository;
    private final VwPedVendaRepository vwPedVRepository;

    /**
     * Ler o pedido de venda pelo numero do pedido
     */
    public PedVenda findPedVendaByNrPedido(final int nrPedido) {

        return pedVRepository.findPedVendaByNrPedido(nrPedido)
                .orElseThrow(() -> new NotFoundRequestException("Pedido de venda não encontrado"));
    }

    public Page<VwPedVenda> pageVwPedVenda(
            final Integer status, final String filterText, final Pageable pageable) {

        Specification<VwPedVenda> spec = Specification.where(null);

        if (status != null)
            spec = spec.and(VwPedVendaSpecifications.hasStatus(status));

        if (!StringUtils.isBlank(filterText))
            spec = spec.and(VwPedVendaSpecifications.hasFiltro(filterText));

        return vwPedVRepository.findAll(spec, pageable);
    }

    @Transactional
    public PedVenda create(@NotNull PedVenda pedVenda) {

        entidadeService.getEntidade(pedVenda.getCodEntd());

        if (pedVenda.getNrPedido() == null || pedVenda.getNrPedido() == 0) {
            var nrPedido = 0;

            do {
                nrPedido = paramService.getNextSequence("seq_ped_venda");

            } while (pedVRepository.existsByNrPedido(nrPedido));

            pedVenda.setNrPedido(nrPedido);
        } else {
            if (pedVRepository.existsByNrPedido(pedVenda.getNrPedido())) {
                throw new NotFoundRequestException("Número de pedido de venda já existe");
            }
        }

        pedVenda.setSituacao(SituacaoPedidoEnum.ABERTO);
        pedVenda.setStatus(STATUS_ATIVO);

        return pedVRepository.saveAndFlush(pedVenda);
    }

    @Transactional
    public PedVenda update(@NotNull PedVenda pedVendaInput) {

        PedVenda pedVenda = pedVRepository.findPedVendaByNrPedido(pedVendaInput.getNrPedido())
                .orElseThrow(() -> new NotFoundRequestException("Pedido de venda não encontrado"));

        pedVenda.setDescricao(pedVendaInput.getDescricao());
        pedVenda.setNrContrato(pedVendaInput.getNrContrato());
        pedVenda.setNrPedcli(pedVendaInput.getNrPedcli());
        pedVenda.setPercDesconto(pedVendaInput.getPercDesconto());
        pedVenda.setVlDesconto(pedVendaInput.getVlDesconto());
        pedVenda.setVlFrete(pedVendaInput.getVlFrete());
        pedVenda.setVlSeguro(pedVendaInput.getVlSeguro());
        pedVenda.setVlEmbal(pedVendaInput.getVlEmbal());
        pedVenda.setVlTotPed(pedVendaInput.getVlTotPed());
        pedVenda.setVlTotItens(pedVendaInput.getVlTotItens());
        pedVenda.setCodCondPag(pedVendaInput.getCodCondPag());
        pedVenda.setParcelas(pedVendaInput.getParcelas());
        pedVenda.setPrazoEntrega(pedVendaInput.getPrazoEntrega());
        pedVenda.setNatOperacao(pedVendaInput.getNatOperacao());
        pedVenda.setContato(pedVendaInput.getContato());
        pedVenda.setTelefone(pedVendaInput.getTelefone());
        pedVenda.setEmail(pedVendaInput.getEmail());
        pedVenda.setObservacao(pedVendaInput.getObservacao());
        pedVenda.setDtEmissao(pedVendaInput.getDtEmissao());
        pedVenda.setDtEntrega(pedVendaInput.getDtEntrega());
        pedVenda.setIndAprov(pedVendaInput.isIndAprov());
        pedVenda.setDtAprovacao(pedVendaInput.getDtAprovacao());
        pedVenda.setDtValCot(pedVendaInput.getDtValCot());
        pedVenda.setPrazoValCot(pedVendaInput.getPrazoValCot());
        pedVenda.setDtMinfat(pedVendaInput.getDtMinfat());
        pedVenda.setDtLimFat(pedVendaInput.getDtLimFat());
        pedVenda.setDtDevolucao(pedVendaInput.getDtDevolucao());
        pedVenda.setDtSuspensao(pedVendaInput.getDtSuspensao());
        pedVenda.setCodPriori(pedVendaInput.getCodPriori());
        pedVenda.setLogradouro(pedVendaInput.getLogradouro());
        pedVenda.setNumero(pedVendaInput.getNumero());
        pedVenda.setComplemento(pedVendaInput.getComplemento());
        pedVenda.setBairro(pedVendaInput.getBairro());
        pedVenda.setEstado(pedVendaInput.getEstado());
        pedVenda.setCep(pedVendaInput.getCep());
        pedVenda.setPais(pedVendaInput.getPais());
        pedVenda.setCgc(pedVendaInput.getCgc());
        pedVenda.setInsEstadual(pedVendaInput.getInsEstadual());
        pedVenda.setPercDesco2(pedVendaInput.getPercDesco2());
        pedVenda.setCondRedespa(pedVendaInput.getCondRedespa());
        pedVenda.setCidadeCif(pedVendaInput.getCidadeCif());
        pedVenda.setCodPortador(pedVendaInput.getCodPortador());
        pedVenda.setModalidade(pedVendaInput.getModalidade());
        pedVenda.setCodMensagem(pedVendaInput.getCodMensagem());
        pedVenda.setCondEspec(pedVendaInput.getCondEspec());
        pedVenda.setCodDesMerc(pedVendaInput.getCodDesMerc());
        pedVenda.setNomeTransp(pedVendaInput.getNomeTransp());
        pedVenda.setTpPreco(pedVendaInput.getTpPreco());
        pedVenda.setIndFatPar(pedVendaInput.isIndFatPar());
        pedVenda.setVlLiqPed(pedVendaInput.getVlLiqPed());
        pedVenda.setVlLiqAbe(pedVendaInput.getVlLiqAbe());
        pedVenda.setIndAntecip(pedVendaInput.isIndAntecip());
        pedVenda.setDistancia(pedVendaInput.getDistancia());
        pedVenda.setVlMerAbe(pedVendaInput.getVlMerAbe());
        pedVenda.setDescSuspend(pedVendaInput.getDescSuspend());
        pedVenda.setDescBloqCr(pedVendaInput.getDescBloqCr());
        pedVenda.setDescForcCr(pedVendaInput.getDescForcCr());
        pedVenda.setNomeTrRed(pedVendaInput.getNomeTrRed());
        pedVenda.setTipCobDesp(pedVendaInput.getTipCobDesp());
        pedVenda.setCodSitPre(pedVendaInput.getCodSitPre());
        pedVenda.setPerDesIcms(pedVendaInput.getPerDesIcms());
        pedVenda.setVlCredLib(pedVendaInput.getVlCredLib());
        pedVenda.setIncDescTxt(pedVendaInput.isIncDescTxt());
        pedVenda.setDtBaseFt(pedVendaInput.getDtBaseFt());
        pedVenda.setIndEntCompleta(pedVendaInput.isIndEntCompleta());
        pedVenda.setCompleto(pedVendaInput.isCompleto());
        pedVenda.setVlDescontoTotal(pedVendaInput.getVlDescontoTotal());
        pedVenda.setDescValorPed(pedVendaInput.getDescValorPed());
        pedVenda.setTipoFinId(pedVendaInput.getTipoFinId());
        pedVenda.setCondPag(pedVendaInput.getCondPag());
        pedVenda.setDtSituacao(pedVendaInput.getDtSituacao());
        pedVenda.setStatus(pedVendaInput.getStatus());

        return pedVRepository.saveAndFlush(pedVenda);
    }

    @Transactional
    public void delete(int nrPedido) {

        checkDelete(nrPedido);
        pedVRepository.deleteById(nrPedido);
    }

    /**
     * Verificar se o item pode ser deletado
     */
    public void checkDelete(int nrPedido) {

        if (!pedVRepository.existsByNrPedido(nrPedido))
            throw new ApiRequestException("Pedido de venda não encontrado");
    }

    public PedVenda confirm(int nrPedido) {

        PedVenda pedVenda = pedVRepository.findPedVendaByNrPedido(nrPedido)
                .orElseThrow(() -> new ApiRequestException("Pedido de venda não encontrado"));

        if (pedVenda.isTpPedido() == PEDIDO)
            throw new ApiRequestException("Registro já é um pedido de venda");

        pedVenda.setTpPedido(PEDIDO);
        pedVenda.setSituacao(SituacaoPedidoEnum.ABERTO);
        pedVenda.setStatus(STATUS_ATIVO);

        return pedVRepository.saveAndFlush(pedVenda);
    }

    public void archive(int nrPedido) {

        PedVenda pedVenda = pedVRepository.findPedVendaByNrPedido(nrPedido)
                .orElseThrow(() -> new ApiRequestException("Pedido de venda não encontrado"));

        pedVenda.setStatus(STATUS_ARQUIVADO);
    }

    public void unarchive(int nrPedido) {

        PedVenda pedVenda = pedVRepository.findPedVendaByNrPedido(nrPedido)
                .orElseThrow(() -> new ApiRequestException("Pedido de venda não encontrado"));

        pedVenda.setStatus(STATUS_ATIVO);
    }

    public PedVenda cancel(int nrPedido) {

        PedVenda pedVenda = pedVRepository.findPedVendaByNrPedido(nrPedido)
                .orElseThrow(() -> new ApiRequestException("Pedido de venda não encontrado"));

        if (pedVenda.isTpPedido())
            throw new ApiRequestException("Registro já é um pedido de venda");

        pedVenda.setSituacao(SituacaoPedidoEnum.CANCELADO);
        pedVenda.setStatus(STATUS_ARQUIVADO);

        return pedVRepository.saveAndFlush(pedVenda);
    }
}