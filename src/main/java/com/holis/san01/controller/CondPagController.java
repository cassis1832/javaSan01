package com.holis.san01.controller;

import com.holis.san01.dto.ApiResponse;
import com.holis.san01.dto.CondPagDto;
import com.holis.san01.mapper.CondPagMapper;
import com.holis.san01.model.CondPag;
import com.holis.san01.services.CondPagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller para tratamento de condição de pagamento
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/condpags", produces = MediaType.APPLICATION_JSON_VALUE)
public class CondPagController implements BaseController<CondPagDto, String, CondPagDto> {

    private final CondPagService condPagService;
    private final CondPagMapper condPagMapper;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CondPagDto>> findByID(@PathVariable String id) {
        CondPag condPag = condPagService.findById(id);
        return ResponseEntity.ok(
                ApiResponse.success(condPagMapper.toDto(condPag))
        );
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<CondPagDto>> create(@RequestBody @Valid CondPagDto dto) {
        CondPag condPag = condPagService.create(condPagMapper.toEntity(dto));
        CondPagDto condPagDto = condPagMapper.toDto(condPag);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(condPagDto, "Condição de pagamento criada com sucesso"));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CondPagDto>> update(
            @PathVariable String id,
            @RequestBody @Valid CondPagDto dto) {
        CondPag condPag = condPagService.update(condPagMapper.toEntity(dto));
        CondPagDto condPagDTO = condPagMapper.toDto(condPag);
        return ResponseEntity.ok(
                ApiResponse.success(condPagDTO, "Condição de pagamento alterada com sucesso")
        );
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        condPagService.delete(id);
        return ResponseEntity.ok(
                ApiResponse.success("Condição de pagamento excluída com sucesso")
        );
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<CondPagDto>>> findAll(
            @RequestParam(required = false) Map<String, String> filtros) {
        List<CondPag> condPags = condPagService.findAll(filtros);
        List<CondPagDto> condPagDtos = condPagMapper.toDtoList(condPags);
        return ResponseEntity.ok(
                ApiResponse.success(condPagDtos, "Lista de condições de pagamento")
        );
    }

    @Override
    @GetMapping("/page")
    public ResponseEntity<ApiResponse<Page<CondPagDto>>> findPage(
            Pageable pageable,
            @RequestParam(required = false) Map<String, String> filtros) {
        Page<CondPag> condPags = condPagService.findPage(pageable, filtros);
        Page<CondPagDto> condPagDtos = condPagMapper.toDtoPage(condPags);
        return ResponseEntity.ok(
                ApiResponse.success(condPagDtos, "Pagina de condições de pagamento")
        );
    }

    @PatchMapping("/{id}/arquivar")
    public ResponseEntity<ApiResponse<Void>> arquivar(@PathVariable String id) {
        condPagService.arquivar(id);
        return ResponseEntity.ok(
                ApiResponse.success("Condição de pagamento arquivada com sucesso")
        );
    }

    @PatchMapping("/{id}/desarquivar")
    public ResponseEntity<ApiResponse<Void>> desarquivar(@PathVariable String id) {
        condPagService.desarquivar(id);
        return ResponseEntity.ok(
                ApiResponse.success("Condição de pagamento desarquivada com sucesso")
        );
    }
}
