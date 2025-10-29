package com.holis.san01.model.local;

import lombok.Data;

import java.time.LocalDate;

/**
 * Classe usada para parametos de pesquisa com paginação
 */
@Data
public class FiltroPesquisa {

    private Integer status;
    private String filterText;
    private String codItem;
    private Integer codEntd;
    private LocalDate dtInicio;
    private LocalDate dtFim;
    private String tipo;
    private Integer numero;
    private Integer id;
    private String codigo;

    // Paginação e ordenação
    private Integer pageIndex;
    private Integer size;
    private String sortField;
    private String sortDirection;

}
