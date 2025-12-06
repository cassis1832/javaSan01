package com.holis.san01.controller;

import com.holis.san01.mapper.EntidadeMapper;
import com.holis.san01.model.Entidade;
import com.holis.san01.model.EntidadeDto;
import com.holis.san01.model.local.ApiResponse02;
import com.holis.san01.services.EntidadeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller para tratamento de Entidades
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/entds", produces = MediaType.APPLICATION_JSON_VALUE)
public class EntidadeController implements BaseController<EntidadeDto, Integer, Entidade> {

    private final EntidadeService entidadeService;
    private final EntidadeMapper entidadeMapper;

    /**
     * Ler um determinado registro pelo código da entidade
     */
    @Override
    @GetMapping
    public ResponseEntity<ApiResponse02<EntidadeDto>> buscarPorId(
            @RequestParam(name = "id") Integer id) {
        return entidadeService.findById(id)
                .map(entidade -> ResponseEntity.ok(ApiResponse02.success(entidadeMapper.toDto(entidade))))
                .orElse(ResponseEntity.status(200).body(ApiResponse02.errorMessage("Item não encontrado")));
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse02<EntidadeDto>> criar(
            @RequestBody @Valid EntidadeDto dto) {
        Entidade salvo = entidadeService.save(entidadeMapper.toEntity(dto));
        EntidadeDto salvoDTO = entidadeMapper.toDto(salvo);
        return ResponseEntity.ok(ApiResponse02.success(salvoDTO, "Item criado com sucesso"));
    }

    @Override
    @PutMapping
    public ResponseEntity<ApiResponse02<EntidadeDto>> alterar(
            @RequestBody @Valid EntidadeDto dto) {
        Entidade salvo = entidadeService.update(entidadeMapper.toEntity(dto));
        EntidadeDto salvoDTO = entidadeMapper.toDto(salvo);
        return ResponseEntity.ok(ApiResponse02.success(salvoDTO, "Item alterado com sucesso"));
    }

    @Override
    @DeleteMapping
    public ResponseEntity<ApiResponse02<Void>> excluir(
            @RequestParam(name = "id") Integer id) {
        entidadeService.delete(id);
        return ResponseEntity.ok(ApiResponse02.success("Item excluído sucesso"));
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<ApiResponse02<List<EntidadeDto>>> buscarLista(
            @RequestParam(required = false) Map<String, String> filtros) {
        List<Entidade> entidades = entidadeService.findList(filtros);
        List<EntidadeDto> dtos = entidadeMapper.toDtoList(entidades);
        return ResponseEntity.ok(ApiResponse02.success(dtos, "Lista de Itens"));
    }

    @Override
    @GetMapping("/page")
    public ResponseEntity<ApiResponse02<Page<Entidade>>> buscarPagina(
            Pageable pageable,
            @RequestParam(required = false) Map<String, String> filtros) {
        Page<Entidade> pagina = entidadeService.findPage(pageable, filtros);
        return ResponseEntity.ok(ApiResponse02.success(pagina, "Pagina de Entidades"));
    }

    @Override
    @PutMapping("/archive")
    public ResponseEntity<ApiResponse02<Void>> arquivar(@RequestParam(name = "id") Integer id, @RequestParam(name = "status") Boolean status) {
        entidadeService.archive(id, status);
        return ResponseEntity.ok(ApiResponse02.success("Arquivado com sucesso"));
    }
}
