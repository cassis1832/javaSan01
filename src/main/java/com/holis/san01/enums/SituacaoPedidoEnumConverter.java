package com.holis.san01.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SituacaoPedidoEnumConverter implements AttributeConverter<SituacaoPedidoEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(SituacaoPedidoEnum attribute) {
        return attribute == null ? null : attribute.getCodigo();
    }

    @Override
    public SituacaoPedidoEnum convertToEntityAttribute(Integer dbData) {
        return SituacaoPedidoEnum.fromCodigo(dbData);
    }
}