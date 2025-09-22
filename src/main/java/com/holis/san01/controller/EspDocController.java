package com.holis.san01.controller;

import com.holis.san01.model.local.ApiResponse;
import com.holis.san01.services.EspDocService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller para tratamento de Especie de documento
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/espdocs", produces = MediaType.APPLICATION_JSON_VALUE)
public class EspDocController {

    private final EspDocService espDocService;

    @GetMapping
    public ResponseEntity<ApiResponse> getEspDoc(
            @RequestParam(name = "codEspDoc", defaultValue = "") String codEspDoc) {

        ApiResponse apiResponse = espDocService.getEspDoc(codEspDoc);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<ApiResponse> pageEspDoc(
            @RequestParam(name = "tipo", defaultValue = "0") boolean tipo,
            @RequestParam(name = "filterText", defaultValue = "") String filterText,
            @PageableDefault(size = 40)
            @SortDefault.SortDefaults({@SortDefault(sort = "codEspDoc")}) Pageable pageable) {

        ApiResponse apiResponse = espDocService.pageEspDoc(tipo, pageable);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
