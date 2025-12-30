package com.holis.san01.controller;

import com.holis.san01.dto.ApiResponse;
import com.holis.san01.model.UnidMedida;
import com.holis.san01.services.UnidMedidaService;
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
 * Controller para tratamento de Unidades de Medidas
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/unimeds", produces = MediaType.APPLICATION_JSON_VALUE)
public class UnidMedidaController implements BaseController<UnidMedida, String, UnidMedida> {

    private final UnidMedidaService unidMedidaService;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UnidMedida>> findByID(@PathVariable String id) {
        UnidMedida unidMedida = unidMedidaService.findById(id);
        return ResponseEntity.ok(
                ApiResponse.success(unidMedida)
        );
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<UnidMedida>> create(@RequestBody @Valid UnidMedida dto) {
        UnidMedida unidMedida = unidMedidaService.create(dto);
        return ResponseEntity.ok(
                ApiResponse.success(unidMedida, "Condição de pagamento criada com sucesso")
        );
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UnidMedida>> update(
            @PathVariable String id,
            @RequestBody @Valid UnidMedida dto) {
        UnidMedida unidMedida = unidMedidaService.update(dto);
        return ResponseEntity.ok(
                ApiResponse.success(unidMedida, "Coondição de pagamento alterada com sucesso")
        );
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        unidMedidaService.delete(id);
        return ResponseEntity.ok(
                ApiResponse.success("Condição de pagamento excluída com sucesso")
        );
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<UnidMedida>>> findAll(
            @RequestParam(required = false) Map<String, String> filtros) {
        List<UnidMedida> unidMedidas = unidMedidaService.findAll(filtros);
        return ResponseEntity.ok(
                ApiResponse.success(unidMedidas, "Lista de condições de pagamento")
        );
    }

    @Override
    @GetMapping("/page")
    public ResponseEntity<ApiResponse<Page<UnidMedida>>> findPage(
            Pageable pageable,
            @RequestParam(required = false) Map<String, String> filtros) {
        return null;
    }

    @PatchMapping("/{id}/arquivar")
    public ResponseEntity<ApiResponse<Void>> arquivar(@PathVariable String id) {
        unidMedidaService.arquivar(id);
        return ResponseEntity.ok(
                ApiResponse.success("Condição de pagamento arquivada com sucesso")
        );
    }

    @PatchMapping("/{id}/desarquivar")
    public ResponseEntity<ApiResponse<Void>> desarquivar(@PathVariable String id) {
        unidMedidaService.desarquivar(id);
        return ResponseEntity.ok(
                ApiResponse.success("Condição de pagamento desarquivada com sucesso")
        );
    }
}
