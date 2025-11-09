package com.holis.san01.controller;

import com.holis.san01.model.local.ApiResponse02;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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

    ResponseEntity<ApiResponse02<DTO>> buscarPorId(@RequestParam(name = "id") ID id);

    ResponseEntity<ApiResponse02<DTO>> criar(@RequestBody @Valid DTO dto);

    ResponseEntity<ApiResponse02<DTO>> alterar(@RequestBody @Valid DTO dto);

    ResponseEntity<ApiResponse02<Void>> excluir(@RequestParam(name = "id") ID id);

    ResponseEntity<ApiResponse02<List<DTO>>> listar(@RequestParam(required = false) Map<String, String> filtros);

    ResponseEntity<ApiResponse02<Page<VIEW>>> listarPagina(Pageable pageable, @RequestParam(required = false) Map<String, String> filtros);

    ResponseEntity<ApiResponse02<Void>> arquivar(@RequestParam(name = "id") ID id);

    ResponseEntity<ApiResponse02<Void>> desarquivar(@RequestParam(name = "id") ID id);
}
