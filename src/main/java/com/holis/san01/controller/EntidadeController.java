package com.holis.san01.controller;

import com.holis.san01.exceptions.ApiDeleteException;
import com.holis.san01.exceptions.NotFoundRequestException;
import com.holis.san01.model.EntidadeDTO;
import com.holis.san01.services.EntidadeService;
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

    private final EntidadeService entidadeService;

    public EntidadeController(EntidadeService entidadeService) {
        this.entidadeService = entidadeService;
    }

    /**
     * Ler um determinado registro pelo código
     */
    @GetMapping("/ler")
    public ResponseEntity<EntidadeDTO> lerEntidade(
            @RequestParam(name = "codEntd", defaultValue = "") Long codEntd) {
        return ResponseEntity.status(HttpStatus.OK).body(entidadeService.lerEntidade(codEntd));
    }

    /**
     * Ler uma lista de entidades filtrando pelo nome/codigo e situacao
     */
    @GetMapping("/listarPag")
    public ResponseEntity<Page<EntidadeDTO>> listarEntidades(
            @RequestParam(name = "tipo", defaultValue = "todos") String tipo,
            @RequestParam(name = "archive", defaultValue = "N") String archive,
            @RequestParam(name = "filterText", defaultValue = "") String filterText,
            @PageableDefault(page = 0, size = 40)
            @SortDefault.SortDefaults({@SortDefault(sort = "codEntd")}) Pageable pageable) {
        Page<EntidadeDTO> clientes = entidadeService.listarEntidades(tipo, archive, filterText, pageable);
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    /**
     * Incluir um novo registro
     */
    @PostMapping("/incluir")
    public ResponseEntity<EntidadeDTO> incluirEntidade(@RequestBody @Valid EntidadeDTO entidadeDTO) {
        entidadeDTO = entidadeService.incluirEntidade(entidadeDTO);
        return new ResponseEntity<>(entidadeDTO, HttpStatus.CREATED);
    }

    /**
     * Alterar um registro existente
     */
    @PutMapping("/alterar")
    public ResponseEntity<EntidadeDTO> alterarEntidade(@RequestBody @Valid EntidadeDTO entidadeDTO) {
        entidadeDTO = entidadeService.alterarEntidade(entidadeDTO);
        return new ResponseEntity<>(entidadeDTO, HttpStatus.OK);
    }

    /**
     * Excluir um registro
     */
    @DeleteMapping("/excluir")
    public ResponseEntity<?> excluirEntidade(@RequestParam(name = "codEntd") Long codEntd) {
        try {
            entidadeService.excluirEntidade(codEntd);
        } catch (Exception ex) {
            throw new ApiDeleteException(ex.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Obter o próximo codigo de cliente disponivel para criar um novo cliente
     */
    @GetMapping("/proximocodigo")
    public ResponseEntity<Long> obterProximoCodigo() {
        Long proximoCodigo = entidadeService.obterProximoCodigo();
        if (proximoCodigo == null) {
            throw new NotFoundRequestException("Retornando null no proximo código");
        }
        return new ResponseEntity<>(proximoCodigo, HttpStatus.OK);
    }
}
