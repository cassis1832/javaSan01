package com.holis.san01.controller;

import com.holis.san01.mapper.TituloApMapper;
import com.holis.san01.model.TituloAp;
import com.holis.san01.model.local.ApiResponse;
import com.holis.san01.model.local.FiltroPesquisa;
import com.holis.san01.services.TituloApService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller para tratamento de titulos de contas pagar
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tituloaps")
public class TituloApController {

    private final TituloApService tituloApService;
    private final TituloApMapper tituloApMapper;

    @GetMapping
    public ResponseEntity<ApiResponse> findTituloAp(@RequestParam(name = "id") Integer id) {

        TituloAp tituloAp = tituloApService.findTituloAp(id);
        return new ResponseEntity<>(new ApiResponse(true, tituloApMapper.toDTO(tituloAp)), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<ApiResponse> pageVwTituloAp(
            @ModelAttribute FiltroPesquisa filtroPesquisa) {

        var page = tituloApService.pageVwTituloAp(filtroPesquisa);
        return new ResponseEntity<>(new ApiResponse(true, page), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(
            @RequestBody @Valid TituloAp tituloApDto) {

        TituloAp tituloAp = tituloApService.create(tituloApDto);
        return new ResponseEntity<>(new ApiResponse(true, tituloApMapper.toDTO(tituloAp)), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> update(
            @RequestBody @Valid TituloAp tituloApDto) {

        TituloAp tituloAp = tituloApService.update(tituloApDto);
        return new ResponseEntity<>(new ApiResponse(true, tituloApMapper.toDTO(tituloAp)), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> delete(
            @RequestParam(name = "id") Integer id) {

        tituloApService.delete(id);
        return new ResponseEntity<>(new ApiResponse(true, "Título excluído com sucesso"), HttpStatus.OK);
    }
}
