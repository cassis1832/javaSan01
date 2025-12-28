package com.holis.san01.controller;

import com.holis.san01.dto.ApiResponse;
import com.holis.san01.mapper.TituloApMapper;
import com.holis.san01.model.TituloAp;
import com.holis.san01.model.TituloApDto;
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
    @GetMapping
    public ResponseEntity<ApiResponse<TituloApDto>> getById(@PathVariable Integer id) {
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
    @PutMapping
    public ResponseEntity<ApiResponse<TituloApDto>> update(@RequestBody @Valid TituloApDto dto) {
        TituloAp salvo = tituloApService.update(tituloApMapper.toEntity(dto));
        TituloApDto salvoDTO = tituloApMapper.toDto(salvo);
        return ResponseEntity.ok(
                ApiResponse.success(salvoDTO, "Título alterado com sucesso")
        );
    }

    @Override
    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
        tituloApService.deleteById(id);
        return ResponseEntity.ok(
                ApiResponse.success("Título excluído sucesso")
        );
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<TituloApDto>>> getList(@RequestParam(required = false) Map<String, String> filtros) {
        List<TituloAp> entidades = tituloApService.findList(filtros);
        List<TituloApDto> dtos = tituloApMapper.toDtoList(entidades);
        return ResponseEntity.ok(
                ApiResponse.success(dtos, "Lista de títulos")
        );
    }

    @Override
    @GetMapping("/page")
    public ResponseEntity<ApiResponse<Page<VwTituloAp>>> getPage(Pageable pageable,
                                                                 @RequestParam(required = false) Map<String, String> filtros) {
        Page<VwTituloAp> pagina = tituloApService.findPage(pageable, filtros);
        return ResponseEntity.ok(
                ApiResponse.success(pagina, "Pagina de títulos")
        );
    }

    @Override
    public ResponseEntity<ApiResponse<Void>> archive(Integer integer) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse<Void>> unarchive(Integer integer) {
        return null;
    }
}
