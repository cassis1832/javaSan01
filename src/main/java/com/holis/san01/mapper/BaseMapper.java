package com.holis.san01.mapper;

import jakarta.annotation.Nonnull;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseMapper<ENTITY, DTO> {

    // From Entity to DTO
    DTO toDto(ENTITY entity);

    // From DTO to Entity
    ENTITY toEntity(DTO dto);

    // From Entity List to DTO List
    List<DTO> toDtoList(List<ENTITY> entities);

    // From Entity Page to DTO Page
    default Page<DTO> toDtoPage(@Nonnull Page<ENTITY> page) {
        return page.map(this::toDto);
    }

    // Atualizar entity a partir de DTO
    void updateEntityFromDto(DTO updateDto, @MappingTarget ENTITY entity);
}