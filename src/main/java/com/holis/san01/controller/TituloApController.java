package com.holis.san01.controller;

import com.holis.san01.mapper.TituloApMapper;
import com.holis.san01.model.TituloAp;
import com.holis.san01.model.TituloApDto;
import com.holis.san01.model.VwTituloAp;
import com.holis.san01.model.local.ApiResponse02;
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
    public ResponseEntity<ApiResponse02<TituloApDto>> buscarPorId(
            @RequestParam(name = "id") Integer id) {
        return tituloApService.findById(id)
                .map(entidade -> ResponseEntity.ok(ApiResponse02.success(tituloApMapper.toDto(entidade))))
                .orElse(ResponseEntity.status(200).body(ApiResponse02.errorMessage("Item não encontrado")));
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse02<TituloApDto>> criar(
            @RequestBody @Valid TituloApDto dto) {
        TituloAp salvo = tituloApService.save(tituloApMapper.toEntity(dto));
        TituloApDto salvoDTO = tituloApMapper.toDto(salvo);
        return ResponseEntity.ok(ApiResponse02.success(salvoDTO, "Item criado com sucesso"));
    }

    @Override
    @PutMapping
    public ResponseEntity<ApiResponse02<TituloApDto>> alterar(
            @RequestBody @Valid TituloApDto dto) {
        TituloAp salvo = tituloApService.update(tituloApMapper.toEntity(dto));
        TituloApDto salvoDTO = tituloApMapper.toDto(salvo);
        return ResponseEntity.ok(ApiResponse02.success(salvoDTO, "Item alterado com sucesso"));
    }

    @Override
    @DeleteMapping
    public ResponseEntity<ApiResponse02<Void>> excluir(
            @RequestParam(name = "id") Integer id) {
        tituloApService.deleteById(id);
        return ResponseEntity.ok(ApiResponse02.success("Item excluído sucesso"));
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<ApiResponse02<List<TituloApDto>>> buscarLista(
            @RequestParam(required = false) Map<String, String> filtros) {
        List<TituloAp> entidades = tituloApService.findList(filtros);
        List<TituloApDto> dtos = tituloApMapper.toDtoList(entidades);
        return ResponseEntity.ok(ApiResponse02.success(dtos, "Lista de Itens"));
    }

    @Override
    @GetMapping("/page")
    public ResponseEntity<ApiResponse02<Page<VwTituloAp>>> buscarPagina(
            Pageable pageable,
            @RequestParam(required = false) Map<String, String> filtros) {
        Page<VwTituloAp> pagina = tituloApService.findPage(pageable, filtros);
        return ResponseEntity.ok(ApiResponse02.success(pagina, "Pagina de VwItem"));
    }
}
