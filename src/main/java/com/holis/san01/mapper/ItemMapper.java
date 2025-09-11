package com.holis.san01.mapper;

import com.holis.san01.model.Item;
import com.holis.san01.model.ItemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item toEntity(ItemDTO dto);

    ItemDTO toDTO(Item usuario);
}
