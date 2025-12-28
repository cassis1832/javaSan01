package com.holis.san01.controller;

import com.holis.san01.dto.ApiResponse;
import com.holis.san01.mapper.PedVendaMapper;
import com.holis.san01.model.PedVenda;
import com.holis.san01.model.PedVendaDto;
import com.holis.san01.model.VwPedVenda;
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
    public ResponseEntity<ApiResponse<PedVendaDto>> getById(@PathVariable Integer id) {
        PedVenda pedVenda = pedVService.findById(id);
        return ResponseEntity.ok(
                ApiResponse.success(pedVMapper.toDto(pedVenda))
        );
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<PedVendaDto>> create(@RequestBody @Valid PedVendaDto dto) {
        PedVenda salvo = pedVService.create(pedVMapper.toEntity(dto));
        PedVendaDto salvoDTO = pedVMapper.toDto(salvo);
        return ResponseEntity.ok(
                ApiResponse.success(salvoDTO, "Pedido criado com sucesso")
        );
    }

    @Override
    @PutMapping
    public ResponseEntity<ApiResponse<PedVendaDto>> update(@RequestBody @Valid PedVendaDto dto) {
        PedVenda salvo = pedVService.update(pedVMapper.toEntity(dto));
        PedVendaDto salvoDTO = pedVMapper.toDto(salvo);
        return ResponseEntity.ok(
                ApiResponse.success(salvoDTO, "Pedido alterado com sucesso")
        );
    }

    @Override
    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
        pedVService.deleteById(id);
        return ResponseEntity.ok(
                ApiResponse.success("Pedido excluído sucesso")
        );
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<PedVendaDto>>> getList(@RequestParam(required = false) Map<String, String> filtros) {
        List<PedVenda> entidades = pedVService.findList(filtros);
        List<PedVendaDto> dtos = pedVMapper.toDtoList(entidades);
        return ResponseEntity.ok(
                ApiResponse.success(dtos, "Lista de pedidos")
        );
    }

    @Override
    @GetMapping("/page")
    public ResponseEntity<ApiResponse<Page<VwPedVenda>>> getPage(Pageable pageable,
                                                                 @RequestParam(required = false) Map<String, String> filtros) {
        Page<VwPedVenda> pagina = pedVService.findPage(pageable, filtros);
        return ResponseEntity.ok(
                ApiResponse.success(pagina, "Pagina de pedidos")
        );
    }

    @PutMapping("/archive")
    public ResponseEntity<ApiResponse<Void>> archive(@PathVariable Integer id) {
        pedVService.archive(id);
        return ResponseEntity.ok(
                ApiResponse.success("Pedido arquivado com sucesso")
        );
    }

    @PutMapping("/unarchive")
    public ResponseEntity<ApiResponse<Void>> unarchive(@PathVariable Integer id) {
        pedVService.unarchive(id);
        return ResponseEntity.ok(
                ApiResponse.success("Pedido desarquivado com sucesso")
        );
    }
}
