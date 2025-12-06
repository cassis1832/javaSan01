package com.holis.san01.controller;

import com.holis.san01.mapper.ItemMapper;
import com.holis.san01.model.Item;
import com.holis.san01.model.ItemDto;
import com.holis.san01.model.TipoItem;
import com.holis.san01.model.VwItem;
import com.holis.san01.model.local.ApiResponse02;
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
    @GetMapping
    public ResponseEntity<ApiResponse02<ItemDto>> buscarPorId(
            @RequestParam(name = "id") String id) {
        return itemService.findById(id)
                .map(entidade -> ResponseEntity.ok(ApiResponse02.success(itemMapper.toDto(entidade))))
                .orElse(ResponseEntity.status(200).body(ApiResponse02.errorMessage("Item não encontrado")));
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse02<ItemDto>> criar(
            @RequestBody @Valid ItemDto dto) {
        Item salvo = itemService.save(itemMapper.toEntity(dto));
        ItemDto salvoDTO = itemMapper.toDto(salvo);
        return ResponseEntity.ok(ApiResponse02.success(salvoDTO, "Item criado com sucesso"));
    }

    @Override
    @PutMapping
    public ResponseEntity<ApiResponse02<ItemDto>> alterar(
            @RequestBody @Valid ItemDto dto) {
        Item salvo = itemService.update(itemMapper.toEntity(dto));
        ItemDto salvoDTO = itemMapper.toDto(salvo);
        return ResponseEntity.ok(ApiResponse02.success(salvoDTO, "Item alterado com sucesso"));
    }

    @Override
    @DeleteMapping
    public ResponseEntity<ApiResponse02<Void>> excluir(
            @RequestParam(name = "id") String id) {
        itemService.deleteById(id);
        return ResponseEntity.ok(ApiResponse02.success("Item excluído sucesso"));
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<ApiResponse02<List<ItemDto>>> buscarLista(
            @RequestParam(required = false) Map<String, String> filtros) {
        List<Item> entidades = itemService.findList(filtros);
        List<ItemDto> dtos = itemMapper.toDtoList(entidades);
        return ResponseEntity.ok(ApiResponse02.success(dtos, "Lista de Itens"));
    }

    @Override
    @GetMapping("/page")
    public ResponseEntity<ApiResponse02<Page<VwItem>>> buscarPagina(
            Pageable pageable,
            @RequestParam(required = false) Map<String, String> filtros) {
        Page<VwItem> pagina = itemService.findPage(pageable, filtros);
        return ResponseEntity.ok(ApiResponse02.success(pagina, "Pagina de VwItem"));
    }

    @PutMapping("/archive")
    public ResponseEntity<ApiResponse02<Void>> arquivar(
            @RequestParam(name = "codItem", defaultValue = "") String codItem) {
        itemService.archive(codItem);
        return ResponseEntity.ok(ApiResponse02.success("Item arquivado com sucesso"));
    }

    @PutMapping("/unarchive")
    public ResponseEntity<ApiResponse02<Void>> desarquivar(
            @RequestParam(name = "codItem", defaultValue = "") String codItem) {
        itemService.unarchive(codItem);
        return ResponseEntity.ok(ApiResponse02.success("Item desarquivado com sucesso"));
    }

    @GetMapping("/checkDelete")
    public ResponseEntity<ApiResponse02<Void>> checkExcluir(
            @RequestParam(name = "codItem") String codItem) {
        itemService.checkDelete(codItem);
        return ResponseEntity.ok(ApiResponse02.success("Item pode ser excluído"));
    }

    @GetMapping("/tpItems")
    public ResponseEntity<ApiResponse02<List<TipoItem>>> listTipoItem() {
        List<TipoItem> tipoItems = tipoItemService.listTipoItem();
        return ResponseEntity.ok(ApiResponse02.success(tipoItems, "Lista de tipos de itens"));
    }

    @GetMapping("/familias")
    public ResponseEntity<ApiResponse02<List<String>>> listFamilia() {
        List<String> familias = itemService.listFamilia();
        return ResponseEntity.ok(ApiResponse02.success(familias, "Lista de familias"));
    }

    @GetMapping("/situacoes")
    public ResponseEntity<ApiResponse02<List<String>>> listSituacao() {
        List<String> situacoes = itemService.listSituacao();
        return ResponseEntity.ok(ApiResponse02.success(situacoes, "Lista de situações"));
    }
}
