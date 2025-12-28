package com.holis.san01.controller;

import com.holis.san01.dto.ApiResponse;
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

    ResponseEntity<ApiResponse<DTO>> getById(@PathVariable ID id);

    ResponseEntity<ApiResponse<DTO>> create(@RequestBody @Valid DTO dto);

    ResponseEntity<ApiResponse<DTO>> update(@RequestBody @Valid DTO dto);

    ResponseEntity<ApiResponse<Void>> delete(@PathVariable ID id);

    ResponseEntity<ApiResponse<Void>> archive(@PathVariable ID id);

    ResponseEntity<ApiResponse<Void>> unarchive(@PathVariable ID id);

    ResponseEntity<ApiResponse<List<DTO>>> getList(@RequestParam(required = false) Map<String, String> filtros);

    ResponseEntity<ApiResponse<Page<VIEW>>> getPage(Pageable pageable,
                                                    @RequestParam(required = false) Map<String, String> filtros);
}
