package com.holis.san01.controller;

import com.holis.san01.dto.ApiResponse02;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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

    ResponseEntity<ApiResponse02<DTO>> getById(@PathVariable ID id);

    ResponseEntity<ApiResponse02<DTO>> create(@RequestBody @Valid DTO dto);

    ResponseEntity<ApiResponse02<DTO>> update(@RequestBody @Valid DTO dto);

    ResponseEntity<ApiResponse02<Void>> delete(@PathVariable ID id);

    ResponseEntity<ApiResponse02<Void>> archive(@PathVariable ID id);

    ResponseEntity<ApiResponse02<Void>> unarchive(@PathVariable ID id);

    ResponseEntity<ApiResponse02<List<DTO>>> getList(@RequestParam(required = false) Map<String, String> filtros);

    ResponseEntity<ApiResponse02<Page<VIEW>>> getPage(Pageable pageable,
                                                      @RequestParam(required = false) Map<String, String> filtros);
}
