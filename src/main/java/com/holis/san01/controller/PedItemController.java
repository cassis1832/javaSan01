package com.holis.san01.controller;

import com.holis.san01.dto.ApiResponse;
import com.holis.san01.mapper.PedItemMapper;
import com.holis.san01.model.PedItem;
import com.holis.san01.model.PedItemDto;
import com.holis.san01.model.VwPedItem;
import com.holis.san01.services.PedItemService;
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
 * Controller para tratamento dos itens do pedido de venda
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/pedis", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedItemController implements BaseController<PedItemDto, Integer, VwPedItem> {

    private final PedItemService pedItemService;
    private final PedItemMapper pedItemMapper;

    @Override
    @GetMapping
    public ResponseEntity<ApiResponse<PedItemDto>> getById(@PathVariable Integer id) {
        PedItem pedItem = pedItemService.findById(id);
        return ResponseEntity.ok(
                ApiResponse.success(pedItemMapper.toDto(pedItem))
        );
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<PedItemDto>> create(@RequestBody @Valid PedItemDto dto) {
        PedItem pedItem = pedItemService.create(pedItemMapper.toEntity(dto));
        PedItemDto pedItemDto = pedItemMapper.toDto(pedItem);
        return ResponseEntity.ok(
                ApiResponse.success(pedItemDto, "Item criado com sucesso")
        );
    }

    @Override
    @PutMapping
    public ResponseEntity<ApiResponse<PedItemDto>> update(@RequestBody @Valid PedItemDto dto) {
        PedItem pedItem = pedItemService.update(pedItemMapper.toEntity(dto));
        PedItemDto pedItemDto = pedItemMapper.toDto(pedItem);
        return ResponseEntity.ok(
                ApiResponse.success(pedItemDto, "Item alterado com sucesso")
        );
    }

    @Override
    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
        pedItemService.deleteById(id);
        return ResponseEntity.ok(
                ApiResponse.success("Item excluído sucesso")
        );
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<PedItemDto>>> getList(@RequestParam(required = false)
                                                                 Map<String, String> filtros) {
        List<PedItem> pedIs = pedItemService.findList(filtros);
        List<PedItemDto> dtos = pedItemMapper.toDtoList(pedIs);
        return ResponseEntity.ok(
                ApiResponse.success(dtos, "Lista de Itens")
        );
    }

    @Override
    @GetMapping("/page")
    public ResponseEntity<ApiResponse<Page<VwPedItem>>> getPage(Pageable pageable,
                                                                @RequestParam(required = false) Map<String, String> filtros) {
        Page<VwPedItem> pagina = pedItemService.findPage(pageable, filtros);
        return ResponseEntity.ok(
                ApiResponse.success(pagina, "Pagina de PedItems")
        );
    }

    @PutMapping("/archive")
    public ResponseEntity<ApiResponse<Void>> archive(@PathVariable Integer id) {
        pedItemService.archive(id);
        return ResponseEntity.ok(
                ApiResponse.success("Cliente/fornecedor arquivado com sucesso")
        );
    }

    @PutMapping("/unarchive")
    public ResponseEntity<ApiResponse<Void>> unarchive(@PathVariable Integer id) {
        pedItemService.unarchive(id);
        return ResponseEntity.ok(
                ApiResponse.success("Cliente/fornecedor desarquivado com sucesso")
        );
    }
}
