package com.holis.san01.mapper;

import com.holis.san01.model.PedItem;
import com.holis.san01.model.PedItemDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedItemMapper extends BaseMapper<PedItem, PedItemDto> {
}