package com.holis.san01.controller;

import com.holis.san01.dto.ApiResponse;
import com.holis.san01.dto.ItemDto;
import com.holis.san01.mapper.ItemMapper;
import com.holis.san01.model.Item;
import com.holis.san01.model.TipoItem;
import com.holis.san01.model.VwItem;
import com.holis.san01.services.ItemService;
import com.holis.san01.services.TipoItemService;
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
 * Controller para tratamento de Itens
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/items", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemController implements BaseController<ItemDto, String, VwItem> {

    private final ItemService itemService;
    private final TipoItemService tipoItemService;
    private final ItemMapper itemMapper;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ItemDto>> findByID(@PathVariable String id) {
        Item item = itemService.findById(id);
        return ResponseEntity.ok(
                ApiResponse.success(itemMapper.toDto(item))
        );
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<ItemDto>> create(@RequestBody @Valid ItemDto dto) {
        Item item = itemService.create(itemMapper.toEntity(dto));
        ItemDto itemDto = itemMapper.toDto(item);
        return ResponseEntity.ok(
                ApiResponse.success(itemDto, "Item criado com sucesso")
        );
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ItemDto>> update(
            @PathVariable String id,
            @RequestBody @Valid ItemDto dto) {
        Item salvo = itemService.update(itemMapper.toEntity(dto));
        ItemDto salvoDTO = itemMapper.toDto(salvo);
        return ResponseEntity.ok(
                ApiResponse.success(salvoDTO, "Item alterado com sucesso")
        );
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        itemService.delete(id);
        return ResponseEntity.ok(
                ApiResponse.success("Item excluído sucesso")
        );
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<VwItem>>> findAll(
            @RequestParam(required = false) Map<String, String> filtros) {
        List<VwItem> vwItems = itemService.findAll(filtros);
        return ResponseEntity.ok(
                ApiResponse.success(vwItems, "Lista de Itens")
        );
    }

    @Override
    @GetMapping("/page")
    public ResponseEntity<ApiResponse<Page<VwItem>>> findPage(
            Pageable pageable,
            @RequestParam(required = false) Map<String, String> filtros) {
        Page<VwItem> pagina = itemService.findPage(pageable, filtros);
        return ResponseEntity.ok(
                ApiResponse.success(pagina, "Pagina de VwItem")
        );
    }

    @PatchMapping("/{id}/arquivar")
    public ResponseEntity<ApiResponse<Void>> arquivar(@PathVariable String id) {
        itemService.arquivar(id);
        return ResponseEntity.ok(
                ApiResponse.success("Item arquivado com sucesso")
        );
    }

    @PatchMapping("/{id}/desarquivar")
    public ResponseEntity<ApiResponse<Void>> desarquivar(@PathVariable String id) {
        itemService.desarquivar(id);
        return ResponseEntity.ok(
                ApiResponse.success("Item desarquivado com sucesso")
        );
    }

    @GetMapping("/tpItems")
    public ResponseEntity<ApiResponse<List<TipoItem>>> listTipoItem() {
        List<TipoItem> tipoItems = tipoItemService.listTipoItem();
        return ResponseEntity.ok(
                ApiResponse.success(tipoItems, "Lista de tipos de itens")
        );
    }

    @GetMapping("/familias")
    public ResponseEntity<ApiResponse<List<String>>> listFamilia() {
        List<String> familias = itemService.listFamilia();
        return ResponseEntity.ok(
                ApiResponse.success(familias, "Lista de familias")
        );
    }

    @GetMapping("/situacoes")
    public ResponseEntity<ApiResponse<List<String>>> listSituacao() {
        List<String> situacoes = itemService.listSituacao();
        return ResponseEntity.ok(
                ApiResponse.success(situacoes, "Lista de situações")
        );
    }
}
