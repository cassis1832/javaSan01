package com.holis.san01.controller;

import com.holis.san01.mapper.ItemMapper;
import com.holis.san01.model.Item;
import com.holis.san01.model.ItemDTO;
import com.holis.san01.model.TipoItem;
import com.holis.san01.model.VwItem;
import com.holis.san01.model.local.ApiResponse02;
import com.holis.san01.services.ItemService03;
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
public class ItemController03 implements BaseController<ItemDTO, String, VwItem> {

    private final ItemService03 itemService;
    private final TipoItemService tipoItemService;
    private final ItemMapper itemMapper;

    @Override
    @GetMapping
    public ResponseEntity<ApiResponse02<ItemDTO>> buscarPorId(@RequestParam(name = "id") String id) {
        return itemService.findById(id)
                .map(entidade -> ResponseEntity.ok(ApiResponse02.success(itemMapper.toDto(entidade))))
                .orElse(ResponseEntity.status(200).body(ApiResponse02.errorMessage("Item não encontrado")));
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<ApiResponse02<List<ItemDTO>>> listar(@RequestParam(required = false) Map<String, String> filtros) {
        List<Item> entidades = itemService.listEntity(filtros);
        List<ItemDTO> dtos = itemMapper.toDtoList(entidades);
        return ResponseEntity.ok(ApiResponse02.success(dtos, "Lista de Itens"));
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse02<ItemDTO>> criar(@RequestBody @Valid ItemDTO dto) {
        Item salvo = itemService.save(itemMapper.toEntity(dto));
        ItemDTO salvoDTO = itemMapper.toDto(salvo);
        return ResponseEntity.ok(ApiResponse02.success(salvoDTO, "Item criado com sucesso"));
    }

    @Override
    @PutMapping
    public ResponseEntity<ApiResponse02<ItemDTO>> alterar(@RequestBody @Valid ItemDTO dto) {
        Item salvo = itemService.update(itemMapper.toEntity(dto));
        ItemDTO salvoDTO = itemMapper.toDto(salvo);
        return ResponseEntity.ok(ApiResponse02.success(salvoDTO, "Item alterado com sucesso"));
    }

    @Override
    @DeleteMapping
    public ResponseEntity<ApiResponse02<Void>> excluir(@RequestParam(name = "codItem") String codItem) {
        itemService.deleteById(codItem);
        return ResponseEntity.ok(ApiResponse02.success("Item excluído sucesso"));
    }

    @Override
    @GetMapping("/page")
    public ResponseEntity<ApiResponse02<Page<VwItem>>> listarPagina(
            Pageable pageable, @RequestParam(required = false) Map<String, String> filtros) {
        Page<VwItem> pagina = itemService.pageView(pageable, filtros);
        return ResponseEntity.ok(ApiResponse02.success(pagina, "Pagina de VwItem"));
    }

    @Override
    @PutMapping("/archive")
    public ResponseEntity<ApiResponse02<Void>> arquivar(@RequestParam(name = "codItem", defaultValue = "") String codItem) {
        itemService.archive(codItem);
        return ResponseEntity.ok(ApiResponse02.success("Item arquivado com sucesso"));
    }

    @Override
    @PutMapping("/unarchive")
    public ResponseEntity<ApiResponse02<Void>> desarquivar(@RequestParam(name = "codItem", defaultValue = "") String codItem) {
        itemService.unarchive(codItem);
        return ResponseEntity.ok(ApiResponse02.success("Item desarquivado com sucesso"));
    }

    @GetMapping("/checkDelete")
    public ResponseEntity<ApiResponse02<Void>> checkExcluir(@RequestParam(name = "codItem") String codItem) {
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
