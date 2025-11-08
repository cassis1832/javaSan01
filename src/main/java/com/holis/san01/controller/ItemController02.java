package com.holis.san01.controller;

import com.holis.san01.mapper.ItemMapper;
import com.holis.san01.model.Item;
import com.holis.san01.model.ItemDTO;
import com.holis.san01.model.TipoItem;
import com.holis.san01.model.VwItem;
import com.holis.san01.model.local.ApiResponse02;
import com.holis.san01.services.ItemService02;
import com.holis.san01.services.TipoItemService;
import jakarta.validation.Valid;
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
@RequestMapping(value = "/api/items", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemController02 extends BaseController<Item, ItemDTO, String> {

    private final ItemService02 itemService;
    private final TipoItemService tipoItemService;
    private final ItemMapper itemMapper;

    protected ItemController02(
            ItemService02 itemService,
            TipoItemService tipoItemService,
            ItemMapper itemMapper) {
        super(itemService, itemMapper);
        this.itemService = itemService;
        this.tipoItemService = tipoItemService;
        this.itemMapper = itemMapper;
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse02<ItemDTO>> criar(@RequestBody @Valid ItemDTO dto) {
        Item salvo = itemService.save(itemMapper.toEntity(dto));
        ItemDTO salvoDTO = itemMapper.toDto(salvo);
        return ResponseEntity.ok(ApiResponse02.success(salvoDTO, "Registro criado com sucesso"));
    }

    @Override
    @PutMapping
    public ResponseEntity<ApiResponse02<ItemDTO>> atualizar(@RequestBody @Valid ItemDTO dto) {
        Item salvo = itemService.update(itemMapper.toEntity(dto));
        ItemDTO salvoDTO = itemMapper.toDto(salvo);
        return ResponseEntity.ok(ApiResponse02.success(salvoDTO, "Registro alterado com sucesso"));
    }

    @Override
    @DeleteMapping
    public ResponseEntity<ApiResponse02<Void>> excluir(@RequestParam(name = "codItem") String codItem) {
        itemService.deleteById(codItem);
        return ResponseEntity.ok(ApiResponse02.success("Registro excluído sucesso"));
    }

    @GetMapping("/page")
    public ResponseEntity<ApiResponse02<Page<VwItem>>> listarPagina(Pageable pageable, @RequestParam(required = false) Map<String, String> filtros) {
        Page<VwItem> pagina = itemService.pageVwItem(pageable, filtros);
        return ResponseEntity.ok(ApiResponse02.success(pagina, "Pagina de VwItem"));
    }

    @PutMapping("/archive")
    public ResponseEntity<ApiResponse02<Void>> arquivar(@RequestParam(name = "codItem", defaultValue = "") String codItem) {
        itemService.archive(codItem);
        return ResponseEntity.ok(ApiResponse02.success("Registro Arquivado Com Sucesso"));
    }

    @PutMapping("/unarchive")
    public ResponseEntity<ApiResponse02<Void>> desarquivar(@RequestParam(name = "codItem", defaultValue = "") String codItem) {
        itemService.unarchive(codItem);
        return ResponseEntity.ok(ApiResponse02.success("Registro Desarquivado Com Sucesso"));
    }

    @GetMapping("/checkDelete")
    public ResponseEntity<ApiResponse02<Void>> checkDelete(@RequestParam(name = "codItem") String codItem) {
        itemService.checkDelete(codItem);
        return ResponseEntity.ok(ApiResponse02.success("Registro pode ser excluído"));
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
