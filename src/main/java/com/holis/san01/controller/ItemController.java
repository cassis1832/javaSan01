package com.holis.san01.controller;

import com.holis.san01.model.ApiResponse;
import com.holis.san01.model.ItemDTO;
import com.holis.san01.model.TipoItem;
import com.holis.san01.model.VwItem;
import com.holis.san01.services.ItemService;
import com.holis.san01.services.TipoItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller para tratamento de Itens
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/items", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemController {

    private final ItemService itemService;
    private final TipoItemService tipoItemService;

    /**
     * Ler um determinado registro pelo código
     */
    @GetMapping
    public ResponseEntity<ApiResponse> getItem(
            @RequestParam(name = "codItem", defaultValue = "") String codItem) {

        ItemDTO itemDTO = itemService.findItemByCodItem(codItem);
        return new ResponseEntity<>(new ApiResponse(true, itemDTO), HttpStatus.OK);
    }

    /**
     * Ler uma lista de itens filtrando pelo nome/codigo e situacao
     */
    @GetMapping("/pages")
    public ResponseEntity<ApiResponse> pageItem(
            @RequestParam(name = "tipoItem", defaultValue = "") String tipoItem,
            @RequestParam(name = "status", defaultValue = "0") Integer status,
            @RequestParam(name = "filterText", defaultValue = "") String filterText,
            @PageableDefault(page = 0, size = 40)
            @SortDefault.SortDefaults({@SortDefault(sort = "codItem")}) Pageable pageable) {

        Page<VwItem> vwItems = itemService.pageVwItem(tipoItem, status, filterText, pageable);
        return new ResponseEntity<>(new ApiResponse(true, vwItems), HttpStatus.OK);
    }

    /**
     * Incluir um novo registro
     */
    @PostMapping
    public ResponseEntity<ApiResponse> create(
            @RequestBody @Valid ItemDTO dto) {

        ItemDTO itemDTO = itemService.create(dto);
        return new ResponseEntity<>(new ApiResponse(true, itemDTO), HttpStatus.CREATED);
    }

    /**
     * Alterar um registro existente
     */
    @PutMapping
    public ResponseEntity<ApiResponse> update(
            @RequestBody @Valid ItemDTO dto) {

        ItemDTO itemDTO = itemService.update(dto);
        return new ResponseEntity<>(new ApiResponse(true, itemDTO), HttpStatus.OK);
    }

    /**
     * Verificar se o item pode ser deletado
     */
    @GetMapping("/checkDelete")
    public ResponseEntity<ApiResponse> checkDelete(
            @RequestParam(name = "codItem") String codItem) {

        itemService.checkDelete(codItem);
        return new ResponseEntity<>(new ApiResponse(true, "Item pode ser excluído"), HttpStatus.OK);
    }

    /**
     * Excluir um registro
     */
    @DeleteMapping
    public ResponseEntity<ApiResponse> delete(
            @RequestParam(name = "codItem") String codItem) {

        itemService.delete(codItem);
        return new ResponseEntity<>(new ApiResponse(true, "Item excluído com sucesso"), HttpStatus.OK);
    }

    @GetMapping("/tpItems")
    public ResponseEntity<ApiResponse> listTipoItem() {

        List<TipoItem> tipoItems = tipoItemService.listar();
        return new ResponseEntity<>(new ApiResponse(true, tipoItems), HttpStatus.OK);
    }

    /**
     * Ler uma lista das familias dos itens
     */
    @GetMapping("/familias")
    public ResponseEntity<ApiResponse> listFamilia() {

        List<String> familias = itemService.listFamilia();
        return new ResponseEntity<>(new ApiResponse(true, familias), HttpStatus.OK);
    }

    /**
     * Ler uma lista de itens filtrando pelo nome/codigo e situacao
     */
    @GetMapping("/situacoes")
    public ResponseEntity<ApiResponse> listSituacao() {

        List<String> situacoes = itemService.listSituacao();
        return new ResponseEntity<>(new ApiResponse(true, situacoes), HttpStatus.OK);
    }
}
