package com.holis.san01.controller;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.model.Ncm;
import com.holis.san01.services.NcmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller para tratamento de NCM
 */
@RestController
@RequestMapping(value = "/api/ncms", produces = MediaType.APPLICATION_JSON_VALUE)
public class NcmController {
    @Autowired
    private NcmService ncmService;

    /**
     * Ler um determinado registro pelo código
     */
    @GetMapping
    public ResponseEntity<Ncm> ler(
            @RequestParam(name = "codNcm", defaultValue = "") String codNcm) {
        return ResponseEntity.status(HttpStatus.OK).body(ncmService.ler(codNcm));
    }

    /**
     * Ler uma lista de registros filtrando pelo nome/codigo e situacao
     */
    @GetMapping("/pages")
    public ResponseEntity<Page<Ncm>> listarNcm(
            @RequestParam(name = "archive", defaultValue = "0") boolean archive,
            @RequestParam(name = "filterText", defaultValue = "") String filterText,
            @PageableDefault(page = 0, size = 40)
            @SortDefault.SortDefaults({@SortDefault(sort = "codNcm")}) Pageable pageable) {

        Page<Ncm> ncms = ncmService.listarPaging(archive, filterText, pageable);
        return new ResponseEntity<>(ncms, HttpStatus.OK);
    }

    /**
     * Incluir um novo registro
     */
    @PostMapping
    public ResponseEntity<Ncm> incluir(@RequestBody @Valid Ncm ncm) {
        ncm = ncmService.incluir(ncm);
        return new ResponseEntity<>(ncm, HttpStatus.CREATED);
    }

    /**
     * Alterar um registro existente
     */
    @PutMapping
    public ResponseEntity<Ncm> alterar(@RequestBody @Valid Ncm ncm) {
        ncm = ncmService.alterar(ncm);
        return new ResponseEntity<>(ncm, HttpStatus.OK);
    }

    /**
     * Excluir um registro
     */
    @DeleteMapping
    public ResponseEntity<?> excluir(@RequestParam(name = "codNcm") String codNcm) {
        try {
            ncmService.excluir(codNcm);
        } catch (Exception ex) {
            throw new ApiRequestException(ex.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
