package com.holis.san01.controller;

import com.holis.san01.dto.ApiResponse02;
import com.holis.san01.mapper.UsuarioMapper;
import com.holis.san01.model.UsuarioDto;
import com.holis.san01.model.VwUsuario;
import com.holis.san01.services.UsuarioService;
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
    @GetMapping
    public ResponseEntity<ApiResponse02<UsuarioDto>> getById(Integer integer) {
        return null;
    }

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse02<UsuarioDto>> create(UsuarioDto usuarioDto) {
        return null;
    }

    @Override
    @PutMapping
    public ResponseEntity<ApiResponse02<UsuarioDto>> update(UsuarioDto usuarioDto) {
        return null;
    }

    @Override
    @DeleteMapping
    public ResponseEntity<ApiResponse02<Void>> delete(Integer integer) {
        return null;
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<ApiResponse02<List<UsuarioDto>>> getList(Map<String, String> filtros) {
        return null;
    }

    @Override
    @GetMapping("/page")
    public ResponseEntity<ApiResponse02<Page<VwUsuario>>> getPage(Pageable pageable, Map<String, String> filtros) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse02<Void>> archive(Integer integer) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse02<Void>> unarchive(Integer integer) {
        return null;
    }
}
