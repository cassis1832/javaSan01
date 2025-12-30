package com.holis.san01.controller;

import com.holis.san01.dto.ApiResponse;
import com.holis.san01.dto.UsuarioDto;
import com.holis.san01.mapper.UsuarioMapper;
import com.holis.san01.model.Usuario;
import com.holis.san01.model.VwUsuario;
import com.holis.san01.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController implements BaseController<UsuarioDto, Integer, VwUsuario> {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioDto>> findByID(@PathVariable Integer id) {
        Usuario usuario = usuarioService.findById(id);
        return ResponseEntity.ok(
                ApiResponse.success(usuarioMapper.toDto(usuario))
        );
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<UsuarioDto>> create(@RequestBody @Valid UsuarioDto dto) {
        Usuario usuario = usuarioService.create(usuarioMapper.toEntity(dto));
        UsuarioDto usuarioDto = usuarioMapper.toDto(usuario);
        return ResponseEntity.ok(
                ApiResponse.success(usuarioDto, "Usuário criado com sucesso")
        );
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioDto>> update(
            @PathVariable Integer id,
            @RequestBody @Valid UsuarioDto dto) {
        Usuario usuario = usuarioService.update(usuarioMapper.toEntity(dto));
        UsuarioDto usuarioDto = usuarioMapper.toDto(usuario);
        return ResponseEntity.ok(
                ApiResponse.success(usuarioDto, "Usuário alterado com sucesso")
        );
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
        usuarioService.delete(id);
        return ResponseEntity.ok(
                ApiResponse.success("Usuário excluído sucesso")
        );
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<VwUsuario>>> findAll(
            @RequestParam(required = false) Map<String, String> filtros) {
        List<VwUsuario> vwUsuarios = usuarioService.findAll(filtros);
        return ResponseEntity.ok(
                ApiResponse.success(vwUsuarios, "Lista de usuários")
        );
    }

    @Override
    @GetMapping("/page")
    public ResponseEntity<ApiResponse<Page<VwUsuario>>> findPage(
            Pageable pageable,
            @RequestParam(required = false) Map<String, String> filtros) {
        Page<VwUsuario> pagina = usuarioService.findPage(pageable, filtros);
        return ResponseEntity.ok(
                ApiResponse.success(pagina, "Pagina de Usuarios")
        );
    }

    @Override
    public ResponseEntity<ApiResponse<Void>> arquivar(@PathVariable Integer id) {
        usuarioService.arquivar(id);
        return ResponseEntity.ok(
                ApiResponse.success("Usuário arquivado com sucesso")
        );
    }

    @Override
    public ResponseEntity<ApiResponse<Void>> desarquivar(@PathVariable Integer id) {
        usuarioService.desarquivar(id);
        return ResponseEntity.ok(
                ApiResponse.success("Usuário desarquivado com sucesso")
        );
    }
}
