package com.holis.san01.controller;

import com.holis.san01.dto.ApiResponse;
import com.holis.san01.mapper.EntidadeMapper;
import com.holis.san01.model.Entidade;
import com.holis.san01.model.EntidadeDto;
import com.holis.san01.services.EntidadeService;
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
 * Controller para tratamento de Entidades
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/entds", produces = MediaType.APPLICATION_JSON_VALUE)
public class EntidadeController implements BaseController<EntidadeDto, Integer, Entidade> {

    private final EntidadeService entidadeService;
    private final EntidadeMapper entidadeMapper;

    @Override
    @GetMapping
    public ResponseEntity<ApiResponse<EntidadeDto>> getById(@PathVariable Integer id) {
        Entidade entidade = entidadeService.findById(id);
        return ResponseEntity.ok(
                ApiResponse.success(entidadeMapper.toDto(entidade))
        );
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<EntidadeDto>> create(@RequestBody @Valid EntidadeDto dto) {
        Entidade entidade = entidadeService.create(entidadeMapper.toEntity(dto));
        EntidadeDto entidadeDto = entidadeMapper.toDto(entidade);
        return ResponseEntity.ok(
                ApiResponse.success(entidadeDto, "Item criado com sucesso")
        );
    }

    @Override
    @PutMapping
    public ResponseEntity<ApiResponse<EntidadeDto>> update(@RequestBody @Valid EntidadeDto dto) {
        Entidade entidade = entidadeService.update(entidadeMapper.toEntity(dto));
        EntidadeDto entidadeDto = entidadeMapper.toDto(entidade);
        return ResponseEntity.ok(
                ApiResponse.success(entidadeDto, "Item alterado com sucesso")
        );
    }

    @Override
    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
        entidadeService.deleteById(id);
        return ResponseEntity.ok(
                ApiResponse.success("Item excluído sucesso")
        );
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<EntidadeDto>>> getList(@RequestParam(required = false)
                                                                  Map<String, String> filtros) {
        List<Entidade> entidades = entidadeService.findList(filtros);
        List<EntidadeDto> dtos = entidadeMapper.toDtoList(entidades);
        return ResponseEntity.ok(
                ApiResponse.success(dtos, "Lista de Itens")
        );
    }

    @Override
    @GetMapping("/page")
    public ResponseEntity<ApiResponse<Page<Entidade>>> getPage(Pageable pageable,
                                                               @RequestParam(required = false) Map<String, String> filtros) {
        Page<Entidade> pagina = entidadeService.findPage(pageable, filtros);
        return ResponseEntity.ok(
                ApiResponse.success(pagina, "Pagina de Entidades")
        );
    }

    @PutMapping("/archive")
    public ResponseEntity<ApiResponse<Void>> archive(@PathVariable Integer id) {
        entidadeService.archive(id);
        return ResponseEntity.ok(
                ApiResponse.success("Cliente/fornecedor arquivado com sucesso")
        );
    }

    @PutMapping("/unarchive")
    public ResponseEntity<ApiResponse<Void>> unarchive(@PathVariable Integer id) {
        entidadeService.unarchive(id);
        return ResponseEntity.ok(
                ApiResponse.success("Cliente/fornecedor desarquivado com sucesso")
        );
    }
}
