package com.holis.san01.controller;

import com.holis.san01.mapper.PedItemMapper;
import com.holis.san01.model.PedItem;
import com.holis.san01.model.PedItemDTO;
import com.holis.san01.model.local.ApiResponse;
import com.holis.san01.model.local.FiltroPesquisa;
import com.holis.san01.services.PedItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller para tratamento dos itens do pedido de venda
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/pedis", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedItemController {

    private final PedItemService pedIService;
    private final PedItemMapper pedIMapper;

    /**
     * Ler um determinado item do pedido de venda pelo id
     */
    @GetMapping
    public ResponseEntity<ApiResponse> findPedItemById(
            @RequestParam(name = "id", defaultValue = "") Integer id) {

        PedItem pedItem = pedIService.findPedItemById(id);
        return new ResponseEntity<>(new ApiResponse(true, pedIMapper.toDTO(pedItem)), HttpStatus.OK);
    }

    /**
     * Listar as linhas do pedido de vendas
     */
    @GetMapping("/list")
    public ResponseEntity<ApiResponse> listVwPedItem(
            @ModelAttribute FiltroPesquisa filtroPesquisa) {

        var list = pedIService.listVwPedItem(filtroPesquisa);
        return new ResponseEntity<>(new ApiResponse(true, list), HttpStatus.OK);
    }

    /**
     * Listar a view de linhas do pedido de vendas
     */
    @GetMapping("/page")
    public ResponseEntity<ApiResponse> pageVwPedItem(
            @ModelAttribute FiltroPesquisa filtroPesquisa) {

        var page = pedIService.pageVwPedItem(filtroPesquisa);
        return new ResponseEntity<>(new ApiResponse(true, page), HttpStatus.OK);
    }

    /**
     * Incluir um novo registro PEDITEM
     */
    @PostMapping
    public ResponseEntity<ApiResponse> create(
            @RequestBody @Valid PedItemDTO pedItemDTO) {

        var pedItem = pedIService.create(pedIMapper.toEntity(pedItemDTO));
        return new ResponseEntity<>(new ApiResponse(true, pedIMapper.toDTO(pedItem)), HttpStatus.CREATED);
    }

    /**
     * Alterar um registro existente PEDITEM
     */
    @PutMapping
    public ResponseEntity<ApiResponse> update(
            @RequestBody @Valid PedItemDTO pedItemDTO) {

        var pedItem = pedIService.update(pedIMapper.toEntity(pedItemDTO));
        return new ResponseEntity<>(new ApiResponse(true, pedIMapper.toDTO(pedItem)), HttpStatus.OK);
    }

    /**
     * Verificar se o item pode ser deletado
     */
    @GetMapping("/checkDelete")
    public ResponseEntity<ApiResponse> checkDelete(
            @RequestParam(name = "id") int id) {

        pedIService.checkDelete(id);
        return new ResponseEntity<>(new ApiResponse(true, "Exclusão pode ser efetuada"), HttpStatus.OK);
    }

    /**
     * Excluir uma linha do pedido de venda
     */
    @DeleteMapping
    public ResponseEntity<ApiResponse> delete(
            @RequestParam(name = "id") Integer id) {

        pedIService.delete(id);
        return new ResponseEntity<>(new ApiResponse(true, "Item do pedido foi excluído com sucesso"), HttpStatus.OK);
    }
}
