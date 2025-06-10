package com.holis.san01.services;

import com.holis.san01.exceptions.ApiDeleteException;
import com.holis.san01.exceptions.NotFoundRequestException;
import com.holis.san01.model.PedVenda;
import com.holis.san01.model.PedVendaItem;
import com.holis.san01.model.VwPedVendaItem;
import com.holis.san01.repository.PedVendaItemRepository;
import com.holis.san01.repository.PedVendaRepository;
import com.holis.san01.repository.VwPedVendaItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service para tratamento de pedidos de vendas
 */
@Service
@Transactional
@RequiredArgsConstructor
public class PedidoVendaService {

    private final PedVendaRepository pedVendaRepository;
    private final PedVendaItemRepository pedVendaItemRepository;
    private final VwPedVendaItemRepository vwPedVendaItemRepository;
    private final SequenciaService sequenciaService;

    /**
     * Ler o pedido de venda pelo numero do pedido
     * Usado para manutenção do registro
     */
    public PedVenda lerPedVenda(final Long nrPedido) {
        return pedVendaRepository.findPedVenda(nrPedido)
                .orElseThrow(() -> new NotFoundRequestException("Pedido de venda não encontrado"));
    }

    /**
     * Ler um item do pedido de venda por ID
     * Usado para manutenção do item do pedido
     */
    public PedVendaItem lerPedVendaItem(final Long id) {
        return pedVendaItemRepository.findPedVendaItem(id)
                .orElseThrow(() -> new NotFoundRequestException("Item do pedido não encontrado"));
    }

    public List<PedVendaItem> listarPedVendaItem(final Long nrPedido) {
        return pedVendaItemRepository.listPedVendaItem(nrPedido);
    }

    /**
     * Listar as linhas dos pedidos por status e filtro no nome da entidade ou descrição do item
     */
    public Page<VwPedVendaItem> listarVwPedVendaItem(final String archive, final String filterText, final Pageable pageable) {
        if (StringUtils.isBlank(filterText)) {
            return vwPedVendaItemRepository.listVwPedVendaItem(archive, pageable);
        } else {
            return vwPedVendaItemRepository.listVwPedVendaItem(archive, filterText, pageable);
        }
    }

    public PedVenda incluirPedVenda(final PedVenda pedVenda) {
        return new PedVenda();
    }

    public PedVenda alterarPedVenda(final PedVenda pedVenda) {
        return new PedVenda();
    }

    public void excluirPedVenda(Long nrPedido) {
        pedVendaRepository.findPedVenda(nrPedido)
                .orElseThrow(() -> new NotFoundRequestException("Pedido de venda não encontrado"));

        //  Verifica se o registro pode ser deletado
        boolean podeDeletar = true;

        if (podeDeletar) {
            pedVendaRepository.deleteById(nrPedido);
        } else {
            throw new ApiDeleteException("Não é possível excluir o pedido. Existem coisas relacionadas.");
        }
    }

    public Object obterProximoCodigo() {
        Long codigo = sequenciaService.proximoNumero("nr_pedido_venda");

        while (true) {
            Optional<PedVenda> opt = pedVendaRepository.findPedVenda(codigo);

            if (opt.isEmpty()) {
                return codigo;
            }
            codigo++;
        }
    }

    public PedVendaItem incluirPedVendaItem(final PedVendaItem pedVendaItem) {
        return new PedVendaItem();
    }

    public PedVendaItem alterarPedVendaItem(final PedVendaItem pedVendaItem) {
        return new PedVendaItem();
    }

    public void excluirPedVendaItem(Integer id) {
    }


    //---------------------------------------------------------
// por status/tipo (abertos,fechados,todos)/ codEntd / codItem
//
//    public Page<VwPedVendaItem> ListarVwPedVendaItem(
//            Integer codEntd,
//            String codItem,
//            String status,
//            String tipo,
//            Pageable pageable,
//            String filterText) {
//        if (StringUtils.isBlank(filterText)) {
//            return vwPedvRepository.findPedVendas(tokenUtil.getEmpresaId(), tpPedido, arquivado, pageable);
//        } else {
//            return vwPedvRepository.findPedVendas(tokenUtil.getEmpresaId(), tpPedido, arquivado, filterText, pageable);
//        }
//    }
}