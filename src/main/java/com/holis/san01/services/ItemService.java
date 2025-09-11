package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.exceptions.NotFoundRequestException;
import com.holis.san01.mapper.ItemMapper;
import com.holis.san01.model.Item;
import com.holis.san01.model.ItemDTO;
import com.holis.san01.model.PedVendaItem;
import com.holis.san01.model.VwItem;
import com.holis.san01.repository.ItemRepository;
import com.holis.san01.repository.PedVendaItemRepository;
import com.holis.san01.repository.VwItemRepository;
import com.holis.san01.specs.VwItemSpecifications;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    private final VwItemRepository vwItemRepository;
    private final PedVendaItemRepository pedVendaItemRepository;
    private final ItemMapper itemMapper;

    /**
     * Ler Item por codigo de item
     */
    public ItemDTO findItemByCodItem(final String codItem) {

        Item item = itemRepository.findItemByCodItem(codItem)
                .orElseThrow(() -> new NotFoundRequestException("Item não encontrado"));
        return itemMapper.toDTO(item);
    }

    public Page<VwItem> pageVwItem(
            final String tpProd, final Integer status, final String filterText, final Pageable pageable) {

        Specification<VwItem> spec = Specification.where(null);

        if (status != null)
            spec = spec.and(VwItemSpecifications.hasStatus(status));

        if (!tpProd.equalsIgnoreCase("todos"))
            spec = spec.and(VwItemSpecifications.hasTpProd(tpProd));

        if (!StringUtils.isBlank(filterText))
            spec = spec.and(VwItemSpecifications.hasFiltro(filterText));

        return vwItemRepository.findAll(spec, pageable);
    }

    @Transactional
    public ItemDTO create(final ItemDTO dto) {

        Item item = itemMapper.toEntity(dto);

        Optional<Item> opt = itemRepository.findItemByCodItem(item.getCodItem());

        if (opt.isPresent()) {
            throw new ApiRequestException("Este código de item já existe!");
        }

        item.setArchive(false);
        item.setDtCriacao(LocalDate.now());
        item = itemRepository.saveAndFlush(item);
        return itemMapper.toDTO(item);
    }

    @Transactional
    public ItemDTO update(final ItemDTO dto) {

        Item item = itemRepository.findItemByCodItem(dto.getCodItem())
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

        return itemMapper.toDTO(item);
    }

    @Transactional
    public void delete(final String codItem) {

        checkDelete(codItem);
        itemRepository.deleteById(codItem);
    }

    /**
     * Verificar se o item pode ser deletado
     */
    public void checkDelete(String codItem) {

        Item item = itemRepository.findItemByCodItem(codItem)
                .orElseThrow(() -> new NotFoundRequestException("Item não encontrado para exclusão"));

        List<PedVendaItem> pedVendaItems = pedVendaItemRepository.listPedVendaItemByItemByItem(item.getCodItem());
        if (!pedVendaItems.isEmpty()) {
            throw new ApiRequestException("Exclusão inválida, existem pedidos para o item");
        }
    }

    public List<String> listFamilia() {

        return itemRepository.listFamilias();
    }

    public List<String> listSituacao() {

        return itemRepository.listSituacoes();
    }
}
