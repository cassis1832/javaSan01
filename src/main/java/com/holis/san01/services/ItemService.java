package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.exceptions.NotFoundRequestException;
import com.holis.san01.model.Item;
import com.holis.san01.model.VwItem;
import com.holis.san01.repository.ItemRepository;
import com.holis.san01.repository.PedItemRepository;
import com.holis.san01.repository.VwItemRepository;
import com.holis.san01.utils.SpecificationUtils;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static com.holis.san01.utils.Constantes.STATUS_ARQUIVADO;
import static com.holis.san01.utils.Constantes.STATUS_ATIVO;

/**
 * Service para tratamento da tabela itens
 */
@Service
@RequiredArgsConstructor
public class ItemService implements BaseService<Item, String, VwItem> {

    private final ItemRepository itemRepository;
    private final VwItemRepository vwItemRepository;
    private final PedItemRepository pedItemRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Item findById(@Nonnull String id) {
        return itemRepository.findByCodItemIgnoreCase(id)
                .orElseThrow(() -> new NotFoundRequestException("Item não cadastrado"));
    }

    @Override
    @Transactional
    public Item create(@Nonnull Item item) {
        if (itemRepository.existsByCodItemIgnoreCase(item.getCodItem())) {
            throw new ApiRequestException("Este código de item já existe!");
        }

        item.setStatus(0);
        item.setDtCriacao(Instant.now());
        return itemRepository.saveAndFlush(item);
    }

    @Override
    @Transactional
    public Item update(@Nonnull final Item item) {
        Item existing = itemRepository.findByCodItemIgnoreCase(item.getCodItem())
                .orElseThrow(() -> new NotFoundRequestException("Item não cadastrado"));

        existing.setCodItem(item.getCodItem());
        existing.setCodTipoItem(item.getCodTipoItem());
        existing.setCodUniMed(item.getCodUniMed());
        existing.setStatus(item.getStatus());
        existing.setAliquotaIpi(item.getAliquotaIpi());
        existing.setGtin(item.getGtin());
        existing.setCodLocaliz(item.getCodLocaliz());
        existing.setCodOrigem(item.getCodOrigem());
        existing.setIndComprado((item.getIndComprado()));
        existing.setDescricao(item.getDescricao());
        existing.setPrecoVenda(item.getPrecoVenda());
        existing.setDtPrecoVenda(item.getDtPrecoVenda());
        existing.setDtPrecoCompra(item.getDtPrecoCompra());
        existing.setPrecoCompra(item.getPrecoCompra());
        existing.setDtObsol(item.getDtObsol());
        existing.setDtLiberac(item.getDtLiberac());
        existing.setDtUltEnt(item.getDtUltEnt());
        existing.setCodFamilia(item.getCodFamilia());
        existing.setFraciona(item.getFraciona());
        existing.setGtin(item.getGtin());
        existing.setIndItemFat(item.getIndItemFat());
        existing.setLoteEcon(item.getLoteEcon());
        existing.setLoteMinCpa(item.getLoteMinCpa());
        existing.setLoteMinVda(item.getLoteMinVda());
        existing.setLoteMulven(item.getLoteMulven());
        existing.setNarrativa(item.getNarrativa());
        existing.setCodNcm(item.getCodNcm());
        existing.setOrigem(item.getOrigem());
        existing.setPesoBruto(item.getPesoBruto());
        existing.setPesoLiquido(item.getPesoLiquido());
        existing.setPrazoEntrega(item.getPrazoEntrega());
        existing.setPrecoUltEnt(item.getPrecoUltEnt());
        existing.setQuantPacote(item.getQuantPacote());
        existing.setResCompra(item.getResCompra());
        existing.setResFabric(item.getResFabric());
        existing.setTempoRessup(item.getTempoRessup());
        existing.setUsuarioObsol(item.getUsuarioObsol());
        existing.setSituacao(item.getSituacao());
        existing.setLibCompra(item.getLibCompra());
        existing.setLibVenda(item.getLibVenda());
        existing.setLibProducao(item.getLibProducao());
        return itemRepository.saveAndFlush(item);
    }

    @Override
    @Transactional
    public void delete(@Nonnull String id) {
        checkDelete(id);
        itemRepository.deleteByCodItemIgnoreCase(id);
    }

    @Override
    public List<VwItem> findAll(Map<String, String> filters) {
        Specification<VwItem> spec = SpecificationUtils.createSpecification(filters);
        return vwItemRepository.findAll(spec);
    }

    @Override
    public Page<VwItem> findPage(Pageable pageable, Map<String, String> filtros) {
        Specification<VwItem> spec = SpecificationUtils.createSpecification(
                filtros,                                    // Map com parâmetros da requisição
                "descricao", "codItem"     // campos que serão usados no OR do filterText
        );

        return vwItemRepository.findAll(spec, pageable);
    }

    public void checkDelete(String codItem) {
        if (!itemRepository.existsByCodItemIgnoreCase(codItem))
            throw new NotFoundRequestException("Item não cadastrado");

        if (pedItemRepository.existsByCodItemIgnoreCase(codItem))
            throw new ApiRequestException("Exclusão inválida, existem pedidos para o item");
    }

    @Override
    @Transactional
    public void arquivar(@Nonnull String id) {
        Item item = itemRepository.findByCodItemIgnoreCase(id)
                .orElseThrow(() -> new NotFoundRequestException("Item não cadastrado"));

        itemRepository.archive(item.getCodItem(), STATUS_ARQUIVADO);
    }

    @Override
    @Transactional
    public void desarquivar(@Nonnull String id) {
        Item item = itemRepository.findByCodItemIgnoreCase(id)
                .orElseThrow(() -> new NotFoundRequestException("Item não cadastrado"));

        itemRepository.archive(item.getCodItem(), STATUS_ATIVO);
    }

    public List<String> listFamilia() {
        return itemRepository.listFamilias();
    }

    public List<String> listSituacao() {
        return itemRepository.listSituacoes();
    }
}
