package com.holis.san01.mapper;

import com.holis.san01.model.PedVendaItem;
import com.holis.san01.model.PedVendaItemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedVendaItemMapper {

    PedVendaItem toEntity(PedVendaItemDTO dto);

    PedVendaItemDTO toDTO(PedVendaItem pedVenda);
}
