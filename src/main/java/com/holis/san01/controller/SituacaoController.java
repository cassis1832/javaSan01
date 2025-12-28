package com.holis.san01.controller;

import com.holis.san01.dto.ApiResponse;
import com.holis.san01.model.Situacao;
import com.holis.san01.services.SituacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Controller para tratamento de Situações
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/situacaos", produces = MediaType.APPLICATION_JSON_VALUE)
public class SituacaoController implements BaseController<Situacao, Integer, Situacao> {

    private final SituacaoService situacaoService;

    @Override
    public ResponseEntity<ApiResponse<Situacao>> getById(Integer integer) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<Situacao>> create(Situacao situacao) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<Situacao>> update(Situacao situacao) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<Void>> delete(Integer integer) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<Void>> archive(Integer integer) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<Void>> unarchive(Integer integer) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<List<Situacao>>> getList(Map<String, String> filtros) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<Page<Situacao>>> getPage(Pageable pageable, Map<String, String> filtros) {
        return null;
    }
}
