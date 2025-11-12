package com.holis.san01.mapper;

import com.holis.san01.model.PedVenda;
import com.holis.san01.model.PedVendaDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedVendaMapper extends BaseMapper<PedVenda, PedVendaDto> {
}
