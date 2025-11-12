package com.holis.san01.mapper;

import com.holis.san01.model.CondPag;
import com.holis.san01.model.CondPagDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CondPagMapper extends BaseMapper<CondPag, CondPagDto> {
}
