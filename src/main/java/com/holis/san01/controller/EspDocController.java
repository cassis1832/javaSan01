package com.holis.san01.controller;

import com.holis.san01.dto.ApiResponse;
import com.holis.san01.services.EspDocService;
import lombok.RequiredArgsConstructor;
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

        ApiResponse apiResponse = espDocService.findById(codEspDoc);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
