package com.holis.san01.mapper;

import com.holis.san01.model.Usuario;
import com.holis.san01.model.UsuarioDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper extends BaseMapper<Usuario, UsuarioDto> {
}
