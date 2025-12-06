package com.holis.san01.controller;

import com.holis.san01.mapper.CondPagMapper;
import com.holis.san01.model.CondPag;
import com.holis.san01.model.CondPagDto;
import com.holis.san01.model.local.ApiResponse;
import com.holis.san01.model.local.ApiResponse02;
import com.holis.san01.services.CondPagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller para tratamento de condição de pagamento
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/condpags", produces = MediaType.APPLICATION_JSON_VALUE)
public class CondPagController implements BaseController<CondPagDto, String, CondPag> {

    private final CondPagService condPagService;
    private final CondPagMapper condPagMapper;

    @Override
    @GetMapping
    public ResponseEntity<ApiResponse02<CondPagDto>> buscarPorId(
            @RequestParam(name = "id") String id) {
        return condPagService.findById(id)
                .map(entidade -> ResponseEntity.ok(ApiResponse02.success(condPagMapper.toDto(entidade))))
                .orElse(ResponseEntity.status(200).body(ApiResponse02.errorMessage("Item não encontrado")));
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse02<CondPagDto>> criar(
            @RequestBody @Valid CondPagDto dto) {
        CondPag salvo = condPagService.save(condPagMapper.toEntity(dto));
        CondPagDto salvoDTO = condPagMapper.toDto(salvo);
        return ResponseEntity.ok(ApiResponse02.success(salvoDTO, "Item criado com sucesso"));
    }

    @Override
    @PutMapping
    public ResponseEntity<ApiResponse02<CondPagDto>> alterar(
            @RequestBody @Valid CondPagDto dto) {
        CondPag salvo = condPagService.update(condPagMapper.toEntity(dto));
        CondPagDto salvoDTO = condPagMapper.toDto(salvo);
        return ResponseEntity.ok(ApiResponse02.success(salvoDTO, "Item alterado com sucesso"));

    }

    @Override
    @DeleteMapping
    public ResponseEntity<ApiResponse02<Void>> excluir(
            @RequestParam(name = "id") String id) {
        condPagService.deleteById(id);
        return ResponseEntity.ok(ApiResponse02.success("Item excluído sucesso"));
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<ApiResponse02<List<CondPagDto>>> buscarLista(
            @RequestParam(required = false) Map<String, String> filtros) {
        List<CondPag> entidades = condPagService.findList(filtros);
        List<CondPagDto> dtos = condPagMapper.toDtoList(entidades);
        return ResponseEntity.ok(ApiResponse02.success(dtos, "Lista de Itens"));
    }

    @Override
    @GetMapping("/page")
    public ResponseEntity<ApiResponse02<Page<CondPag>>> buscarPagina(
            Pageable pageable,
            @RequestParam(required = false) Map<String, String> filtros) {
        Page<CondPag> pagina = condPagService.findPage(pageable, filtros);
        return ResponseEntity.ok(ApiResponse02.success(pagina, "Pagina de VwItem"));
    }

    @GetMapping("/checkDelete")
    public ResponseEntity<ApiResponse> checkDelete(
            @RequestParam(name = "codCondPag") String codCondPag) {

        condPagService.checkDelete(codCondPag);
        return new ResponseEntity<>(new ApiResponse(true, "Condição de pagamento pode ser excluída"), HttpStatus.OK);
    }
}
