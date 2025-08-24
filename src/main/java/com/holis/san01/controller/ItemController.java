package com.holis.san01.controller;

import com.holis.san01.model.ApiResponse;
import com.holis.san01.model.ItemDTO;
import com.holis.san01.services.ItemService;
import com.holis.san01.services.TipoItemService;
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
 * Controller para tratamento de Itens
 */
@RestController
@RequestMapping(value = "/api/items", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private TipoItemService tipoItemService;

    /**
     * Ler um determinado registro pelo código
     */
    @GetMapping
    public ResponseEntity<ApiResponse> ler(@RequestParam(name = "codItem", defaultValue = "") String codItem) {
        ApiResponse apiResponse = itemService.ler(codItem);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /**
     * Ler uma lista de itens filtrando pelo nome/codigo e situacao
     */
    @GetMapping("/pages")
    public ResponseEntity<ApiResponse> listarItens(
            @RequestParam(name = "tipoItem", defaultValue = "") String tipoItem,
            @RequestParam(name = "archive", defaultValue = "N") String archive,
            @RequestParam(name = "filterText", defaultValue = "") String filterText,
            @PageableDefault(page = 0, size = 40)
            @SortDefault.SortDefaults({@SortDefault(sort = "codItem")}) Pageable pageable) {

        ApiResponse apiResponse = itemService.listarPaging(tipoItem, archive, filterText, pageable);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /**
     * Incluir um novo registro
     */
    @PostMapping
    public ResponseEntity<ApiResponse> incluir(@RequestBody @Valid ItemDTO itemDTO) {
        ApiResponse apiResponse = itemService.incluir(itemDTO);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    /**
     * Alterar um registro existente
     */
    @PutMapping
    public ResponseEntity<ApiResponse> alterar(@RequestBody @Valid ItemDTO itemDTO) {
        ApiResponse apiResponse = itemService.alterar(itemDTO);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /**
     * Verificar se o item pode ser deletado
     */
    @GetMapping("/checkDelete")
    public ResponseEntity<ApiResponse> checkDelete(@RequestParam(name = "codItem") String codItem) {
        ApiResponse apiResponse = itemService.checkDelete(codItem);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /**
     * Excluir um registro
     */
    @DeleteMapping
    public ResponseEntity<ApiResponse> excluir(@RequestParam(name = "codItem") String codItem) {
        ApiResponse apiResponse = itemService.excluir(codItem);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/tpItems")
    public ResponseEntity<ApiResponse> listarTipoItem() {
        ApiResponse apiResponse = tipoItemService.listar();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /**
     * Ler uma lista das familias dos itens
     */
    @GetMapping("/familias")
    public ResponseEntity<ApiResponse> listarFamilias() {
        ApiResponse apiResponse = itemService.listarFamilias();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /**
     * Ler uma lista de itens filtrando pelo nome/codigo e situacao
     */
    @GetMapping("/situacoes")
    public ResponseEntity<ApiResponse> listarSituacoes() {
        ApiResponse apiResponse = itemService.listarSituacoes();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
