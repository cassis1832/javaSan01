package com.holis.san01.controller;

import com.holis.san01.model.ApiResponse;
import com.holis.san01.model.TituloAp;
import com.holis.san01.services.TituloApService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tituloaps")
public class TituloApController {
    private final TituloApService servico;

    @GetMapping
    public ResponseEntity<ApiResponse> getTituloAp(@RequestParam(name = "id") Integer id) {
        ApiResponse apiResponse = servico.getTituloAp(id);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/lists")
    public ResponseEntity<ApiResponse> listVwTituloAp(
            @RequestParam(name = "archive", defaultValue = "0") Boolean archive) {
        ApiResponse apiResponse = servico.listVwTituloAp(archive);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/pages")
    public ResponseEntity<ApiResponse> pageVwTituloAp(
            @RequestParam(name = "archive", defaultValue = "0") Boolean archive,
            @RequestParam(name = "filterText", defaultValue = "") String filterText,
            @PageableDefault(size = 20) @SortDefault.SortDefaults({
                    @SortDefault(sort = "dtVencto", direction = Sort.Direction.ASC),
                    @SortDefault(sort = "codEntd", direction = Sort.Direction.ASC)
            }) Pageable pageable) {

        ApiResponse apiResponse = servico.pageVwTituloAp(archive, pageable, filterText);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/listCodEspDoc")
    public ResponseEntity<ApiResponse> listVwTituloApPorTipoRef(
            @RequestParam(name = "codEspDoc", defaultValue = "") String codEspDoc,
            @RequestParam(name = "archive", defaultValue = "0") Boolean archive) {

        ApiResponse apiResponse = servico.listVwTituloAp(archive, codEspDoc);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/listDocRef")
    public ResponseEntity<ApiResponse> listVwTituloApPorReferencia(
            @RequestParam(name = "codEspDoc") String codEspDoc,
            @RequestParam(name = "docId") Integer docId) {

        ApiResponse apiResponse = servico.listVwTituloAp(codEspDoc, docId);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(
            @RequestBody @Valid TituloAp tituloApDto) {

        ApiResponse apiResponse = servico.create(tituloApDto);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> update(
            @RequestBody @Valid TituloAp tituloApDto) {

        ApiResponse apiResponse = servico.update(tituloApDto);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> delete(
            @RequestParam(name = "id") Integer id) {

        ApiResponse apiResponse = servico.delete(id);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
