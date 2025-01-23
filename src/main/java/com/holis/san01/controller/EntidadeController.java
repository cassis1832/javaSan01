package com.holis.san01.controller;

import com.holis.san01.exceptions.ApiDeleteException;
import com.holis.san01.exceptions.NotFoundRequestException;
import com.holis.san01.model.EntidadeDTO;
import com.holis.san01.services.EntidadeService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping(value = "/api/entds", produces = MediaType.APPLICATION_JSON_VALUE)
public class EntidadeController {

    private final EntidadeService entidadeService;

    /**
     * Ler um determinado registro pelo código
     *
     * @param codEntd
     * @return
     */
    @GetMapping("/ler")
    public ResponseEntity<EntidadeDTO> lerEntidade(
            @RequestParam(name = "codEntd", defaultValue = "") Integer codEntd) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(entidadeService.lerEntidade(codEntd));
    }

    /**
     * Ler uma lista de entidades filtrando pelo nome/codigo e situacao
     *
     * @param status
     * @param filterText
     * @param pageable
     * @return
     */
    @GetMapping("/listarPag")
    public ResponseEntity<Page<EntidadeDTO>> listarEntidades(
            @RequestParam(name = "tipo", defaultValue = "todos") String tipo,
            @RequestParam(name = "status", defaultValue = "A") String status,
            @RequestParam(name = "filterText", defaultValue = "") String filterText,
            @PageableDefault(page = 0, size = 40)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "codEntd")
            }) Pageable pageable) {

        Page<EntidadeDTO> clientes = entidadeService.listarEntidades(tipo, status, filterText, pageable);
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    /**
     * Incluir um novo registro
     *
     * @param entidadeDTO
     * @return
     */
    @PostMapping("/incluir")
    public ResponseEntity<EntidadeDTO> incluirEntidade(@RequestBody @Valid EntidadeDTO entidadeDTO) {

        entidadeDTO = entidadeService.incluirEntidade(entidadeDTO);
        return new ResponseEntity<>(entidadeDTO, HttpStatus.CREATED);
    }

    /**
     * Alterar um registro existente
     *
     * @param entidadeDTO
     * @return
     */
    @PutMapping("/alterar")
    public ResponseEntity<EntidadeDTO> alterarEntidade(@RequestBody @Valid EntidadeDTO entidadeDTO) {

        entidadeDTO = entidadeService.alterarEntidade(entidadeDTO);
        return new ResponseEntity<>(entidadeDTO, HttpStatus.OK);
    }

    /**
     * Excluir um registro
     *
     * @param codEntd
     * @return
     */
    @DeleteMapping("/excluir")
    public ResponseEntity<?> excluirEntidade(@RequestParam(name = "codEntd") Integer codEntd) {

        try {
            entidadeService.excluirEntidade(codEntd);
        } catch (Exception ex) {
            throw new ApiDeleteException(ex.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Obter o próximo codigo de cliente disponivel para criar um novo cliente
     *
     * @return
     */
    @GetMapping("/proximocodigo")
    public ResponseEntity<Integer> obterProximoCodigo() {

        Integer proximoCodigo = (Integer) entidadeService.obterProximoCodigo();
        if (proximoCodigo == null)
            throw new NotFoundRequestException(
                    "Retornando null no proximo código");

        return new ResponseEntity<>(proximoCodigo, HttpStatus.OK);
    }
}
