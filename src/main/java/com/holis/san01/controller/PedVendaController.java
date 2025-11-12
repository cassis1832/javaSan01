package com.holis.san01.controller;

import com.holis.san01.mapper.PedVendaMapper;
import com.holis.san01.model.PedVenda;
import com.holis.san01.model.PedVendaDto;
import com.holis.san01.model.VwPedVenda;
import com.holis.san01.model.local.ApiResponse02;
import com.holis.san01.services.PedVendaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller para tratamento de Pedidos de Vendas
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/pedvs", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedVendaController implements BaseController<PedVendaDto, Integer, VwPedVenda> {

    private final PedVendaService pedVService;
    private final PedVendaMapper pedVMapper;

    @Override
    @GetMapping
    public ResponseEntity<ApiResponse02<PedVendaDto>> buscarPorId(
            @RequestParam(name = "id") Integer id) {

        return pedVService.findById(id)
                .map(entidade -> ResponseEntity.ok(ApiResponse02.success(pedVMapper.toDto(entidade))))
                .orElse(ResponseEntity.status(200).body(ApiResponse02.errorMessage("Item não encontrado")));
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse02<PedVendaDto>> criar(
            @RequestBody @Valid PedVendaDto dto) {
        PedVenda salvo = pedVService.save(pedVMapper.toEntity(dto));
        PedVendaDto salvoDTO = pedVMapper.toDto(salvo);
        return ResponseEntity.ok(ApiResponse02.success(salvoDTO, "Item criado com sucesso"));
    }

    @Override
    @PutMapping
    public ResponseEntity<ApiResponse02<PedVendaDto>> alterar(
            @RequestBody @Valid PedVendaDto dto) {
        PedVenda salvo = pedVService.update(pedVMapper.toEntity(dto));
        PedVendaDto salvoDTO = pedVMapper.toDto(salvo);
        return ResponseEntity.ok(ApiResponse02.success(salvoDTO, "Item alterado com sucesso"));
    }

    @Override
    @DeleteMapping
    public ResponseEntity<ApiResponse02<Void>> excluir(
            @RequestParam(name = "id") Integer id) {
        pedVService.deleteById(id);
        return ResponseEntity.ok(ApiResponse02.success("Item excluído sucesso"));
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<ApiResponse02<List<PedVendaDto>>> buscarLista(
            @RequestParam(required = false) Map<String, String> filtros) {
        List<PedVenda> entidades = pedVService.findList(filtros);
        List<PedVendaDto> dtos = pedVMapper.toDtoList(entidades);
        return ResponseEntity.ok(ApiResponse02.success(dtos, "Lista de Itens"));
    }

    @Override
    @GetMapping("/page")
    public ResponseEntity<ApiResponse02<Page<VwPedVenda>>> buscarPagina(
            Pageable pageable,
            @RequestParam(required = false) Map<String, String> filtros) {
        Page<VwPedVenda> pagina = pedVService.findPage(pageable, filtros);
        return ResponseEntity.ok(ApiResponse02.success(pagina, "Pagina de VwPedVenda"));
    }

    @PutMapping("/archive")
    public ResponseEntity<ApiResponse02<Void>> arquivar(
            @RequestParam(name = "nrPedido", defaultValue = "") Integer nrPedido) {
        pedVService.archive(nrPedido);
        return ResponseEntity.ok(ApiResponse02.success("Item arquivado com sucesso"));
    }

    @PutMapping("/unarchive")
    public ResponseEntity<ApiResponse02<Void>> desarquivar(
            @RequestParam(name = "nrPedido", defaultValue = "") Integer nrPedido) {
        pedVService.unarchive(nrPedido);
        return ResponseEntity.ok(ApiResponse02.success("Item desarquivado com sucesso"));
    }
}
