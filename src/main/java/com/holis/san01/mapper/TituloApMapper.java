package com.holis.san01.mapper;

import com.holis.san01.dto.TituloApDto;
import com.holis.san01.model.TituloAp;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TituloApMapper extends BaseMapper<TituloAp, TituloApDto> {
}
