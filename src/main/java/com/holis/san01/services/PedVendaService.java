package com.holis.san01.services;

import com.holis.san01.enums.SituacaoPedidoEnum;
import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.exceptions.NotFoundRequestException;
import com.holis.san01.model.PedVenda;
import com.holis.san01.model.VwPedVenda;
import com.holis.san01.repository.EntidadeRepository;
import com.holis.san01.repository.PedVendaRepository;
import com.holis.san01.repository.VwPedVendaRepository;
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

import static com.holis.san01.model.local.Constantes.*;

/**
 * Service para tratamento de pedidos de vendas
 */
@Service
@RequiredArgsConstructor
public class PedVendaService implements BaseService<PedVenda, Integer, VwPedVenda> {
    private final JwtToken jwtToken;
    private final ParamService paramService;
    private final PedVendaRepository pedVRepository;
    private final EntidadeRepository entidadeRepository;
    private final VwPedVendaRepository vwPedVRepository;

    @Override
    public Optional<PedVenda> findById(final Integer id) {
        return pedVRepository.findById(jwtToken.getEmpresa(), id);
    }

    @Override
    @Transactional
    public PedVenda save(@Nonnull PedVenda pedVenda) {
        entidadeRepository.findById(pedVenda.getCodEntd());

        if (pedVenda.getNrPedido() == null || pedVenda.getNrPedido() == 0) {
            var nrPedido = 0;

            do {
                nrPedido = paramService.getNextSequence("seq_ped_venda");
            } while (pedVRepository.existsByNrPedido(jwtToken.getEmpresa(), nrPedido));

            pedVenda.setNrPedido(nrPedido);
        } else {
            if (pedVRepository.existsByNrPedido(jwtToken.getEmpresa(), pedVenda.getNrPedido())) {
                throw new NotFoundRequestException("Número de pedido de venda já existe");
            }
        }

        pedVenda.setTpPedido(ORCAMENTO);
        pedVenda.setSituacao(SituacaoPedidoEnum.ABERTO);
        pedVenda.setStatus(STATUS_ATIVO);
        return pedVRepository.saveAndFlush(pedVenda);
    }

    @Override
    @Transactional
    public PedVenda update(@Nonnull PedVenda pedVendaInput) {
        PedVenda pedVenda = pedVRepository.findById(pedVendaInput.getNrPedido())
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
        pedVenda.setIndAprov(pedVendaInput.getIndAprov());
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
        pedVenda.setLocalidade(pedVendaInput.getLocalidade());
        pedVenda.setCep(pedVendaInput.getCep());
        pedVenda.setEstado(pedVendaInput.getEstado());
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
        pedVenda.setIndFatPar(pedVendaInput.getIndFatPar());
        pedVenda.setVlLiqPed(pedVendaInput.getVlLiqPed());
        pedVenda.setVlLiqAbe(pedVendaInput.getVlLiqAbe());
        pedVenda.setIndAntecip(pedVendaInput.getIndAntecip());
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
        pedVenda.setIncDescTxt(pedVendaInput.getIncDescTxt());
        pedVenda.setDtBaseFt(pedVendaInput.getDtBaseFt());
        pedVenda.setIndEntCompleta(pedVendaInput.getIndEntCompleta());
        pedVenda.setCompleto(pedVendaInput.getCompleto());
        pedVenda.setVlDescontoTotal(pedVendaInput.getVlDescontoTotal());
        pedVenda.setDescValorPed(pedVendaInput.getDescValorPed());
        pedVenda.setTipoFinId(pedVendaInput.getTipoFinId());
        pedVenda.setCondPag(pedVendaInput.getCondPag());
        pedVenda.setDtSituacao(pedVendaInput.getDtSituacao());
        pedVenda.setStatus(pedVendaInput.getStatus());
        return pedVRepository.saveAndFlush(pedVenda);
    }

    @Override
    @Transactional
    public void delete(@Nonnull Integer id) {
        if (!pedVRepository.existsById(jwtToken.getEmpresa(), id))
            throw new ApiRequestException("Pedido de venda não encontrado");
        pedVRepository.deleteById(jwtToken.getEmpresa(), id);
    }

    @Override
    public List<PedVenda> findList(Map<String, String> filters) {
        Specification<PedVenda> spec = SpecificationUtils.createSpecification(filters);
        return pedVRepository.findAll(spec);
    }

    @Override
    public Page<VwPedVenda> findPage(Pageable pageable, Map<String, String> filtros) {
        Specification<VwPedVenda> spec = SpecificationUtils.createSpecification(
                filtros,                                    // Map com parâmetros da requisição
                "descricao", "nome"     // campos que serão usados no OR do filterText
        );
        return vwPedVRepository.findAll(spec, pageable);
    }

    @Transactional
    public PedVenda confirm(Integer nrPedido) {
        PedVenda pedVenda = pedVRepository.findById(nrPedido).orElseThrow(() -> new ApiRequestException("Pedido de venda não encontrado"));

        if (pedVenda.getTpPedido() == PEDIDO) throw new ApiRequestException("Registro já é um pedido de venda");

        pedVenda.setTpPedido(PEDIDO);
        pedVenda.setSituacao(SituacaoPedidoEnum.ABERTO);
        pedVenda.setStatus(STATUS_ATIVO);

        return pedVRepository.saveAndFlush(pedVenda);
    }

    @Override
    @Transactional
    public void archive(@Nonnull Integer id, Boolean status) {
        PedVenda pedVenda = pedVRepository.findById(jwtToken.getEmpresa(), id)
                .orElseThrow(() -> new NotFoundRequestException("Item não cadastrado"));

        if (status)
            pedVenda.setStatus(STATUS_ARQUIVADO);
        else
            pedVenda.setStatus(STATUS_ATIVO);

        pedVRepository.saveAndFlush(pedVenda);
    }

    @Transactional
    public PedVenda cancel(Integer nrPedido) {
        PedVenda pedVenda = pedVRepository.findById(nrPedido).orElseThrow(() -> new ApiRequestException("Pedido de venda não encontrado"));

        if (pedVenda.getTpPedido()) throw new ApiRequestException("Registro já é um pedido de venda");

        pedVenda.setSituacao(SituacaoPedidoEnum.CANCELADO);
        pedVenda.setStatus(STATUS_ARQUIVADO);
        return pedVRepository.saveAndFlush(pedVenda);
    }
}
