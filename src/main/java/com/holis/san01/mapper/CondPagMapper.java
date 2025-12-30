package com.holis.san01.mapper;

import com.holis.san01.dto.CondPagDto;
import com.holis.san01.model.CondPag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CondPagMapper extends BaseMapper<CondPag, CondPagDto> {
}
