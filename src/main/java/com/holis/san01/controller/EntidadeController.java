package com.holis.san01.controller;

import com.holis.san01.model.ApiResponse;
import com.holis.san01.model.EntidadeDTO;
import com.holis.san01.services.EntidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller para tratamento de Entidades
 */
@RestController
@RequestMapping(value = "/api/entds", produces = MediaType.APPLICATION_JSON_VALUE)
public class EntidadeController {
    @Autowired
    private EntidadeService entidadeService;

    /**
     * Ler um determinado registro pelo código da entidade
     */
    @GetMapping
    public ResponseEntity<ApiResponse> ler(
            @RequestParam(name = "codEntd", defaultValue = "0") Integer codEntd) {
        ApiResponse apiResponse = entidadeService.ler(codEntd);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /**
     * Ler uma lista de entidades paginada com filtro
     */
    @GetMapping("/pages")
    public ResponseEntity<ApiResponse> listarTodosPaging(
            @RequestParam(name = "tipo", defaultValue = "") String tipoEntd,
            @RequestParam(name = "archive", defaultValue = "N") String archive,
            @RequestParam(name = "filterText", defaultValue = "") String filterText,
            @PageableDefault(page = 0, size = 40)
            @SortDefault.SortDefaults({@SortDefault(sort = "codEntd")}) Pageable pageable) {
        ApiResponse apiResponse = entidadeService.listarPaging(tipoEntd, archive, filterText, pageable);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /**
     * Incluir um novo registro de entidade
     */
    @PostMapping
    public ResponseEntity<ApiResponse> incluir(
            @RequestBody @Valid EntidadeDTO entidadeDTO) {
        ApiResponse apiResponse = entidadeService.incluir(entidadeDTO);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    /**
     * Alterar um registro existente
     */
    @PutMapping
    public ResponseEntity<ApiResponse> alterar(
            @RequestBody @Valid EntidadeDTO entidadeDTO) {
        ApiResponse apiResponse = entidadeService.alterar(entidadeDTO);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/checkDelete")
    public ResponseEntity<ApiResponse> checkDelete(@RequestParam(name = "codEntd") Integer codEntd) {
        ApiResponse apiResponse = entidadeService.checkDelete(codEntd);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /**
     * Excluir um registro
     */
    @DeleteMapping
    public ResponseEntity<ApiResponse> excluir(
            @RequestParam(name = "codEntd") Integer codEntd) {

        ApiResponse apiResponse = entidadeService.excluir(codEntd);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
