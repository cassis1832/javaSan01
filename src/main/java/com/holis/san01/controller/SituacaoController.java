package com.holis.san01.controller;

import com.holis.san01.model.Situacao;
import com.holis.san01.model.local.ApiResponse;
import com.holis.san01.services.SituacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller para tratamento de Situações
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/situacaos", produces = MediaType.APPLICATION_JSON_VALUE)
public class SituacaoController {

    private final SituacaoService situacaoService;

    /**
     * Ler um determinado registro
     */
    @GetMapping
    public ResponseEntity<ApiResponse> findSituacaoBySituacao(
            @RequestParam(name = "objeto", defaultValue = "") String objeto,
            @RequestParam(name = "situacao", defaultValue = "") Integer codSit) {

        Situacao situacao = situacaoService.findSituacaoBySituacao(objeto, codSit);
        return new ResponseEntity<>(new ApiResponse(true, situacao), HttpStatus.OK);

    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse> listSituacao(
            @RequestParam(name = "objeto", defaultValue = "") String objeto) {

        List<Situacao> situacoes = situacaoService.listSituacao(objeto);
        return new ResponseEntity<>(new ApiResponse(true, situacoes), HttpStatus.OK);
    }

    /**
     * Incluir um novo registro
     */
    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid Situacao situacao) {

        situacao = situacaoService.create(situacao);
        return new ResponseEntity<>(new ApiResponse(true, situacao), HttpStatus.OK);
    }

    /**
     * Alterar um registro existente
     */
    @PutMapping
    public ResponseEntity<ApiResponse> update(@RequestBody @Valid Situacao situacao) {

        situacao = situacaoService.update(situacao);
        return new ResponseEntity<>(new ApiResponse(true, situacao), HttpStatus.OK);
    }

    /**
     * Excluir um registro
     */
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam(name = "id") Integer id) {

        situacaoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
