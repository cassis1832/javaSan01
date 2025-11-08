package com.holis.san01.controller;

import com.holis.san01.mapper.BaseMapper;
import com.holis.san01.model.local.ApiResponse02;
import com.holis.san01.services.BaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller base genérico com suporte a paginação e filtros dinâmicos.
 *
 * @param <E>  Entidade
 * @param <D>  DTO
 * @param <ID> Tipo do ID
 */
public abstract class BaseController<E, D, ID> {

    protected final BaseService<E, ID> service;
    protected final BaseMapper<E, D> mapper;

    protected BaseController(BaseService<E, ID> service, BaseMapper<E, D> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<ApiResponse02<D>> buscarPorId(@RequestParam(name = "id") ID id) {
        return service.findById(id)
                .map(entidade -> ResponseEntity.ok(ApiResponse02.success(mapper.toDto(entidade))))
                .orElse(ResponseEntity.status(200).body(ApiResponse02.errorMessage("Registro não encontrado")));
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse02<List<D>>> listar(@RequestParam(required = false) Map<String, String> filtros) {
        List<E> entidades = service.findList(filtros);
        List<D> dtos = mapper.toDtoList(entidades);
        return ResponseEntity.ok(ApiResponse02.success(dtos));
    }

    @PostMapping
    public ResponseEntity<ApiResponse02<D>> criar(@RequestBody D dto) {
        E entidade = mapper.toEntity(dto);
        E salvo = service.save(entidade);
        return ResponseEntity.ok(ApiResponse02.success(mapper.toDto(salvo), "Registro criado com sucesso"));
    }

    @PutMapping
    public ResponseEntity<ApiResponse02<D>> atualizar(@RequestBody D dto) {
        E entidade = mapper.toEntity(dto);
        E atualizado = service.update(entidade);
        return ResponseEntity.ok(ApiResponse02.success(mapper.toDto(atualizado), "Registro atualizado com sucesso"));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse02<Void>> excluir(@RequestParam(name = "id") ID id) {
        service.deleteById(id);
        return ResponseEntity.ok(ApiResponse02.success(null, "Registro excluído com sucesso"));
    }
}