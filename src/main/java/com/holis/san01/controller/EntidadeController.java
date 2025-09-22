package com.holis.san01.controller;

import com.holis.san01.mapper.EntidadeMapper;
import com.holis.san01.model.local.ApiResponse;
import com.holis.san01.model.Entidade;
import com.holis.san01.model.EntidadeDTO;
import com.holis.san01.model.local.FiltroPesquisa;
import com.holis.san01.services.EntidadeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller para tratamento de Entidades
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/entds", produces = MediaType.APPLICATION_JSON_VALUE)
public class EntidadeController {

    private final EntidadeService entidadeService;
    private final EntidadeMapper entidadeMapper;

    /**
     * Ler um determinado registro pelo código da entidade
     */
    @GetMapping
    public ResponseEntity<ApiResponse> getEntidade(
            @RequestParam(name = "codEntd", defaultValue = "0") Integer codEntd) {

        Entidade entidade = entidadeService.getEntidade(codEntd);
        return new ResponseEntity<>(new ApiResponse(true, entidadeMapper.toDTO(entidade)), HttpStatus.OK);
    }

    /**
     * Ler uma lista de entidades paginada com filtro
     */
    @GetMapping("/page")
    public ResponseEntity<ApiResponse> pageEntidade(
            @ModelAttribute FiltroPesquisa filtroPesquisa) {

        var page = entidadeService.pageEntidade(filtroPesquisa);
        return new ResponseEntity<>(new ApiResponse(true, page), HttpStatus.OK);
    }

    /**
     * Incluir um novo registro de entidade
     */
    @PostMapping
    public ResponseEntity<ApiResponse> create(
            @RequestBody @Valid EntidadeDTO entidadeDTO) {

        Entidade entidade = entidadeService.create(entidadeMapper.toEntity(entidadeDTO));
        return new ResponseEntity<>(new ApiResponse(true, entidadeMapper.toDTO(entidade)), HttpStatus.CREATED);
    }

    /**
     * Alterar um registro existente
     */
    @PutMapping
    public ResponseEntity<ApiResponse> update(
            @RequestBody @Valid EntidadeDTO entidadeDTO) {

        Entidade entidade = entidadeService.update(entidadeMapper.toEntity(entidadeDTO));
        return new ResponseEntity<>(new ApiResponse(true, entidadeMapper.toDTO(entidade)), HttpStatus.OK);
    }

    @GetMapping("/checkDelete")
    public ResponseEntity<ApiResponse> checkDelete(
            @RequestParam(name = "codEntd") Integer codEntd) {

        entidadeService.checkDelete(codEntd);
        return new ResponseEntity<>(new ApiResponse(true, "Entidade pode ser excluído"), HttpStatus.OK);
    }

    /**
     * Excluir um registro
     */
    @DeleteMapping
    public ResponseEntity<ApiResponse> delete(
            @RequestParam(name = "codEntd") Integer codEntd) {

        entidadeService.delete(codEntd);
        return new ResponseEntity<>(new ApiResponse(true, "Entidade excluído com sucesso"), HttpStatus.OK);
    }
}
