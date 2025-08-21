package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.model.Item;
import com.holis.san01.model.ItemDTO;
import com.holis.san01.model.PedVendaItem;
import com.holis.san01.model.VwItem;
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

    public ItemDTO ler(final String codItem) {
        Item item = itemRepository.findItem(codItem)
                .orElseThrow(() -> new ApiRequestException("Item não encontrado"));
        return itemMapper.toDto(item);
    }

    public Page<VwItem> listarPaging(final String tipoItem, final String archive, final String filterText, final Pageable pageable) {
        Page<VwItem> itens;

        if (tipoItem.equalsIgnoreCase("Todos")) {
            if (StringUtils.isBlank(filterText)) {
                itens = vwItemRepository.listItens(archive, pageable);
            } else {
                itens = vwItemRepository.listItens(archive, filterText, pageable);
            }
        } else {
            if (StringUtils.isBlank(filterText)) {
                itens = vwItemRepository.listItensPorTipo(tipoItem, archive, pageable);
            } else {
                itens = vwItemRepository.listItensPorTipo(tipoItem, archive, filterText, pageable);
            }
        }

        return itens;
    }

    @Transactional
    public ItemDTO incluir(final ItemDTO dto) {
        Optional<Item> opt = itemRepository.findItem(dto.getCodItem());

        if (opt.isPresent()) {
            throw new ApiRequestException("Este código de item já existe!");
        }

        Item item = Mapper.mapTo(dto, Item.class);
        item.setArchive("N");
        item.setDtCriacao(new Date());
        item = itemRepository.saveAndFlush(item);
        return itemMapper.toDto(item);
    }

    @Transactional
    public ItemDTO alterar(final ItemDTO dto) {
        Item item = itemRepository.findItem(dto.getCodItem())
                .orElseThrow(() -> new ApiRequestException("Item não encontrado"));

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

        return itemMapper.toDto(item);
    }

    @Transactional
    public void excluir(final String codItem) {
        Item item = itemRepository.findItem(codItem)
                .orElseThrow(() -> new ApiRequestException("Item não encontrado"));

        List<PedVendaItem> pedVendaItems = pedVendaItemRepository.listPedVendaItemDoItem(item.getCodItem());
        if (!pedVendaItems.isEmpty()){
            throw new ApiRequestException("Exclusão inválida, existem pedidos para o item");
        }

        itemRepository.deleteById(item.getCodItem());
    }

    public List<String> listarFamilias() {
        return itemRepository.listFamilias();
    }

    public List<String> listarSituacoes() {
        return itemRepository.listSituacoes();
    }
}
