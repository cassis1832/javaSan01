package com.holis.san01.services;

import com.holis.san01.model.Entidade;
import com.holis.san01.model.EntidadeDTO;
import com.holis.san01.util.Mapper;
import org.springframework.stereotype.Component;

@Component
public class EntidadeMapper {
    public EntidadeDTO toDto(Entidade entidade) {
        return Mapper.mapTo(entidade, EntidadeDTO.class);
    }

    public Entidade toEntity(EntidadeDTO dto) {
        return Mapper.mapTo(dto, Entidade.class);
    }
}
