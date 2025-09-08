package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.exceptions.NotFoundRequestException;
import com.holis.san01.model.ApiResponse;
import com.holis.san01.model.Item;
import com.holis.san01.model.PedVendaItem;
import com.holis.san01.model.VwItem;
import com.holis.san01.repository.ItemRepository;
import com.holis.san01.repository.PedVendaItemRepository;
import com.holis.san01.repository.VwItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service para tratamento da tabela itens
 */
@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    private final PedVendaItemRepository pedVendaItemRepository;

    private final VwItemRepository vwItemRepository;

    /**
     * Ler Item por codigo de item
     */
    public ApiResponse getItem(final String codItem) {
        Item item = itemRepository.getItem(codItem)
                .orElseThrow(() -> new NotFoundRequestException("Item não encontrado"));
        return new ApiResponse(true, item);
    }

    public ApiResponse pageItem(final String criteria, final boolean archive, final String filterText, final Pageable pageable) {
        Page<VwItem> itens;

        if (criteria.equalsIgnoreCase("Todos")) {
            if (StringUtils.isBlank(filterText)) {
                itens = vwItemRepository.pageItens(archive, pageable);
            } else {
                itens = vwItemRepository.pageItens(archive, filterText, pageable);
            }
        } else {
            if (StringUtils.isBlank(filterText)) {
                itens = vwItemRepository.pageItensPorTipo(criteria, archive, pageable);
            } else {
                itens = vwItemRepository.pageItensPorTipo(criteria, archive, filterText, pageable);
            }
        }

        return new ApiResponse(true, itens);
    }

    public ApiResponse pageVwItemByExample(final VwItem vwItem, final Pageable pageable) {

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<VwItem> itemExample = Example.of(vwItem, matcher);

        var vwItems = vwItemRepository.findAll(itemExample, pageable);

        return new ApiResponse(true, vwItems);
    }

    @Transactional
    public ApiResponse create(final Item dto) {
        Optional<Item> opt = itemRepository.getItem(dto.getCodItem());

        if (opt.isPresent()) {
            throw new ApiRequestException("Este código de item já existe!");
        }

        dto.setArchive(false);
        dto.setDtCriacao(LocalDate.now());
        Item item = itemRepository.saveAndFlush(dto);
        return new ApiResponse(true, item);
    }

    @Transactional
    public ApiResponse update(final Item dto) {
        Item item = itemRepository.getItem(dto.getCodItem())
                .orElseThrow(() -> new NotFoundRequestException("Item não encontrado"));

        item.setCodItem(dto.getCodItem());
        item.setArchive(dto.isArchive());
        item.setCodTipoItem(dto.getCodTipoItem());
        item.setAliquotaIpi(dto.getAliquotaIpi());
        item.setCodLocaliz(dto.getCodLocaliz());
        item.setCodOrigem(dto.getCodOrigem());
        item.setIndComprado((dto.isIndComprado()));
        item.setDescricao(dto.getDescricao());
        item.setPrecoVenda(dto.getPrecoVenda());
        item.setDtPrecoVenda(dto.getDtPrecoVenda());
        item.setDtPrecoCompra(dto.getDtPrecoCompra());
        item.setPrecoCompra(dto.getPrecoCompra());
        item.setDtObsol(dto.getDtObsol());
        item.setDtLiberac(dto.getDtLiberac());
        item.setDtUltEnt(dto.getDtUltEnt());
        item.setCodFamilia(dto.getCodFamilia());
        item.setFraciona(dto.getFraciona());
        item.setGtin(dto.getGtin());
        item.setIndItemFat(dto.getIndItemFat());
        item.setLoteEcon(dto.getLoteEcon());
        item.setLoteMinCpa(dto.getLoteMinCpa());
        item.setLoteMinVda(dto.getLoteMinVda());
        item.setLoteMulven(dto.getLoteMulven());
        item.setNarrativa(dto.getNarrativa());
        item.setCodNcm(dto.getCodNcm());
        item.setOrigem(dto.getOrigem());
        item.setPesoBruto(dto.getPesoBruto());
        item.setPesoLiquido(dto.getPesoLiquido());
        item.setPrazoEntrega(dto.getPrazoEntrega());
        item.setPrecoUltEnt(dto.getPrecoUltEnt());
        item.setQuantPacote(dto.getQuantPacote());
        item.setResCompra(dto.getResCompra());
        item.setResFabric(dto.getResFabric());
        item.setSituacao(dto.getSituacao());
        item.setTempoRessup(dto.getTempoRessup());
        item.setUnimed(dto.getUnimed());
        item.setUsuarioObsol(dto.getUsuarioObsol());
        item = itemRepository.saveAndFlush(item);
        return new ApiResponse(true, item);
    }

    @Transactional
    public ApiResponse delete(final String codItem) {
        checkDelete(codItem);

        itemRepository.deleteById(codItem);
        return new ApiResponse(true, "Exclusão efetuada com sucesso");
    }

    /**
     * Verificar se o item pode ser deletado
     */
    public ApiResponse checkDelete(String codItem) {
        Item item = itemRepository.getItem(codItem)
                .orElseThrow(() -> new NotFoundRequestException("Item não encontrado para exclusão"));

        List<PedVendaItem> pedVendaItems = pedVendaItemRepository.listPedVendaItemByItemByItem(item.getCodItem());
        if (!pedVendaItems.isEmpty()) {
            throw new ApiRequestException("Exclusão inválida, existem pedidos para o item");
        }

        return new ApiResponse(true, "Exclusão pode ser efetuada");
    }

    public ApiResponse listFamilia() {
        return new ApiResponse(true, itemRepository.listFamilias());
    }

    public ApiResponse listSituacao() {
        return new ApiResponse(true, itemRepository.listSituacoes());
    }
}
