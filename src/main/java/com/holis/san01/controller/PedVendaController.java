package com.holis.san01.controller;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.model.ApiResponse;
import com.holis.san01.model.PedVenda;
import com.holis.san01.model.VwPedVenda;
import com.holis.san01.services.PedVendaService;
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
 * Controller para tratamento de Pedidos de Vendas
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/pedvs", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedVendaController {

    private final PedVendaService pedVendaService;

    /**
     * Ler um determinado pedido de venda pelo nr_pedido
     */
    @GetMapping
    public ResponseEntity<ApiResponse> getPedV(@RequestParam(name = "nrPedido", defaultValue = "") Integer nrPedido) {

        PedVenda pedVenda = pedVendaService.getPedVenda(nrPedido);
        return new ResponseEntity<>(new ApiResponse(true, pedVenda), HttpStatus.OK);
    }

    /**
     * Ler uma lista dos pedidos de vendas
     */
    @GetMapping("/pages")
    public ResponseEntity<ApiResponse> pageVwPedV(
            @RequestParam(name = "status", defaultValue = "0") Integer status,
            @RequestParam(name = "filterText", defaultValue = "") String filterText,
            @PageableDefault(page = 0, size = 40)
            @SortDefault.SortDefaults({@SortDefault(sort = "numPedido")}) Pageable pageable) {

        Page<VwPedVenda> vwPedVendas = pedVendaService.pageVwPedVenda(status, filterText, pageable);
        return new ResponseEntity<>(new ApiResponse(true, vwPedVendas), HttpStatus.OK);
    }

    /**
     * Incluir um novo registro PEDVENDA
     */
    @PostMapping
    public ResponseEntity<ApiResponse> createPedV(@RequestBody @Valid PedVenda pedVenda) {

        PedVenda pedVenda1 = pedVendaService.incluirPedVenda(pedVenda);
        return new ResponseEntity<>(new ApiResponse(true, pedVenda1), HttpStatus.CREATED);
    }

    /**
     * Alterar um registro existente PEDVENDA
     */
    @PutMapping
    public ResponseEntity<ApiResponse> updatePedV(@RequestBody @Valid PedVenda pedVenda) {

        PedVenda pedVenda1 = pedVendaService.alterarPedVenda(pedVenda);
        return new ResponseEntity<>(new ApiResponse(true, pedVenda1), HttpStatus.OK);
    }

    /**
     * Verificar se o item pode ser deletado
     */
    @GetMapping("/checkDelete")
    public ResponseEntity<ApiResponse> checkDelete(@RequestParam(name = "numPedido") String numPedido) {

        pedVendaService.checkDelete(numPedido);
        return new ResponseEntity<>(new ApiResponse(true, "Exclusão pode ser efetuada"), HttpStatus.OK);
    }

    /**
     * Excluir um pedido de venda
     */
    @DeleteMapping
    public ResponseEntity<ApiResponse> deletePedV(@RequestParam(name = "nrPedido") Integer nrPedido) {

        try {
            pedVendaService.excluirPedVenda(nrPedido);
        } catch (Exception ex) {
            throw new ApiRequestException(ex.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
