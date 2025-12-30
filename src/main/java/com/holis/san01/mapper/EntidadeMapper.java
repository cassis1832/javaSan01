package com.holis.san01.mapper;

import com.holis.san01.dto.EntidadeDto;
import com.holis.san01.model.Entidade;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntidadeMapper extends BaseMapper<Entidade, EntidadeDto> {
}
