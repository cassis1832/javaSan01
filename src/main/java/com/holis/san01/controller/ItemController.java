package com.holis.san01.controller;

import com.holis.san01.model.ItemDTO;
import com.holis.san01.model.TipoItem;
import com.holis.san01.model.VwItem;
import com.holis.san01.services.ItemService;
import com.holis.san01.services.TipoItemService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<ItemDTO> ler(
            @RequestParam(name = "codItem", defaultValue = "") String codItem) {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.ler(codItem));
    }

    /**
     * Ler uma lista de itens filtrando pelo nome/codigo e situacao
     */
    @GetMapping("/pages")
    public ResponseEntity<Page<VwItem>> listarItens(
            @RequestParam(name = "tipoItem", defaultValue = "") String tipoItem,
            @RequestParam(name = "archive", defaultValue = "N") String archive,
            @RequestParam(name = "filterText", defaultValue = "") String filterText,
            @PageableDefault(page = 0, size = 40)
            @SortDefault.SortDefaults({@SortDefault(sort = "codItem")}) Pageable pageable) {

        Page<VwItem> itens = itemService.listarPaging(tipoItem, archive, filterText, pageable);
        return new ResponseEntity<>(itens, HttpStatus.OK);
    }

    /**
     * Incluir um novo registro
     */
    @PostMapping
    public ResponseEntity<ItemDTO> incluir(
            @RequestBody @Valid ItemDTO itemDTO) {

        itemDTO = itemService.incluir(itemDTO);
        return new ResponseEntity<>(itemDTO, HttpStatus.CREATED);
    }

    /**
     * Alterar um registro existente
     */
    @PutMapping
    public ResponseEntity<ItemDTO> alterar(
            @RequestBody @Valid ItemDTO itemDTO) {

        itemDTO = itemService.alterar(itemDTO);
        return new ResponseEntity<>(itemDTO, HttpStatus.OK);
    }

    /**
     * Excluir um registro
     */
    @DeleteMapping
    public ResponseEntity<?> excluir(
            @RequestParam(name = "codItem") String codItem) {

        itemService.excluir(codItem);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/tpItems")
    public ResponseEntity<List<TipoItem>> listarTipoItem() {
        List<TipoItem> tipos = tipoItemService.listar();
        return new ResponseEntity<>(tipos, HttpStatus.OK);
    }

    /**
     * Ler uma lista das familias dos itens
     */
    @GetMapping("/familias")
    public ResponseEntity<List<String>> listarFamilias() {

        List<String> familias = itemService.listarFamilias();
        return new ResponseEntity<>(familias, HttpStatus.OK);
    }

    /**
     * Ler uma lista de itens filtrando pelo nome/codigo e situacao
     */
    @GetMapping("/situacoes")
    public ResponseEntity<List<String>> listarSituacoes() {

        List<String> situacoes = itemService.listarSituacoes();
        return new ResponseEntity<>(situacoes, HttpStatus.OK);
    }
}
