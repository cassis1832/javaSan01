package com.holis.san01.controller;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.model.ApiResponse;
import com.holis.san01.model.Unimed;
import com.holis.san01.services.UnimedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller para tratamento de Unidades de Medidas
 */
@RestController
@RequestMapping(value = "/api/unimeds", produces = MediaType.APPLICATION_JSON_VALUE)
public class UnimedController {
    @Autowired
    private UnimedService unimedService;

    /**
     * Ler um determinado registro pelo código
     */
    @GetMapping
    public ResponseEntity<Unimed> ler(
            @RequestParam(name = "codUnimed", defaultValue = "") String codUnimed) {
        return ResponseEntity.status(HttpStatus.OK).body(unimedService.ler(codUnimed));
    }

    /**
     * Incluir um novo registro
     */
    @PostMapping
    public ResponseEntity<Unimed> incluir(@RequestBody @Valid Unimed unimed) {
        unimed = unimedService.incluir(unimed);
        return new ResponseEntity<>(unimed, HttpStatus.CREATED);
    }

    /**
     * Alterar um registro existente
     */
    @PutMapping
    public ResponseEntity<Unimed> alterar(@RequestBody @Valid Unimed unimed) {
        unimed = unimedService.alterar(unimed);
        return new ResponseEntity<>(unimed, HttpStatus.OK);
    }

    /**
     * Excluir um registro
     */
    @DeleteMapping
    public ResponseEntity<?> excluir(@RequestParam(name = "codUnimed") String codUnimed) {
        try {
            unimedService.excluir(codUnimed);
        } catch (Exception ex) {
            throw new ApiRequestException(ex.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/lista")
    public ResponseEntity<ApiResponse> listar(
            @RequestParam(name = "archive", defaultValue = "N") String archive) {
        ApiResponse apiResponse = unimedService.listar(archive);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
