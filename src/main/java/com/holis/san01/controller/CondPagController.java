package com.holis.san01.controller;

import com.holis.san01.model.CondPag;
import com.holis.san01.model.local.ApiResponse;
import com.holis.san01.model.local.FiltroPesquisa;
import com.holis.san01.services.CondPagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller para tratamento de condição de pagamento
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/condpags", produces = MediaType.APPLICATION_JSON_VALUE)
public class CondPagController {

    private final CondPagService condPagService;

    @GetMapping
    public ResponseEntity<ApiResponse> findCondPag(
            @RequestParam(name = "codCondPag", defaultValue = "") String codCondPag) {
        ApiResponse apiResponse = condPagService.findCondPag(codCondPag);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse> listCondPag() {
        ApiResponse apiResponse = condPagService.listCondPag();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<ApiResponse> pageCondPag(
            @ModelAttribute FiltroPesquisa filtroPesquisa) {

        var page = condPagService.pageCondPag(filtroPesquisa);
        return new ResponseEntity<>(new ApiResponse(true, page), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(
            @RequestBody @Valid CondPag condPag) {

        CondPag condPag1 = condPagService.create(condPag);
        return new ResponseEntity<>(new ApiResponse(true, condPag1), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> update(
            @RequestBody @Valid CondPag condPag) {

        CondPag condPag1 = condPagService.update(condPag);
        return new ResponseEntity<>(new ApiResponse(true, condPag1), HttpStatus.OK);
    }

    @GetMapping("/checkDelete")
    public ResponseEntity<ApiResponse> checkDelete(
            @RequestParam(name = "codCondPag") String codCondPag) {

        condPagService.checkDelete(codCondPag);
        return new ResponseEntity<>(new ApiResponse(true, "Condição de pagamento pode ser excluída"), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> delete(
            @RequestParam(name = "codCondPag") String codCondPag) {

        condPagService.delete(codCondPag);
        return new ResponseEntity<>(new ApiResponse(true, "Condição de pagamento excluída com sucesso"), HttpStatus.OK);
    }
}
