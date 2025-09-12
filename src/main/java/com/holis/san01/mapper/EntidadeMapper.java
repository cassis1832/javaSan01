package com.holis.san01.mapper;

import com.holis.san01.model.Entidade;
import com.holis.san01.model.EntidadeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntidadeMapper {

    Entidade toEntity(EntidadeDTO entidadeDTO);

    EntidadeDTO toDTO(Entidade entidade);
}
