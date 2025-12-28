package com.holis.san01.controller;

import com.holis.san01.dto.ApiResponse;
import com.holis.san01.mapper.CondPagMapper;
import com.holis.san01.model.CondPag;
import com.holis.san01.model.CondPagDto;
import com.holis.san01.services.CondPagService;
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
 * Controller para tratamento de condição de pagamento
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/condpags", produces = MediaType.APPLICATION_JSON_VALUE)
public class CondPagController implements BaseController<CondPagDto, String, CondPag> {

    private final CondPagService condPagService;
    private final CondPagMapper condPagMapper;

    @Override
    @GetMapping
    public ResponseEntity<ApiResponse<CondPagDto>> getById(@PathVariable String id) {
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
        return ResponseEntity.ok(
                ApiResponse.success(condPagDto, "Condição de pagamento criada com sucesso")
        );
    }

    @Override
    @PutMapping
    public ResponseEntity<ApiResponse<CondPagDto>> update(@RequestBody @Valid CondPagDto dto) {
        CondPag condPag = condPagService.update(condPagMapper.toEntity(dto));
        CondPagDto condPagDTO = condPagMapper.toDto(condPag);
        return ResponseEntity.ok(
                ApiResponse.success(condPagDTO, "Coondição de pagamento alterada com sucesso")
        );
    }

    @Override
    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        condPagService.deleteById(id);
        return ResponseEntity.ok(
                ApiResponse.success("Condição de pagamento excluída com sucesso")
        );
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<CondPagDto>>> getList(@RequestParam(required = false) Map<String, String> filtros) {
        List<CondPag> entidades = condPagService.findList(filtros);
        List<CondPagDto> dtos = condPagMapper.toDtoList(entidades);
        return ResponseEntity.ok(
                ApiResponse.success(dtos, "Lista de condições de pagamento")
        );
    }

    @Override
    @GetMapping("/page")
    public ResponseEntity<ApiResponse<Page<CondPag>>> getPage(Pageable pageable,
                                                              @RequestParam(required = false) Map<String, String> filtros) {
        Page<CondPag> pagina = condPagService.findPage(pageable, filtros);
        return ResponseEntity.ok(
                ApiResponse.success(pagina, "Pagina de condições de pagamento")
        );
    }

    @PutMapping("/archive")
    public ResponseEntity<ApiResponse<Void>> archive(@PathVariable String id) {
        condPagService.archive(id);
        return ResponseEntity.ok(
                ApiResponse.success("Condição de pagamento arquivada com sucesso")
        );
    }

    @PutMapping("/unarchive")
    public ResponseEntity<ApiResponse<Void>> unarchive(@PathVariable String id) {
        condPagService.unarchive(id);
        return ResponseEntity.ok(
                ApiResponse.success("Condição de pagamento desarquivada com sucesso")
        );
    }
}
