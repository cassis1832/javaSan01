package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.model.PedItem;
import com.holis.san01.model.VwPedItem;
import com.holis.san01.model.local.FiltroPesquisa;
import com.holis.san01.repository.PedItemRepository;
import com.holis.san01.repository.VwPedItemRepository;
import com.holis.san01.specs.VwPedItemSpecifications;
import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service para tratamento de pedidos de vendas
 */
@Service
@RequiredArgsConstructor
public class PedItemService {

    private final PedItemRepository pedIRepository;
    private final VwPedItemRepository vwPedIRepository;
    private final ItemService itemService;

    /**
     * Ler um item do pedido de venda por ID
     */
    public PedItem findPedItemById(
            final Integer id) {

        return pedIRepository.findPedItemById(id)
                .orElseThrow(() -> new ApiRequestException("Item do pedido não encontrado"));
    }

    public List<VwPedItem> listVwPedItem(
            FiltroPesquisa filtro) {

        var spec = PreparaSpec(filtro);
        return vwPedIRepository.findAll(spec);
    }

    public Page<VwPedItem> pageVwPedItem(
            FiltroPesquisa filtro) {

        var spec = PreparaSpec(filtro);
        Sort sort = Sort.by(Sort.Direction.fromString(filtro.getSortDirection()), filtro.getSortField());
        Pageable pageable = PageRequest.of(filtro.getPageIndex(), filtro.getSize(), sort);

        return vwPedIRepository.findAll(spec, pageable);
    }

    private Specification<VwPedItem> PreparaSpec(
            @Nonnull FiltroPesquisa filtro) {

        Specification<VwPedItem> spec = Specification.where(null);

        if (filtro.getStatus() != null)
            spec = spec.and(VwPedItemSpecifications.hasStatus(filtro.getStatus()));

        if (filtro.getCodEntd() != null)
            spec = spec.and(VwPedItemSpecifications.hasCodEntd(filtro.getCodEntd()));

        if (filtro.getCodItem() != null)
            spec = spec.and(VwPedItemSpecifications.hasCodItem(filtro.getCodItem()));

        if (filtro.getNumero() != null)
            spec = spec.and(VwPedItemSpecifications.hasNrPedido(filtro.getNumero()));

        if (!StringUtils.isBlank(filtro.getFilterText()))
            spec = spec.and(VwPedItemSpecifications.hasFiltro(filtro.getFilterText()));

        return spec;
    }

    @Transactional
    public PedItem create(
            @Nonnull final PedItem pedItem) {

        itemService.findItemByCodItem(pedItem.getCodItem());
        return pedIRepository.saveAndFlush(pedItem);
    }

    @Transactional
    public PedItem update(
            final PedItem pedItem) {

        return pedIRepository.saveAndFlush(pedItem);
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
}