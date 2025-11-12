package com.holis.san01.mapper;

import com.holis.san01.model.Item;
import com.holis.san01.model.ItemDto;
import com.holis.san01.model.TituloAp;
import com.holis.san01.model.TituloApDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TituloApMapper  extends BaseMapper<TituloAp, TituloApDto> {}
