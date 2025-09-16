package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.model.PedVendaItem;
import com.holis.san01.model.VwPedVendaItem;
import com.holis.san01.repository.PedVendaItemRepository;
import com.holis.san01.repository.VwPedVendaItemRepository;
import com.holis.san01.specs.VwPedVendaItemSpecifications;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    public PedVendaItem findPedVendaItemById(final Integer id) {

        return pedIRepository.findPedVendaItemById(id)
                .orElseThrow(() -> new ApiRequestException("Item do pedido não encontrado"));
    }

    public List<VwPedVendaItem> listVwPedVendaItem(
            final Integer status, final Integer codEntd, final Integer nrPedido,
            final String codItem, final String filterText) {

        Specification<VwPedVendaItem> spec = prepareSpec(status, codEntd, nrPedido, codItem, filterText);
        return vwPedIRepository.findAll(spec);
    }

    public Page<VwPedVendaItem> pageVwPedVendaItem(
            final Integer status, final Integer codEntd, final Integer nrPedido,
            final String codItem, final String filterText, final Pageable pageable) {

        Specification<VwPedVendaItem> spec = prepareSpec(status, codEntd, nrPedido, codItem, filterText);
        return vwPedIRepository.findAll(spec, pageable);
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
    public void delete(int id) {

        checkDelete(id);
        pedIRepository.deleteById(id);
    }

    public void checkDelete(int id) {

        if (!pedIRepository.existsById(id))
            throw new ApiRequestException("Item do pedido de venda não encontrado");
    }

    private Specification<VwPedVendaItem> prepareSpec(
            final Integer status, final Integer codEntd, final Integer nrPedido,
            final String codItem, final String filterText) {

        Specification<VwPedVendaItem> spec = Specification.where(null);

        if (status != null)
            spec = spec.and(VwPedVendaItemSpecifications.hasStatus(status));

        if (codEntd != null)
            spec = spec.and(VwPedVendaItemSpecifications.hasCodEntd(codEntd));

        if (nrPedido != null)
            spec = spec.and(VwPedVendaItemSpecifications.hasNrPedido(nrPedido));

        if (codItem != null)
            spec = spec.and(VwPedVendaItemSpecifications.hasCodItem(codItem));

        if (!StringUtils.isBlank(filterText))
            spec = spec.and(VwPedVendaItemSpecifications.hasFiltro(filterText));

        return spec;
    }
}