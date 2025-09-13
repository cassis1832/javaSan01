package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.model.PedVendaItem;
import com.holis.san01.model.VwPedVendaItem;
import com.holis.san01.repository.PedVendaItemRepository;
import com.holis.san01.repository.VwPedVendaItemRepository;
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

    private final PedVendaItemRepository pedIRepository;
    private final VwPedVendaItemRepository vwPedIRepository;
    private final ItemService itemService;

    /**
     * Ler um item do pedido de venda por ID
     */
    public PedVendaItem getPedVendaItem(final Integer id) {

        return pedIRepository.findPedVendaItemById(id)
                .orElseThrow(() -> new ApiRequestException("Item do pedido não encontrado"));
    }

    public List<PedVendaItem> listPedVendaItem(final Integer nrPedido) {

        return pedIRepository.listPedVendaItemByPedido(nrPedido);
    }

    /**
     * Listar as linhas dos pedidos por status e filtro no nome da entidade ou descrição do item
     */
    public Page<VwPedVendaItem> pageVwPedVendaItem(
            final Integer status, final String filterText, final Pageable pageable) {

        if (StringUtils.isBlank(filterText)) {
            return vwPedIRepository.pageVwPedVendaItem(status, pageable);
        } else {
            return vwPedIRepository.pageVwPedVendaItemByFilter(status, filterText, pageable);
        }
    }

    @Transactional
    public PedVendaItem create(final PedVendaItem pedVendaItem) {

        itemService.findItemByCodItem(pedVendaItem.getCodItem());

        return pedIRepository.saveAndFlush(pedVendaItem);
    }

    @Transactional
    public PedVendaItem update(final PedVendaItem pedVendaItem) {

        return pedIRepository.saveAndFlush(pedVendaItem);
    }

    @Transactional
    public void delete(Integer id) {

        pedIRepository.deleteById(id);
    }
}