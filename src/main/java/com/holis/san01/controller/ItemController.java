package com.holis.san01.controller;

import com.holis.san01.exceptions.ApiDeleteException;
import com.holis.san01.model.ItemDTO;
import com.holis.san01.services.ItemService;
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

/**
 * Controller para tratamento de Itens
 */
@RestController
@RequestMapping(value = "/api/items", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * Ler um determinado registro pelo código
     */
    @GetMapping("/ler")
    public ResponseEntity<ItemDTO> lerItem(
            @RequestParam(name = "codItem", defaultValue = "") String codItem) {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.lerItem(codItem));
    }

    /**
     * Ler uma lista de itens filtrando pelo nome/codigo e situacao
     */
    @GetMapping("/listarPag")
    public ResponseEntity<Page<ItemDTO>> listarItens(
            @RequestParam(name = "tipoItem", defaultValue = "") String tipoItem,
            @RequestParam(name = "archive", defaultValue = "N") String archive,
            @RequestParam(name = "filterText", defaultValue = "") String filterText,
            @PageableDefault(page = 0, size = 40)
            @SortDefault.SortDefaults({@SortDefault(sort = "codItem")}) Pageable pageable) {
        Page<ItemDTO> itens = itemService.listarItens(tipoItem, archive, filterText, pageable);
        return new ResponseEntity<>(itens, HttpStatus.OK);
    }

    /**
     * Incluir um novo registro
     */
    @PostMapping("/incluir")
    public ResponseEntity<ItemDTO> incluirItem(@RequestBody @Valid ItemDTO itemDTO) {
        itemDTO = itemService.incluirItem(itemDTO);
        return new ResponseEntity<>(itemDTO, HttpStatus.CREATED);
    }

    /**
     * Alterar um registro existente
     */
    @PutMapping("/alterar")
    public ResponseEntity<ItemDTO> alterarItem(@RequestBody @Valid ItemDTO itemDTO) {
        itemDTO = itemService.alterarItem(itemDTO);
        return new ResponseEntity<>(itemDTO, HttpStatus.OK);
    }

    /**
     * Excluir um registro
     */
    @DeleteMapping("/excluir")
    public ResponseEntity<?> excluirItem(@RequestParam(name = "codItem") String codItem) {
        try {
            itemService.excluirItem(codItem);
        } catch (Exception ex) {
            throw new ApiDeleteException(ex.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
