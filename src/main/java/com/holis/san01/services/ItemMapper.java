package com.holis.san01.services;

import com.holis.san01.model.Item;
import com.holis.san01.model.ItemDTO;
import com.holis.san01.util.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    public ItemDTO toDto(Item item) {
        return Mapper.mapTo(item, ItemDTO.class);
    }

    public Item toEntity(ItemDTO dto) {
        return Mapper.mapTo(dto, Item.class);
    }
}
