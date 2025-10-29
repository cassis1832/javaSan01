package com.holis.san01.controller;

import com.holis.san01.mapper.ItemMapper;
import com.holis.san01.model.Item;
import com.holis.san01.model.ItemDTO;
import com.holis.san01.model.TipoItem;
import com.holis.san01.model.local.ApiResponse;
import com.holis.san01.model.local.FiltroPesquisa;
import com.holis.san01.services.ItemService;
import com.holis.san01.services.TipoItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private final ItemMapper itemMapper;

    /**
     * Ler um determinado registro pelo código
     */
    @GetMapping
    public ResponseEntity<ApiResponse> findItemByCodItem(
            @RequestParam(name = "codItem", defaultValue = "") String codItem) {

        Item item = itemService.findItemByCodItem(codItem);
        return new ResponseEntity<>(new ApiResponse(true, itemMapper.toDTO(item)), HttpStatus.OK);
    }

    /**
     * Ler uma lista de itens filtrando pelo nome/codigo e situacao
     */
    @GetMapping("/page")
    public ResponseEntity<ApiResponse> pageVwItem(
            @ModelAttribute FiltroPesquisa filtroPesquisa) {

        var page = itemService.pageVwItem(filtroPesquisa);
        return new ResponseEntity<>(new ApiResponse(true, page), HttpStatus.OK);
    }

    /**
     * Incluir um novo registro
     */
    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid ItemDTO dto) {

        Item item = itemService.create(itemMapper.toEntity(dto));
        return new ResponseEntity<>(new ApiResponse(true, itemMapper.toDTO(item)), HttpStatus.CREATED);
    }

    /**
     * Alterar um registro existente
     */
    @PutMapping
    public ResponseEntity<ApiResponse> update(@RequestBody @Valid ItemDTO dto) {

        Item item = itemService.update(itemMapper.toEntity(dto));
        return new ResponseEntity<>(new ApiResponse(true, itemMapper.toDTO(item)), HttpStatus.OK);
    }

    @PutMapping("/archive")
    public ResponseEntity<ApiResponse> archive(
            @RequestParam(name = "codItem", defaultValue = "") String codItem) {

        itemService.archive(codItem);
        return new ResponseEntity<>(new ApiResponse(true, "Item arquivado"), HttpStatus.OK);
    }

    @PutMapping("/unarchive")
    public ResponseEntity<ApiResponse> unarchive(
            @RequestParam(name = "codItem", defaultValue = "") String codItem) {

        itemService.unarchive(codItem);
        return new ResponseEntity<>(new ApiResponse(true, "Item desarquivado"), HttpStatus.OK);
    }

    /**
     * Verificar se o item pode ser deletado
     */
    @GetMapping("/checkDelete")
    public ResponseEntity<ApiResponse> checkDelete(@RequestParam(name = "codItem") String codItem) {

        itemService.checkDelete(codItem);
        return new ResponseEntity<>(new ApiResponse(true, "Item pode ser excluído"), HttpStatus.OK);
    }

    /**
     * Excluir um registro
     */
    @DeleteMapping
    public ResponseEntity<ApiResponse> delete(@RequestParam(name = "codItem") String codItem) {

        itemService.delete(codItem);
        return new ResponseEntity<>(new ApiResponse(true, "Item excluído com sucesso"), HttpStatus.OK);
    }

    @GetMapping("/tpItems")
    public ResponseEntity<ApiResponse> listTipoItem() {

        List<TipoItem> tipoItems = tipoItemService.listTipoItem();
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

}
