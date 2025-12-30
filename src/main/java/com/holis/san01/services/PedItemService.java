package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.model.PedItem;
import com.holis.san01.model.VwPedItem;
import com.holis.san01.repository.ItemRepository;
import com.holis.san01.repository.PedItemRepository;
import com.holis.san01.repository.VwPedItemRepository;
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

/**
 * Service para tratamento de pedidos de vendas
 */
@Service
@RequiredArgsConstructor
public class PedItemService implements BaseService<PedItem, Integer, VwPedItem> {

    private final PedItemRepository pedItemRepository;
    private final VwPedItemRepository vwPedItemRepository;
    private final ItemRepository itemRepository;

    @Override
    public PedItem findById(@Nonnull final Integer id) {
        return pedItemRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Item do pedido não encontrado"));
    }

    @Override
    @Transactional
    public PedItem create(@Nonnull final PedItem pedItem) {
        return pedItemRepository.saveAndFlush(pedItem);
    }

    @Transactional
    public PedItem update(@Nonnull final PedItem pedItem) {
        return pedItemRepository.saveAndFlush(pedItem);
    }

    @Override
    public void delete(@Nonnull Integer id) {
        checkDelete(id);
        pedItemRepository.deleteById(id);
    }

    @Override
    public List<VwPedItem> findAll(Map<String, String> filters) {
        Specification<VwPedItem> spec = SpecificationUtils.createSpecification(filters);
        return vwPedItemRepository.findAll(spec);
    }

    @Override
    public Page<VwPedItem> findPage(Pageable pageable, Map<String, String> filtros) {
        Specification<VwPedItem> spec = SpecificationUtils.createSpecification(
                filtros,                                    // Map com parâmetros da requisição
                "nome", "descricao"     // campos que serão usados no OR do filterText
        );

        return vwPedItemRepository.findAll(spec, pageable);
    }

    @Override
    public void arquivar(@Nonnull Integer integer) {

    }

    @Override
    public void desarquivar(@Nonnull Integer integer) {

    }

    public void checkDelete(int id) {
        if (!pedItemRepository.existsById(id))
            throw new ApiRequestException("Item do pedido de venda não encontrado");
    }

    public boolean existsByCoditem(String codItem) {
        return pedItemRepository.existsByCodItemIgnoreCase(codItem);
    }

}