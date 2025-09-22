package com.holis.san01.controller;

import com.holis.san01.mapper.TituloApMapper;
import com.holis.san01.model.local.ApiResponse;
import com.holis.san01.model.TituloAp;
import com.holis.san01.model.VwTituloAp;
import com.holis.san01.services.TituloApService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/list")
    public ResponseEntity<ApiResponse> listVwTituloAp(
            @RequestParam(name = "status" , defaultValue = "0") Integer status,
            @RequestParam(name = "codEntd", defaultValue = "0") Integer codEntd,
            @RequestParam(name = "codEspDco", defaultValue = "") String codEspDoc,
            @RequestParam(name = "docId", defaultValue = "0") Integer docId,
            @RequestParam(name = "vencto", defaultValue = "0") String vencto,
            @RequestParam(name = "filterText", defaultValue = "") String filterText) {

        List<VwTituloAp> vwTituloAp = tituloApService.listVwTituloAp(
                status, codEntd, codEspDoc, docId, vencto, filterText);
        return new ResponseEntity<>(new ApiResponse(true, vwTituloAp), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<ApiResponse> pageVwTituloAp(
            @RequestParam(name = "status", defaultValue = "0") Integer status,
            @RequestParam(name = "codEntd", defaultValue = "0") Integer codEntd,
            @RequestParam(name = "codEspDco", defaultValue = "") String codEspDoc,
            @RequestParam(name = "docId", defaultValue = "0") Integer docId,
            @RequestParam(name = "vencto", defaultValue = "") String vencto,
            @RequestParam(name = "filterText", defaultValue = "") String filterText,
            @PageableDefault(size = 20) @SortDefault.SortDefaults({
                    @SortDefault(sort = "dtVencto", direction = Sort.Direction.ASC),
                    @SortDefault(sort = "codEntd", direction = Sort.Direction.ASC)
            }) Pageable pageable) {

        Page<VwTituloAp> pageVwTituloAp = tituloApService.pageVwTituloAp(
                status, codEntd, codEspDoc, docId, vencto, filterText, pageable);
        return new ResponseEntity<>(new ApiResponse(true, pageVwTituloAp), HttpStatus.OK);
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
