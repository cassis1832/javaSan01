package com.holis.san01.dto;

import lombok.Data;

@Data
public class BaseFiltro {
    // Paginação e ordenação
    private Integer pageIndex;
    private Integer size;
    private String sortField;
    private String sortDirection;

    private Integer status;
    private String situacao;
}
