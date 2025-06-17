package com.holis.san01;

import com.holis.san01.exceptions.ApiDeleteException;
import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.exceptions.NotFoundRequestException;
import com.holis.san01.model.Entidade;
import com.holis.san01.model.EntidadeDTO;
import com.holis.san01.model.PedVenda;
import com.holis.san01.repository.EntidadeRepository;
import com.holis.san01.repository.PedVendaRepository;
import com.holis.san01.services.EntidadeService;
import com.holis.san01.util.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EntidadeServiceTest {

    @Mock
    private EntidadeRepository entidadeRepository;

    @Mock
    private PedVendaRepository pedVendaRepository;

    @InjectMocks
    private EntidadeService entidadeService;

    private Entidade entidade;
    private EntidadeDTO entidadeDTO;

    @BeforeEach
    void setUp() {
        entidade = new Entidade();
        entidade.setCodEntd(1L);
        entidade.setNome("Teste Entidade");
        entidade.setDtCriacao(new Date());
        entidade.setArchive("N");

        entidadeDTO = new EntidadeDTO();
        entidadeDTO.setCodEntd(1L);
        entidadeDTO.setNome("Teste Entidade DTO");
    }

    @Test
    void lerEntidade_deveRetornarEntidadeDTO_quandoCodigoExistir() {
        when(entidadeRepository.findEntidade(1L)).thenReturn(Optional.of(entidade));

        EntidadeDTO result = entidadeService.lerEntidade(1L);

        assertNotNull(result);
        assertEquals(entidade.getCodEntd(), result.getCodEntd());
        verify(entidadeRepository, times(1)).findEntidade(1L);
    }

    @Test
    void lerEntidade_deveLancarNotFoundRequestException_quandoCodigoNaoExistir() {
        when(entidadeRepository.findEntidade(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundRequestException.class, () -> entidadeService.lerEntidade(1L));
        verify(entidadeRepository, times(1)).findEntidade(1L);
    }

    @Test
    void listarEntidades_deveRetornarPageDeEntidadesDTO_quandoTipoForTodosSemFiltro() {
        Pageable pageable = Pageable.unpaged();
        Page<Entidade> page = new PageImpl<>(Collections.singletonList(entidade));

        when(entidadeRepository.listEntidades("N", pageable)).thenReturn(page);

        Page<EntidadeDTO> result = entidadeService.listarEntidades("todos", "N", null, pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(entidadeRepository, times(1)).listEntidades("N", pageable);
    }

    @Test
    void listarEntidades_deveRetornarPageDeClientesDTO_quandoTipoForClientesComFiltro() {
        Pageable pageable = Pageable.unpaged();
        Page<Entidade> page = new PageImpl<>(Collections.singletonList(entidade));
        String filterText = "teste";

        when(entidadeRepository.listClientes("N", filterText, pageable)).thenReturn(page);

        Page<EntidadeDTO> result = entidadeService.listarEntidades("clientes", "N", filterText, pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(entidadeRepository, times(1)).listClientes("N", filterText, pageable);
    }

    @Test
    void listarEntidades_deveLancarApiRequestException_quandoParametrosInvalidos() {
        Pageable pageable = Pageable.unpaged();

        assertThrows(ApiRequestException.class,
                () -> entidadeService.listarEntidades("invalido", "N", null, pageable));
    }

    @Test
    void incluirEntidade_deveSalvarNovaEntidade_quandoCodigoNaoExistir() {
        when(entidadeRepository.findEntidade(1L)).thenReturn(Optional.empty());
        when(entidadeRepository.saveAndFlush(any(Entidade.class))).thenReturn(entidade);

        EntidadeDTO result = entidadeService.incluirEntidade(entidadeDTO);

        assertNotNull(result);
        assertEquals(entidade.getCodEntd(), result.getCodEntd());
        verify(entidadeRepository, times(1)).findEntidade(1L);
        verify(entidadeRepository, times(1)).saveAndFlush(any(Entidade.class));
    }

    @Test
    void incluirEntidade_deveLancarApiRequestException_quandoCodigoJaExistir() {
        when(entidadeRepository.findEntidade(1L)).thenReturn(Optional.of(entidade));

        assertThrows(ApiRequestException.class, () -> entidadeService.incluirEntidade(entidadeDTO));
        verify(entidadeRepository, times(1)).findEntidade(1L);
        verify(entidadeRepository, never()).saveAndFlush(any());
    }

    @Test
    void alterarEntidade_deveAtualizarEntidade_quandoCodigoExistir() {
        when(entidadeRepository.findEntidade(1L)).thenReturn(Optional.of(entidade));
        when(entidadeRepository.saveAndFlush(any(Entidade.class))).thenReturn(entidade);

        EntidadeDTO result = entidadeService.alterarEntidade(entidadeDTO);

        assertNotNull(result);
        assertEquals(entidadeDTO.getNome(), entidade.getNome());
        verify(entidadeRepository, times(1)).findEntidade(1L);
        verify(entidadeRepository, times(1)).saveAndFlush(any(Entidade.class));
    }

    @Test
    void alterarEntidade_deveLancarNotFoundRequestException_quandoCodigoNaoExistir() {
        when(entidadeRepository.findEntidade(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundRequestException.class, () -> entidadeService.alterarEntidade(entidadeDTO));
        verify(entidadeRepository, times(1)).findEntidade(1L);
        verify(entidadeRepository, never()).saveAndFlush(any());
    }

    @Test
    void excluirEntidade_deveDeletarEntidade_quandoNaoHouverPedidosRelacionados() {
        when(entidadeRepository.findEntidade(1L)).thenReturn(Optional.of(entidade));
        when(pedVendaRepository.listPedVendas(1L)).thenReturn(Collections.emptyList());

        entidadeService.excluirEntidade(1L);

        verify(entidadeRepository, times(1)).deleteById(1L);
    }

    @Test
    void excluirEntidade_deveLancarNotFoundRequestException_quandoCodigoNaoExistir() {
        when(entidadeRepository.findEntidade(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundRequestException.class, () -> entidadeService.excluirEntidade(1L));
        verify(entidadeRepository, never()).deleteById(any());
    }

    @Test
    void excluirEntidade_deveLancarApiDeleteException_quandoHouverPedidosRelacionados() {
        when(entidadeRepository.findEntidade(1L)).thenReturn(Optional.of(entidade));
        when(pedVendaRepository.listPedVendas(1L)).thenReturn(Collections.singletonList(new PedVenda()));

        assertThrows(ApiDeleteException.class, () -> entidadeService.excluirEntidade(1L));
        verify(entidadeRepository, never()).deleteById(any());
    }
}