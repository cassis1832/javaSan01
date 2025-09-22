package com.holis.san01.controller;

import com.holis.san01.model.local.ApiResponse;
import com.holis.san01.model.Situacao;
import com.holis.san01.model.local.FiltroPesquisa;
import com.holis.san01.services.SituacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller para tratamento de Situações
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/situacaos", produces = MediaType.APPLICATION_JSON_VALUE)
public class SituacaoController {

    private final SituacaoService situacaoService;

    /**
     * Ler um determinado registro pelo id
     */
    @GetMapping
    public ResponseEntity<Situacao> findSituacaoById(
            @RequestParam(name = "id", defaultValue = "") Integer id) {

        return ResponseEntity.status(HttpStatus.OK).body(situacaoService.findSituacaoById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse> listSituacao(
            @ModelAttribute FiltroPesquisa filtro) {

        ApiResponse apiResponse = situacaoService.listSituacao(filtro);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /**
     * Incluir um novo registro
     */
    @PostMapping
    public ResponseEntity<Situacao> create(@RequestBody @Valid Situacao situacao) {

        situacao = situacaoService.create(situacao);
        return new ResponseEntity<>(situacao, HttpStatus.CREATED);
    }

    /**
     * Alterar um registro existente
     */
    @PutMapping
    public ResponseEntity<Situacao> update(@RequestBody @Valid Situacao situacao) {

        situacao = situacaoService.update(situacao);
        return new ResponseEntity<>(situacao, HttpStatus.OK);
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
