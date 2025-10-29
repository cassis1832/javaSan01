package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.exceptions.NotFoundRequestException;
import com.holis.san01.model.Item;
import com.holis.san01.model.VwItem;
import com.holis.san01.model.local.FiltroPesquisa;
import com.holis.san01.repository.ItemRepository;
import com.holis.san01.repository.PedItemRepository;
import com.holis.san01.repository.VwItemRepository;
import com.holis.san01.specs.VwItemSpecifications;
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

import java.time.LocalDate;
import java.util.List;

import static com.holis.san01.model.local.Constantes.STATUS_ARQUIVADO;
import static com.holis.san01.model.local.Constantes.STATUS_ATIVO;

/**
 * Service para tratamento da tabela itens
 */
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final VwItemRepository vwItemRepository;
    private final PedItemRepository pedItemRepository;

    /**
     * Ler Item por codigo de item
     */
    public Item findItemByCodItem(final String codItem) {

        return itemRepository.findItemByCodItem(codItem)
                .orElseThrow(() -> new NotFoundRequestException("Item não cadastrado"));
    }

    public Page<VwItem> pageVwItem(FiltroPesquisa filtro) {

        Specification<VwItem> spec = Specification.where(null);

        if (filtro.getStatus() != null)
            spec = spec.and(VwItemSpecifications.hasStatus(filtro.getStatus()));

        if (!StringUtils.isBlank(filtro.getTipo()) && !filtro.getTipo().equalsIgnoreCase("todos"))
            spec = spec.and(VwItemSpecifications.hasTpProd(filtro.getTipo()));

        if (!StringUtils.isBlank(filtro.getFilterText()))
            spec = spec.and(VwItemSpecifications.hasFiltro(filtro.getFilterText()));

        Sort sort = Sort.by(Sort.Direction.fromString(filtro.getSortDirection()), filtro.getSortField());
        Pageable pageable = PageRequest.of(filtro.getPageIndex(), filtro.getSize(), sort);

        return vwItemRepository.findAll(spec, pageable);
    }

    @Transactional
    public Item create(@Nonnull final Item item) {

        if (itemRepository.existsByCodItem(item.getCodItem())) {
            throw new ApiRequestException("Este código de item já existe!");
        }

        item.setStatus(0);
        item.setDtCriacao(LocalDate.now());
        return itemRepository.saveAndFlush(item);
    }

    @Transactional
    public Item update(final Item itemInput) {

        Item item = itemRepository.findItemByCodItem(itemInput.getCodItem())
                .orElseThrow(() -> new NotFoundRequestException("Item não cadastrado"));

        item.setCodItem(itemInput.getCodItem());
        item.setStatus(itemInput.getStatus());
        item.setCodTipoItem(itemInput.getCodTipoItem());
        item.setAliquotaIpi(itemInput.getAliquotaIpi());
        item.setGtin(itemInput.getGtin());
        item.setCodLocaliz(itemInput.getCodLocaliz());
        item.setCodOrigem(itemInput.getCodOrigem());
        item.setIndComprado((itemInput.getIndComprado()));
        item.setDescricao(itemInput.getDescricao());
        item.setPrecoVenda(itemInput.getPrecoVenda());
        item.setDtPrecoVenda(itemInput.getDtPrecoVenda());
        item.setDtPrecoCompra(itemInput.getDtPrecoCompra());
        item.setPrecoCompra(itemInput.getPrecoCompra());
        item.setDtObsol(itemInput.getDtObsol());
        item.setDtLiberac(itemInput.getDtLiberac());
        item.setDtUltEnt(itemInput.getDtUltEnt());
        item.setCodFamilia(itemInput.getCodFamilia());
        item.setFraciona(itemInput.getFraciona());
        item.setGtin(itemInput.getGtin());
        item.setIndItemFat(itemInput.getIndItemFat());
        item.setLoteEcon(itemInput.getLoteEcon());
        item.setLoteMinCpa(itemInput.getLoteMinCpa());
        item.setLoteMinVda(itemInput.getLoteMinVda());
        item.setLoteMulven(itemInput.getLoteMulven());
        item.setNarrativa(itemInput.getNarrativa());
        item.setCodNcm(itemInput.getCodNcm());
        item.setOrigem(itemInput.getOrigem());
        item.setPesoBruto(itemInput.getPesoBruto());
        item.setPesoLiquido(itemInput.getPesoLiquido());
        item.setPrazoEntrega(itemInput.getPrazoEntrega());
        item.setPrecoUltEnt(itemInput.getPrecoUltEnt());
        item.setQuantPacote(itemInput.getQuantPacote());
        item.setResCompra(itemInput.getResCompra());
        item.setResFabric(itemInput.getResFabric());
        item.setCodSit(itemInput.getCodSit());
        item.setTempoRessup(itemInput.getTempoRessup());
        item.setCodUniMed(itemInput.getCodUniMed());
        item.setUsuarioObsol(itemInput.getUsuarioObsol());

        return itemRepository.saveAndFlush(item);
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

        if (!itemRepository.existsByCodItem(codItem))
            throw new NotFoundRequestException("Item não cadsatrado");

        if (pedItemRepository.existsByCodItem(codItem))
            throw new ApiRequestException("Exclusão inválida, existem pedidos para o item");
    }

    public void archive(String codItem) {

        Item item = itemRepository.findItemByCodItem(codItem)
                .orElseThrow(() -> new NotFoundRequestException("Item não cadastrado"));

        item.setStatus(STATUS_ARQUIVADO);
        itemRepository.saveAndFlush(item);
    }

    public void unarchive(String codItem) {

        Item item = itemRepository.findItemByCodItem(codItem)
                .orElseThrow(() -> new NotFoundRequestException("Item não cadastrado"));

        item.setStatus(STATUS_ATIVO);
        itemRepository.saveAndFlush(item);
    }

    public List<String> listFamilia() {

        return itemRepository.listFamilias();
    }
}
