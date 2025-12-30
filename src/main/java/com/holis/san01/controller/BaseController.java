package com.holis.san01.controller;

import com.holis.san01.dto.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller Base
 *
 * @param <DTO>
 * @param <ID>
 * @param <VIEW>
 */
public interface BaseController<DTO, ID, VIEW> {

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<DTO>> findByID(@PathVariable ID id);

    @PostMapping
    ResponseEntity<ApiResponse<DTO>> create(@RequestBody @Valid DTO dto);

    @PutMapping("/{id}")
    ResponseEntity<ApiResponse<DTO>> update(
            @PathVariable ID id,
            @RequestBody @Valid DTO dto);

    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<Void>> delete(@PathVariable ID id);

    @PatchMapping("/{id}/arquivar")
    ResponseEntity<ApiResponse<Void>> arquivar(@PathVariable ID id);

    @PatchMapping("/{id}/desarquivar")
    ResponseEntity<ApiResponse<Void>> desarquivar(@PathVariable ID id);

    @GetMapping("/list")
    ResponseEntity<ApiResponse<List<VIEW>>> findAll(
            @RequestParam(required = false) Map<String, String> filtros);

    @GetMapping("/page")
    ResponseEntity<ApiResponse<Page<VIEW>>> findPage(
            Pageable pageable,
            @RequestParam(required = false) Map<String, String> filtros);
}
