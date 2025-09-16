package com.holis.san01.controller;

import com.holis.san01.mapper.PedVendaMapper;
import com.holis.san01.model.ApiResponse;
import com.holis.san01.model.PedVenda;
import com.holis.san01.model.PedVendaDTO;
import com.holis.san01.model.VwPedVenda;
import com.holis.san01.services.PedVendaService;
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

/**
 * Controller para tratamento de Pedidos de Vendas
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/pedvs", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedVendaController {

    private final PedVendaService pedVService;
    private final PedVendaMapper pedVMapper;

    /**
     * Ler um determinado pedido de venda pelo nr_pedido
     */
    @GetMapping
    public ResponseEntity<ApiResponse> findPedVendaByNrPedido(
            @RequestParam(name = "nrPedido", defaultValue = "") Integer nrPedido) {

        PedVenda pedVenda = pedVService.findPedVendaByNrPedido(nrPedido);
        return new ResponseEntity<>(new ApiResponse(true, pedVMapper.toDTO(pedVenda)), HttpStatus.OK);
    }

    /**
     * Ler uma lista dos pedidos de vendas
     */
    @GetMapping("/page")
    public ResponseEntity<ApiResponse> pageVwPedVenda(
            @RequestParam(name = "status", defaultValue = "0") Integer status,
            @RequestParam(name = "filterText", defaultValue = "") String filterText,
            @PageableDefault(size = 40)
            @SortDefault.SortDefaults({@SortDefault(sort = "numPedido")}) Pageable pageable) {

        Page<VwPedVenda> vwPedVendas = pedVService.pageVwPedVenda(status, filterText, pageable);
        return new ResponseEntity<>(new ApiResponse(true, vwPedVendas), HttpStatus.OK);
    }

    /**
     * Incluir um novo registro PEDVENDA
     */
    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid PedVendaDTO pedVendaDTO) {

        PedVenda pedVenda = pedVService.create(pedVMapper.toEntity(pedVendaDTO));
        return new ResponseEntity<>(new ApiResponse(true, pedVMapper.toDTO(pedVenda)), HttpStatus.CREATED);
    }

    /**
     * Alterar um registro existente PEDVENDA
     */
    @PutMapping
    public ResponseEntity<ApiResponse> update(@RequestBody @Valid PedVendaDTO pedVendaDTO) {

        PedVenda pedVenda = pedVService.update(pedVMapper.toEntity(pedVendaDTO));
        return new ResponseEntity<>(new ApiResponse(true, pedVMapper.toDTO(pedVenda)), HttpStatus.OK);
    }

    /**
     * Verificar se o item pode ser deletado
     */
    @GetMapping("/checkDelete")
    public ResponseEntity<ApiResponse> checkDelete(@RequestParam(name = "numPedido") int numPedido) {

        pedVService.checkDelete(numPedido);
        return new ResponseEntity<>(new ApiResponse(true, "Exclusão pode ser efetuada"), HttpStatus.OK);
    }

    /**
     * Excluir um pedido de venda
     */
    @DeleteMapping
    public ResponseEntity<ApiResponse> delete(@RequestParam(name = "nrPedido") Integer nrPedido) {

        pedVService.delete(nrPedido);
        return new ResponseEntity<>(new ApiResponse(true, "Pedido excluído com sucesso"), HttpStatus.OK);
    }

    /**
     * Confirmar o orçamento transformando em pedido
     */
    @PutMapping("/confirm")
    public ResponseEntity<ApiResponse> confirm(@RequestParam(name = "nrPedido") Integer nrPedido) {

        PedVenda pedVenda = pedVService.confirm(nrPedido);
        return new ResponseEntity<>(new ApiResponse(true, pedVMapper.toDTO(pedVenda)), HttpStatus.OK);
    }

    @PutMapping("/cancel")
    public ResponseEntity<ApiResponse> cancel(@RequestParam(name = "nrPedido") Integer nrPedido) {

        PedVenda pedVenda = pedVService.cancel(nrPedido);
        return new ResponseEntity<>(new ApiResponse(true, pedVMapper.toDTO(pedVenda)), HttpStatus.OK);
    }

    @PutMapping("/archive")
    public ResponseEntity<ApiResponse> archive(@RequestParam(name = "nrPedido") Integer nrPedido) {

        pedVService.archive(nrPedido);
        return new ResponseEntity<>(new ApiResponse(true, "Pedido arquivado"), HttpStatus.OK);
    }

    @PutMapping("/unarchive")
    public ResponseEntity<ApiResponse> unarchive(@RequestParam(name = "nrPedido") Integer nrPedido) {

        pedVService.unarchive(nrPedido);
        return new ResponseEntity<>(new ApiResponse(true, "Pedido desarquivado"), HttpStatus.OK);
    }
}
