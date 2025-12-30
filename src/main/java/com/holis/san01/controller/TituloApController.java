package com.holis.san01.controller;

import com.holis.san01.dto.ApiResponse;
import com.holis.san01.dto.TituloApDto;
import com.holis.san01.mapper.TituloApMapper;
import com.holis.san01.model.TituloAp;
import com.holis.san01.model.VwTituloAp;
import com.holis.san01.services.TituloApService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller para tratamento de titulos de contas pagar
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tituloaps")
public class TituloApController implements BaseController<TituloApDto, Integer, VwTituloAp> {

    private final TituloApService tituloApService;
    private final TituloApMapper tituloApMapper;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TituloApDto>> findByID(@PathVariable Integer id) {
        TituloAp tituloAp = tituloApService.findById(id);
        return ResponseEntity.ok(
                ApiResponse.success(tituloApMapper.toDto(tituloAp)));
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<TituloApDto>> create(@RequestBody @Valid TituloApDto dto) {
        TituloAp salvo = tituloApService.create(tituloApMapper.toEntity(dto));
        TituloApDto salvoDTO = tituloApMapper.toDto(salvo);
        return ResponseEntity.ok(
                ApiResponse.success(salvoDTO, "Título criado com sucesso")
        );
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TituloApDto>> update(
            @PathVariable Integer id,
            @RequestBody @Valid TituloApDto dto) {
        TituloAp salvo = tituloApService.update(tituloApMapper.toEntity(dto));
        TituloApDto salvoDTO = tituloApMapper.toDto(salvo);
        return ResponseEntity.ok(
                ApiResponse.success(salvoDTO, "Título alterado com sucesso")
        );
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
        tituloApService.delete(id);
        return ResponseEntity.ok(
                ApiResponse.success("Título excluído sucesso")
        );
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<VwTituloAp>>> findAll(
            @RequestParam(required = false) Map<String, String> filtros) {
        List<VwTituloAp> vwTituloAps = tituloApService.findAll(filtros);
        return ResponseEntity.ok(
                ApiResponse.success(vwTituloAps, "Lista de títulos")
        );
    }

    @Override
    @GetMapping("/page")
    public ResponseEntity<ApiResponse<Page<VwTituloAp>>> findPage(
            Pageable pageable,
            @RequestParam(required = false) Map<String, String> filtros) {
        Page<VwTituloAp> pagina = tituloApService.findPage(pageable, filtros);
        return ResponseEntity.ok(
                ApiResponse.success(pagina, "Pagina de títulos")
        );
    }

    @Override
    public ResponseEntity<ApiResponse<Void>> arquivar(Integer integer) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<Void>> desarquivar(Integer integer) {
        return null;
    }
}
