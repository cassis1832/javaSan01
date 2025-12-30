package com.holis.san01.controller;

import com.holis.san01.dto.ApiResponse;
import com.holis.san01.model.EspDoc;
import com.holis.san01.services.EspDocService;
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
 * Controller para tratamento de Especie de documento
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/espdocs", produces = MediaType.APPLICATION_JSON_VALUE)
public class EspDocController implements BaseController<EspDoc, String, EspDoc> {

    private final EspDocService espDocService;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EspDoc>> findByID(@PathVariable String id) {
        EspDoc espDoc = espDocService.findById(id);
        return ResponseEntity.ok(
                ApiResponse.success(espDoc)
        );
    }

    @Override
    public ResponseEntity<ApiResponse<EspDoc>> create(@RequestBody @Valid EspDoc espDoc) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<EspDoc>> update(
            @PathVariable String id,
            @RequestBody @Valid EspDoc espDoc) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<Void>> arquivar(@PathVariable String id) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<Void>> desarquivar(@PathVariable String id) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<List<EspDoc>>> findAll(
            @RequestParam(required = false) Map<String, String> filtros) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<Page<EspDoc>>> findPage(
            Pageable pageable,
            @RequestParam(required = false) Map<String, String> filtros) {
        return null;
    }
}
