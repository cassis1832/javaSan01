package com.holis.san01.mapper;

import com.holis.san01.model.PedItem;
import com.holis.san01.model.PedItemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedItemMapper {

    PedItem toEntity(PedItemDTO dto);

    PedItemDTO toDTO(PedItem pedVenda);
}
