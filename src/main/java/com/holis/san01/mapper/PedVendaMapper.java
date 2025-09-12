package com.holis.san01.mapper;

import com.holis.san01.model.PedVenda;
import com.holis.san01.model.PedVendaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedVendaMapper {

    PedVenda toEntity(PedVendaDTO dto);

    PedVendaDTO toDTO(PedVenda pedVenda);
}
