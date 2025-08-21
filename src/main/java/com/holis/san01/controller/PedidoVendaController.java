package com.holis.san01.controller;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.model.PedVenda;
import com.holis.san01.model.PedVendaItem;
import com.holis.san01.model.VwPedVendaItem;
import com.holis.san01.services.PedidoVendaService;
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
 * Controller para tratamento de Pedidos de Vendas
 */
@RestController
@RequestMapping(value = "/api/pedvs", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoVendaController {

    @Autowired
    private PedidoVendaService pedidoVendaService;

    /**
     * Ler um determinado pedido de venda pelo nr_pedido
     * Usado para manutenção do registro
     */
    @GetMapping(value = "/lerPedV")
    public ResponseEntity<PedVenda> lerPedV(
            @RequestParam(name = "nrPedido", defaultValue = "") Long nrPedido) {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoVendaService.lerPedVenda(nrPedido));
    }

    /**
     * Ler um determinado item do pedido de venda pelo id
     * Usado para manutenção do registro
     */
    @GetMapping("/lerPedI")
    public ResponseEntity<PedVendaItem> lerPedI(
            @RequestParam(name = "id", defaultValue = "") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoVendaService.lerPedVendaItem(id));
    }

    /**
     * Listar as linhas do pedido de vendas
     */
    @GetMapping("/listarPedI")
    public ResponseEntity<List<PedVendaItem>> listarPedI(
            @RequestParam(name = "nrPedido", defaultValue = "0") Long nrPedido) {
        List<PedVendaItem> pedVendaItem = pedidoVendaService.listarPedVendaItem(nrPedido);
        return new ResponseEntity<>(pedVendaItem, HttpStatus.OK);
    }

    /**
     * Listar a view de linhas do pedido de vendas
     */
    @GetMapping("/listarVwPedI")
    public ResponseEntity<Page<VwPedVendaItem>> listarVwPedI(
            @RequestParam(name = "archive", defaultValue = "N") String archive,
            @RequestParam(name = "filterText", defaultValue = "") String filterText,
            @PageableDefault(page = 0, size = 40)
            @SortDefault.SortDefaults({@SortDefault(sort = "codEntd, codItem")}) Pageable pageable) {
        Page<VwPedVendaItem> vwPedi = pedidoVendaService.listarVwPedVendaItem(archive, filterText, pageable);
        return new ResponseEntity<>(vwPedi, HttpStatus.OK);
    }

    /**
     * Incluir um novo registro PEDVENDA
     */
    @PostMapping("/incluirPedV")
    public ResponseEntity<PedVenda> incluirPedV(
            @RequestBody @Valid PedVenda pedVenda) {
        pedVenda = pedidoVendaService.incluirPedVenda(pedVenda);
        return new ResponseEntity<>(pedVenda, HttpStatus.CREATED);
    }

    /**
     * Incluir um novo registro PEDVENDAITEM
     */
    @PostMapping("/incluirPedI")
    public ResponseEntity<PedVendaItem> incluirPedI(
            @RequestBody @Valid PedVendaItem pedVendaItem) {
        pedVendaItem = pedidoVendaService.incluirPedVendaItem(pedVendaItem);
        return new ResponseEntity<>(pedVendaItem, HttpStatus.CREATED);
    }

    /**
     * Alterar um registro existente PEDVENDA
     */
    @PutMapping("/alterarPedV")
    public ResponseEntity<PedVenda> alterarPedV(
            @RequestBody @Valid PedVenda pedVenda) {
        pedVenda = pedidoVendaService.alterarPedVenda(pedVenda);
        return new ResponseEntity<>(pedVenda, HttpStatus.OK);
    }

    /**
     * Alterar um registro existente PEDVENDAITEM
     */
    @PutMapping("/alterarPedI")
    public ResponseEntity<PedVendaItem> alterarPedI(
            @RequestBody @Valid PedVendaItem pedVendaItem) {
        pedVendaItem = pedidoVendaService.alterarPedVendaItem(pedVendaItem);
        return new ResponseEntity<>(pedVendaItem, HttpStatus.OK);
    }

    /**
     * Excluir um pedido de venda
     */
    @DeleteMapping("/excluirPedV")
    public ResponseEntity<?> excluirPedV(
            @RequestParam(name = "nrPedido") Long nrPedido) {
        try {
            pedidoVendaService.excluirPedVenda(nrPedido);
        } catch (Exception ex) {
            throw new ApiRequestException(ex.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Excluir um pedido de venda
     */
    @DeleteMapping("/excluirPedI")
    public ResponseEntity<?> excluirPedI(
            @RequestParam(name = "id") Integer id) {
        try {
            pedidoVendaService.excluirPedVendaItem(id);
        } catch (Exception ex) {
            throw new ApiRequestException(ex.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Obter o próximo numero de pedido de venda - nr_pedido
     */
    @GetMapping("/proximocodigo")
    public ResponseEntity<Integer> obterProximoCodigo() {
        Integer proximoCodigo = (Integer) pedidoVendaService.obterProximoCodigo();
        if (proximoCodigo == null) {
            throw new ApiRequestException("Retornando null no proximo código");
        }
        return new ResponseEntity<>(proximoCodigo, HttpStatus.OK);
    }
}
