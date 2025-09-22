package com.holis.san01.controller;

import com.holis.san01.model.local.ApiResponse;
import com.holis.san01.model.UnidMedida;
import com.holis.san01.services.UnidMedidaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller para tratamento de Unidades de Medidas
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/unimeds", produces = MediaType.APPLICATION_JSON_VALUE)
public class UnidMedidaController {

    private final UnidMedidaService unidMedidaService;

    /**
     * Ler um determinado registro pelo código
     */
    @GetMapping
    public ResponseEntity<UnidMedida> findUnidMedidaByCodUnimed(
            @RequestParam(name = "codUniMed", defaultValue = "") String codUniMed) {

        return ResponseEntity.status(HttpStatus.OK).body(unidMedidaService.findUnidMedidaByCodUnimed(codUniMed));
    }

    /**
     * Incluir um novo registro
     */
    @PostMapping
    public ResponseEntity<UnidMedida> create(@RequestBody @Valid UnidMedida unidMedida) {

        unidMedida = unidMedidaService.create(unidMedida);
        return new ResponseEntity<>(unidMedida, HttpStatus.CREATED);
    }

    /**
     * Alterar um registro existente
     */
    @PutMapping
    public ResponseEntity<UnidMedida> update(@RequestBody @Valid UnidMedida unidMedida) {

        unidMedida = unidMedidaService.update(unidMedida);
        return new ResponseEntity<>(unidMedida, HttpStatus.OK);
    }

    /**
     * Excluir um registro
     */
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam(name = "codUniMed") String codUniMed) {

        unidMedidaService.delete(codUniMed);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse> listUnime(
            @RequestParam(name = "status", defaultValue = "0") int status) {

        ApiResponse apiResponse = unidMedidaService.listUnidMedida(status);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
