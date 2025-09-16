package com.holis.san01.mapper;

import com.holis.san01.model.TituloAp;
import com.holis.san01.model.TituloApDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TituloApMapper {

    TituloAp toEntity(TituloApDTO tituloApDTO);

    TituloApDTO toDTO(TituloAp tituloAp);
}
