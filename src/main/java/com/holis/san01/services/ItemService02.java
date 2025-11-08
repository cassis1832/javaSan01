package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.exceptions.NotFoundRequestException;
import com.holis.san01.model.Item;
import com.holis.san01.model.VwItem;
import com.holis.san01.repository.ItemRepository;
import com.holis.san01.repository.VwItemRepository;
import com.holis.san01.utils.SpecificationUtils;
import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.holis.san01.model.local.Constantes.STATUS_ARQUIVADO;
import static com.holis.san01.model.local.Constantes.STATUS_ATIVO;

/**
 * Service para tratamento da tabela itens
 */
@Service
public class ItemService02 extends BaseService<Item, String> {

    private final ItemRepository itemRepository;
    private final VwItemRepository vwItemRepository;
    private final PedItemService pedItemService;

    public ItemService02(
            ItemRepository itemRepository,
            VwItemRepository vwItemRepository,
            PedItemService pedItemService) {
        super(itemRepository, itemRepository);
        this.itemRepository = itemRepository;
        this.vwItemRepository = vwItemRepository;
        this.pedItemService = pedItemService;
    }

    @Override
    @Transactional
    public Item save(@Nonnull final Item item) {

        if (itemRepository.existsByCodItem(item.getCodItem())) {
            throw new ApiRequestException("Este código de item já existe!");
        }

        System.out.println("item.getLibProducao()");
        System.out.println(item.getLibProducao());
        item.setStatus(0);
        item.setDtCriacao(LocalDate.now());
        return itemRepository.saveAndFlush(item);
    }

    @Override
    @Transactional
    public Item update(@Nonnull final Item itemInput) {

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
        item.setTempoRessup(itemInput.getTempoRessup());
        item.setCodUniMed(itemInput.getCodUniMed());
        item.setUsuarioObsol(itemInput.getUsuarioObsol());
        item.setSituacao(itemInput.getSituacao());
        item.setLibCompra(itemInput.getLibCompra());
        item.setLibVenda(itemInput.getLibVenda());
        item.setLibProducao(itemInput.getLibProducao());

        return itemRepository.saveAndFlush(item);
    }

    @Override
    @Transactional
    public void deleteById(@Nonnull String codItem) {
        checkDelete(codItem);
        itemRepository.deleteById(codItem);
    }

    public Page<VwItem> pageVwItem(Pageable pageable, Map<String, String> filtros) {
        Specification<VwItem> spec = SpecificationUtils.createSpecification(filtros);
        return vwItemRepository.findAll(spec, pageable);
    }

    public void checkDelete(String codItem) {
        if (!itemRepository.existsByCodItem(codItem))
            throw new NotFoundRequestException("Item não cadsatrado");

        if (pedItemService.existsByCoditem(codItem))
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

    public List<String> listSituacao() {
        return itemRepository.listSituacoes();
    }
}
