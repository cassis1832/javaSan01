package com.holis.san01.mapper;

import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseMapper<ENTITY, DTO> {

    /**
     * From Entity to DTO
     *
     * @param entity - entidade
     * @return DTO - DTO
     */
    DTO toDto(ENTITY entity);

    /**
     * From DTO to Entity
     *
     * @param dto - DTO
     * @return Entity
     */
    ENTITY toEntity(DTO dto);

    /**
     * From Entity List to DTO List
     *
     * @param entities - list of entities
     * @return List of DTOs
     */
    List<DTO> toDtoList(List<ENTITY> entities);

    /**
     * From Entity Page to DTO Page
     *
     * @param page of entities
     * @return page of DTOs
     */
    default Page<DTO> toDtoPage(Page<ENTITY> page) {
        return page.map(this::toDto);
    }

    // Atualizar entity a partir de DTO
    void updateEntityFromDto(DTO updateDto, @MappingTarget ENTITY entity);
}