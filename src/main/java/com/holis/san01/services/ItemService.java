package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.exceptions.NotFoundRequestException;
import com.holis.san01.model.Item;
import com.holis.san01.model.ItemDTO;
import com.holis.san01.repository.ItemRepository;
import com.holis.san01.util.Converter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * Service para tratamento da tabela itens
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemDTO lerItem(String codItem) {
        Item item = itemRepository.findItem(codItem)
                .orElseThrow(() -> new NotFoundRequestException("Item não encontrado"));
        return Converter.mapTo(item, ItemDTO.class);
    }

    public Page<ItemDTO> listarItens(String status, String filterText, Pageable pageable) {
        Page<Item> itens;

        if (StringUtils.isBlank(filterText)) {
            itens = itemRepository.findItens(status, pageable);
        } else {
            itens = itemRepository.findItens(status, filterText, pageable);
        }
        return itens.map(this::toDto);
    }

    public ItemDTO incluirItem(ItemDTO dto) {
        Optional<Item> opt = itemRepository.findItem(dto.getCodItem());

        if (opt.isPresent()) {
            throw new ApiRequestException("Este código de item já existe!");
        }

        Item item = Converter.mapTo(dto, Item.class);
        item.setStatus("A");
        item.setDtCriacao(new Date());
        item = itemRepository.saveAndFlush(item);
        return Converter.mapTo(item, ItemDTO.class);
    }

    public ItemDTO alterarItem(ItemDTO dto) {
        Item item = itemRepository.findItem(dto.getCodItem())
                .orElseThrow(() -> new NotFoundRequestException("Item não encontrado"));

        item.setCodItem(dto.getCodItem());
        item.setStatus(dto.getStatus());
        item.setAliquotaIpi(dto.getAliquotaIpi());
        item.setClassFiscal(dto.getClassFiscal());
        item.setCodLocaliz(dto.getCodLocaliz());
        item.setCodOrigem(dto.getCodOrigem());
        item.setComprFabric(dto.getComprFabric());
        item.setCustoRep(dto.getCustoRep());
        item.setDescricao(dto.getDescricao());
        item.setDtBase(dto.getDtBase());
        item.setDtObsol(dto.getDtObsol());
        item.setDtLiberac(dto.getDtLiberac());
        item.setDtUltEnt(dto.getDtUltEnt());
        item.setDtUltRep(dto.getDtUltRep());
        item.setCodFamilia(dto.getCodFamilia());
        item.setFraciona(dto.getFraciona());
        item.setGtin(dto.getGtin());
        item.setIndItemFat(dto.getIndItemFat());
        item.setLoteEcon(dto.getLoteEcon());
        item.setLoteMinCpa(dto.getLoteMinCpa());
        item.setLoteMinVda(dto.getLoteMinVda());
        item.setLoteMulven(dto.getLoteMulven());
        item.setNarrativa(dto.getNarrativa());
        item.setNcm(dto.getNcm());
        item.setOrigem(dto.getOrigem());
        item.setPesoBruto(dto.getPesoBruto());
        item.setPesoLiquido(dto.getPesoLiquido());
        item.setPrazoEntrega(dto.getPrazoEntrega());
        item.setPrecoBase(dto.getPrecoBase());
        item.setPrecoUltEnt(dto.getPrecoUltEnt());
        item.setQuantPacote(dto.getQuantPacote());
        item.setResCompra(dto.getResCompra());
        item.setResFabric(dto.getResFabric());
        item.setSituacao(dto.getSituacao());
        item.setTempoRessup(dto.getTempoRessup());
        item.setCodTipoItem(dto.getCodTipoItem());
        item.setUnimed(dto.getUnimed());
        item.setUsuarioObsol(dto.getUsuarioObsol());
        item = itemRepository.saveAndFlush(item);
        return Converter.mapTo(item, ItemDTO.class);
    }

    public void excluirItem(String codItem) {
        Item item = itemRepository.findItem(codItem)
                .orElseThrow(() -> new NotFoundRequestException("Item não encontrado"));

        itemRepository.delete(item);
    }

    private ItemDTO toDto(Item item) {
        return Converter.mapTo(item, ItemDTO.class);
    }
}
