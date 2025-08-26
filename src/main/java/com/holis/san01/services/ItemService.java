package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.exceptions.NotFoundRequestException;
import com.holis.san01.model.*;
import com.holis.san01.repository.ItemRepository;
import com.holis.san01.repository.PedVendaItemRepository;
import com.holis.san01.repository.VwItemRepository;
import com.holis.san01.util.Mapper;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Service para tratamento da tabela itens
 */
@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PedVendaItemRepository pedVendaItemRepository;

    @Autowired
    private VwItemRepository vwItemRepository;

    @Autowired
    private ItemMapper itemMapper;

    /**
     * Ler Item por codigo de item
     */
    public ApiResponse getItem(final String codItem) {
        Item item = itemRepository.getItem(codItem)
                .orElseThrow(() -> new NotFoundRequestException("Item não encontrado"));

        ItemDTO itemDTO = itemMapper.toDto(item);
        return new ApiResponse(true, itemDTO);
    }

    public ApiResponse pageItem(final String tipoItem, final String archive, final String filterText, final Pageable pageable) {
        Page<VwItem> itens;

        if (tipoItem.equalsIgnoreCase("Todos")) {
            if (StringUtils.isBlank(filterText)) {
                itens = vwItemRepository.pageItens(archive, pageable);
            } else {
                itens = vwItemRepository.pageItens(archive, filterText, pageable);
            }
        } else {
            if (StringUtils.isBlank(filterText)) {
                itens = vwItemRepository.pageItensPorTipo(tipoItem, archive, pageable);
            } else {
                itens = vwItemRepository.pageItensPorTipo(tipoItem, archive, filterText, pageable);
            }
        }

        return new ApiResponse(true, itens);
    }

    @Transactional
    public ApiResponse create(final ItemDTO dto) {
        Optional<Item> opt = itemRepository.getItem(dto.getCodItem());

        if (opt.isPresent()) {
            throw new ApiRequestException("Este código de item já existe!");
        }

        Item item = Mapper.mapTo(dto, Item.class);
        item.setArchive("N");
        item.setDtCriacao(LocalDate.now());
        item = itemRepository.saveAndFlush(item);
        return new ApiResponse(true, itemMapper.toDto(item));
    }

    @Transactional
    public ApiResponse update(final ItemDTO dto) {
        Item item = itemRepository.getItem(dto.getCodItem())
                .orElseThrow(() -> new NotFoundRequestException("Item não encontrado"));

        item.setCodItem(dto.getCodItem());
        item.setArchive(dto.getArchive());
        item.setCodTipoItem(dto.getCodTipoItem());
        item.setAliquotaIpi(dto.getAliquotaIpi());
        item.setCodLocaliz(dto.getCodLocaliz());
        item.setCodOrigem(dto.getCodOrigem());
        item.setComprFabric(dto.getComprFabric());
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
        return new ApiResponse(true, itemMapper.toDto(item));
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

        List<PedVendaItem> pedVendaItems = pedVendaItemRepository.listPedVendaItemDoItem(item.getCodItem());
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
