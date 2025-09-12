package com.holis.san01.controller;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.mapper.PedVendaItemMapper;
import com.holis.san01.model.ApiResponse;
import com.holis.san01.model.PedVendaItem;
import com.holis.san01.model.PedVendaItemDTO;
import com.holis.san01.model.VwPedVendaItem;
import com.holis.san01.services.PedVendaItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller para tratamento dos itens do pedido de venda
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/pedis", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedVendaItemController {

    private final PedVendaItemService pedIService;
    private final PedVendaItemMapper pedIMapper;

    /**
     * Ler um determinado item do pedido de venda pelo id
     */
    @GetMapping
    public ResponseEntity<ApiResponse> getPedVendaItem(
            @RequestParam(name = "id", defaultValue = "") Integer id) {

        PedVendaItem pedVendaItem = pedIService.getPedVendaItem(id);
        return new ResponseEntity<>(new ApiResponse(true, pedIMapper.toDTO(pedVendaItem)), HttpStatus.OK);
    }

    /**
     * Listar as linhas do pedido de vendas
     */
    @GetMapping("/list")
    public ResponseEntity<ApiResponse> listPedVendaItem(
            @RequestParam(name = "nrPedido", defaultValue = "0") Integer nrPedido) {

        List<PedVendaItem> pedVendaItems = pedIService.listPedVendaItem(nrPedido);
        return new ResponseEntity<>(new ApiResponse(true, pedVendaItems), HttpStatus.OK);
    }

    /**
     * Listar a view de linhas do pedido de vendas
     */
    @GetMapping("/pageVwPedI")
    public ResponseEntity<ApiResponse> pageVwPedVendaItem(
            @RequestParam(name = "status", defaultValue = "0") Integer status,
            @RequestParam(name = "filterText", defaultValue = "") String filterText,
            @PageableDefault(page = 0, size = 40)
            @SortDefault.SortDefaults({@SortDefault(sort = "codEntd, codItem")}) Pageable pageable) {

        Page<VwPedVendaItem> vwPedi = pedIService.pageVwPedVendaItem(status, filterText, pageable);
        return new ResponseEntity<>(new ApiResponse(true, vwPedi), HttpStatus.OK);
    }

    /**
     * Incluir um novo registro PEDVENDAITEM
     */
    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid PedVendaItemDTO pedVendaItemDTO) {

        var pedVendaItem = pedIService.create(pedIMapper.toEntity(pedVendaItemDTO));
        return new ResponseEntity<>(new ApiResponse(true, pedIMapper.toDTO(pedVendaItem)), HttpStatus.CREATED);
    }

    /**
     * Alterar um registro existente PEDVENDAITEM
     */
    @PutMapping
    public ResponseEntity<ApiResponse> update(@RequestBody @Valid PedVendaItemDTO pedVendaItemDTO) {

        var pedVendaItem = pedIService.update(pedIMapper.toEntity(pedVendaItemDTO));
        return new ResponseEntity<>(new ApiResponse(true, pedIMapper.toDTO(pedVendaItem)), HttpStatus.OK);
    }

    /**
     * Verificar se o item pode ser deletado
     */
    @GetMapping("/checkDelete")
    public ResponseEntity<ApiResponse> checkDelete(@RequestParam(name = "id") int id) {

        return new ResponseEntity<>(new ApiResponse(true, "Exclusão pode ser efetuada"), HttpStatus.OK);
    }

    /**
     * Excluir uma linha do pedido de venda
     */
    @DeleteMapping
    public ResponseEntity<ApiResponse> delete(@RequestParam(name = "id") Integer id) {

        try {
            pedIService.delete(id);
        } catch (Exception ex) {
            throw new ApiRequestException(ex.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
