package com.holis.san01.mapper;

import com.holis.san01.dto.PedItemDto;
import com.holis.san01.model.PedItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedItemMapper extends BaseMapper<PedItem, PedItemDto> {
}