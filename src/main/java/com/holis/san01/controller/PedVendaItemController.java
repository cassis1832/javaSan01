package com.holis.san01.controller;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.model.ApiResponse;
import com.holis.san01.model.PedVendaItem;
import com.holis.san01.model.VwPedVendaItem;
import com.holis.san01.services.PedVendaItemService;
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
 * Controller para tratamento dos itens do pedido de venda
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/pedis", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedVendaItemController {

    private final PedVendaItemService pedVendaItemService;

    /**
     * Ler um determinado item do pedido de venda pelo id
     */
    @GetMapping
    public ResponseEntity<PedVendaItem> getPedI(@RequestParam(name = "id", defaultValue = "") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(pedVendaItemService.lerPedVendaItem(id));
    }

    /**
     * Listar as linhas do pedido de vendas
     */
    @GetMapping("/list")
    public ResponseEntity<List<PedVendaItem>> listPedI(@RequestParam(name = "nrPedido", defaultValue = "0") Integer nrPedido) {
        List<PedVendaItem> pedVendaItem = pedVendaItemService.listarPedVendaItemByPedido(nrPedido);
        return new ResponseEntity<>(pedVendaItem, HttpStatus.OK);
    }

    /**
     * Listar a view de linhas do pedido de vendas
     */
    @GetMapping("/pageVwPedI")
    public ResponseEntity<Page<VwPedVendaItem>> pageVwPedI(
            @RequestParam(name = "status", defaultValue = "0") Integer status,
            @RequestParam(name = "filterText", defaultValue = "") String filterText,
            @PageableDefault(page = 0, size = 40)
            @SortDefault.SortDefaults({@SortDefault(sort = "codEntd, codItem")}) Pageable pageable) {
        Page<VwPedVendaItem> vwPedi = pedVendaItemService.listarVwPedVendaItem(status, filterText, pageable);
        return new ResponseEntity<>(vwPedi, HttpStatus.OK);
    }

    /**
     * Incluir um novo registro PEDVENDAITEM
     */
    @PostMapping
    public ResponseEntity<PedVendaItem> createPedI(@RequestBody @Valid PedVendaItem pedVendaItem) {
        pedVendaItem = pedVendaItemService.incluirPedVendaItem(pedVendaItem);
        return new ResponseEntity<>(pedVendaItem, HttpStatus.CREATED);
    }

    /**
     * Alterar um registro existente PEDVENDAITEM
     */
    @PutMapping
    public ResponseEntity<PedVendaItem> updatePedI(@RequestBody @Valid PedVendaItem pedVendaItem) {
        pedVendaItem = pedVendaItemService.alterarPedVendaItem(pedVendaItem);
        return new ResponseEntity<>(pedVendaItem, HttpStatus.OK);
    }

    /**
     * Verificar se o item pode ser deletado
     */
//    @GetMapping("/checkDelete")
//    public ResponseEntity<ApiResponse> checkDelete(@RequestParam(name = "codItem") String codItem) {
//        ApiResponse apiResponse = pedVendaItemService.checkDelete(codItem);
//        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
//    }

    /**
     * Excluir uma linha do pedido de venda
     */
    @DeleteMapping
    public ResponseEntity<?> deletePedI(@RequestParam(name = "id") Integer id) {
        try {
            pedVendaItemService.excluirPedVendaItem(id);
        } catch (Exception ex) {
            throw new ApiRequestException(ex.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
