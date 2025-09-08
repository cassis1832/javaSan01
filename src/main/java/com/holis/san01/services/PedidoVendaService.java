package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.model.PedVenda;
import com.holis.san01.model.PedVendaItem;
import com.holis.san01.model.VwPedVendaItem;
import com.holis.san01.repository.PedVendaItemRepository;
import com.holis.san01.repository.PedVendaRepository;
import com.holis.san01.repository.VwPedVendaItemRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service para tratamento de pedidos de vendas
 */
@Service
public class PedidoVendaService {
    @Autowired
    private PedVendaRepository pedVendaRepository;

    @Autowired
    private PedVendaItemRepository pedVendaItemRepository;

    @Autowired
    private VwPedVendaItemRepository vwPedVendaItemRepository;

    /**
     * Ler o pedido de venda pelo numero do pedido
     */
    public PedVenda lerPedVenda(final Integer nrPedido) {
        return pedVendaRepository.getPedVenda(nrPedido)
                .orElseThrow(() -> new ApiRequestException("Pedido de venda não encontrado"));
    }

    /**
     * Ler um item do pedido de venda por ID
     */
    public PedVendaItem lerPedVendaItem(final Integer id) {
        return pedVendaItemRepository.getPedVendaItem(id)
                .orElseThrow(() -> new ApiRequestException("Item do pedido não encontrado"));
    }

    public List<PedVendaItem> listarPedVendaItemByPedido(final Integer nrPedido) {
        return pedVendaItemRepository.listPedVendaItemByPedido(nrPedido);
    }

    /**
     * Listar as linhas dos pedidos por status e filtro no nome da entidade ou descrição do item
     */
    public Page<VwPedVendaItem> listarVwPedVendaItem(final boolean archive, final String filterText, final Pageable pageable) {
        if (StringUtils.isBlank(filterText)) {
            return vwPedVendaItemRepository.pageVwPedVendaItem(archive, pageable);
        } else {
            return vwPedVendaItemRepository.pageVwPedVendaItemByFilter(archive, filterText, pageable);
        }
    }

    @Transactional
    public PedVenda incluirPedVenda(final PedVenda pedVenda) {
        return new PedVenda();
    }

    @Transactional
    public PedVenda alterarPedVenda(final PedVenda pedVenda) {
        return new PedVenda();
    }

    @Transactional
    public void excluirPedVenda(Integer nrPedido) {
        pedVendaRepository.getPedVenda(nrPedido)
                .orElseThrow(() -> new ApiRequestException("Pedido de venda não encontrado"));

        //  Verifica se o registro pode ser deletado
        boolean podeDeletar = true;

        if (podeDeletar) {
            pedVendaRepository.deleteById(nrPedido);
        } else {
            throw new ApiRequestException("Não é possível excluir o pedido. Existem coisas relacionadas.");
        }
    }

    @Transactional
    public PedVendaItem incluirPedVendaItem(final PedVendaItem pedVendaItem) {
        return new PedVendaItem();
    }

    @Transactional
    public PedVendaItem alterarPedVendaItem(final PedVendaItem pedVendaItem) {
        return new PedVendaItem();
    }

    @Transactional
    public void excluirPedVendaItem(Integer id) {
    }
}