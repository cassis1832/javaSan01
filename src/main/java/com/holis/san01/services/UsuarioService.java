package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.exceptions.NotFoundRequestException;
import com.holis.san01.model.Usuario;
import com.holis.san01.model.VwUsuario;
import com.holis.san01.repository.UsuarioRepository;
import com.holis.san01.repository.VwUsuarioRepository;
import com.holis.san01.utils.SpecificationUtils;
import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static com.holis.san01.utils.Constantes.STATUS_ARQUIVADO;
import static com.holis.san01.utils.Constantes.STATUS_ATIVO;

/**
 * Service para tratamento de usuarios
 */
@Service
@RequiredArgsConstructor
public class UsuarioService implements BaseService<Usuario, Integer, VwUsuario> {

    private final UsuarioRepository usuarioRepository;
    private final VwUsuarioRepository vwUsuarioRepository;

    @Override
    public Usuario findById(@Nonnull Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException("Usuário não cadastrado"));
    }

    @Override
    @Transactional
    public Usuario create(@Nonnull Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new ApiRequestException("Este email já existe!");
        }

        usuario.setStatus(0);
        usuario.setDtCriacao(Instant.now());
        return usuarioRepository.saveAndFlush(usuario);
    }

    @Override
    @Transactional
    public Usuario update(@Nonnull Usuario usuario) {
        Usuario existing = usuarioRepository.findById(usuario.getId())
                .orElseThrow(() -> new NotFoundRequestException("Usuário não cadastrado"));

        existing.setNome(usuario.getNome());
        existing.setEmail(usuario.getEmail());
        existing.setCodFilial(usuario.getCodFilial());
        existing.setRoles(usuario.getRoles());
        existing.setDtInicio(usuario.getDtInicio());
        existing.setDtTermino(usuario.getDtTermino());
        existing.setSituacao(usuario.getSituacao());
        existing.setStatus(usuario.getStatus());
        return usuarioRepository.saveAndFlush(existing);
    }

    @Override
    public void deleteById(@Nonnull Integer id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public List<Usuario> findList(Map<String, String> filters) {
        return List.of();
    }

    @Override
    public Page<VwUsuario> findPage(Pageable pageable, Map<String, String> filtros) {
        Specification<VwUsuario> spec = SpecificationUtils.createSpecification(
                filtros,                            // Map com parâmetros da requisição
                "email", "nome"    // campos que serão usados no OR do filterText
        );

        return vwUsuarioRepository.findAll(spec, pageable);
    }

    @Transactional
    public void archive(@Nonnull Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException("Usuario não cadastrado"));

        usuarioRepository.archive(usuario.getId(), STATUS_ARQUIVADO);
    }

    @Transactional
    public void unarchive(@Nonnull Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundRequestException("Usuario não cadastrado"));

        usuarioRepository.archive(usuario.getId(), STATUS_ATIVO);
    }
}
