package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.model.PedVendaItem;
import com.holis.san01.model.VwPedVendaItem;
import com.holis.san01.repository.PedVendaItemRepository;
import com.holis.san01.repository.PedVendaRepository;
import com.holis.san01.repository.VwPedVendaItemRepository;
import com.holis.san01.repository.VwPedVendaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service para tratamento de pedidos de vendas
 */
@Service
@RequiredArgsConstructor
public class PedVendaItemService {

    private final PedVendaRepository pedVendaRepository;

    private final PedVendaItemRepository pedVendaItemRepository;

    private final VwPedVendaRepository vwPedVendaRepository;

    private final VwPedVendaItemRepository vwPedVendaItemRepository;

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
    public Page<VwPedVendaItem> listarVwPedVendaItem(
            final Integer status, final String filterText, final Pageable pageable) {
        if (StringUtils.isBlank(filterText)) {
            return vwPedVendaItemRepository.pageVwPedVendaItem(status, pageable);
        } else {
            return vwPedVendaItemRepository.pageVwPedVendaItemByFilter(status, filterText, pageable);
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