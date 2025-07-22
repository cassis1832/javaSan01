package com.holis.san01.controller;

import com.holis.san01.model.EntidadeDTO;
import com.holis.san01.services.EntidadeService;
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

/**
 * Controller para tratamento de Entidades
 */
@RestController
@RequestMapping(value = "/api/entds", produces = MediaType.APPLICATION_JSON_VALUE)
public class EntidadeController {
    @Autowired
    private EntidadeService entidadeService;

    /**
     * Ler um determinado registro pelo código da entidade
     */
    @GetMapping
    public ResponseEntity<EntidadeDTO> ler(
            @RequestParam(name = "codEntd", defaultValue = "0") Integer codEntd) {
        return ResponseEntity.status(HttpStatus.OK).body(entidadeService.ler(codEntd));
    }

    /**
     * Ler uma lista de entidades paginada com filtro
     */
    @GetMapping("/pages")
    public ResponseEntity<Page<EntidadeDTO>> listarTodosPaging(
            @RequestParam(name = "tipo", defaultValue = "") String tipoEntd,
            @RequestParam(name = "archive", defaultValue = "N") String archive,
            @RequestParam(name = "filterText", defaultValue = "") String filterText,
            @PageableDefault(page = 0, size = 40)
            @SortDefault.SortDefaults({@SortDefault(sort = "codEntd")}) Pageable pageable) {

        Page<EntidadeDTO> clientes = entidadeService.listarPaging(tipoEntd, archive, filterText, pageable);
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    /**
     * Incluir um novo registro de entidade
     */
    @PostMapping
    public ResponseEntity<EntidadeDTO> incluir(
            @RequestBody @Valid EntidadeDTO entidadeDTO) {

        entidadeDTO = entidadeService.incluir(entidadeDTO);
        return new ResponseEntity<>(entidadeDTO, HttpStatus.CREATED);
    }

    /**
     * Alterar um registro existente
     */
    @PutMapping
    public ResponseEntity<EntidadeDTO> alterar(
            @RequestBody @Valid EntidadeDTO entidadeDTO) {

        entidadeDTO = entidadeService.alterar(entidadeDTO);
        return new ResponseEntity<>(entidadeDTO, HttpStatus.OK);
    }

    /**
     * Excluir um registro
     */
    @DeleteMapping
    public ResponseEntity<?> excluir(
            @RequestParam(name = "codEntd") Integer codEntd) {

//        try {
        entidadeService.excluir(codEntd);
//        } catch (Exception ex) {
//            throw new ApiDeleteException(ex.getMessage());
//        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
