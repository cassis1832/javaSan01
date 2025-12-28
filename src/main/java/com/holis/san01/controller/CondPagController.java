package com.holis.san01.controller;

import com.holis.san01.dto.ApiResponse02;
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
    public ResponseEntity<ApiResponse02<CondPagDto>> getById(@PathVariable String id) {
        CondPag condPag = condPagService.findById(id);
        return ResponseEntity.ok(
                ApiResponse02.success(condPagMapper.toDto(condPag))
        );
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse02<CondPagDto>> create(@RequestBody @Valid CondPagDto dto) {
        CondPag condPag = condPagService.save(condPagMapper.toEntity(dto));
        CondPagDto condPagDto = condPagMapper.toDto(condPag);
        return ResponseEntity.ok(
                ApiResponse02.success(condPagDto, "Condição de pagamento criada com sucesso")
        );
    }

    @Override
    @PutMapping
    public ResponseEntity<ApiResponse02<CondPagDto>> update(@RequestBody @Valid CondPagDto dto) {
        CondPag condPag = condPagService.update(condPagMapper.toEntity(dto));
        CondPagDto condPagDTO = condPagMapper.toDto(condPag);
        return ResponseEntity.ok(
                ApiResponse02.success(condPagDTO, "Coondição de pagamento alterada com sucesso")
        );
    }

    @Override
    @DeleteMapping
    public ResponseEntity<ApiResponse02<Void>> delete(@PathVariable String id) {
        condPagService.deleteById(id);
        return ResponseEntity.ok(
                ApiResponse02.success("Condição de pagamento excluída com sucesso")
        );
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<ApiResponse02<List<CondPagDto>>> getList(@RequestParam(required = false) Map<String, String> filtros) {
        List<CondPag> entidades = condPagService.findList(filtros);
        List<CondPagDto> dtos = condPagMapper.toDtoList(entidades);
        return ResponseEntity.ok(
                ApiResponse02.success(dtos, "Lista de condições de pagamento")
        );
    }

    @Override
    @GetMapping("/page")
    public ResponseEntity<ApiResponse02<Page<CondPag>>> getPage(Pageable pageable,
                                                                @RequestParam(required = false) Map<String, String> filtros) {
        Page<CondPag> pagina = condPagService.findPage(pageable, filtros);
        return ResponseEntity.ok(
                ApiResponse02.success(pagina, "Pagina de condições de pagamento")
        );
    }

    @Override
    public ResponseEntity<ApiResponse02<Void>> archive(String s) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse02<Void>> unarchive(String s) {
        return null;
    }
}
