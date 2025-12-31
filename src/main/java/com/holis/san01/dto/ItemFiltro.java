package com.holis.san01.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ItemFiltro extends BaseFiltro {
    private String codItem;
    private String codTipoItem;
    private String descricao;
    private String familia;
    private String gtin;
}