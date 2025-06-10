package com.holis.san01.services;

import com.holis.san01.exceptions.ApiDeleteException;
import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.exceptions.NotFoundRequestException;
import com.holis.san01.model.Entidade;
import com.holis.san01.model.EntidadeDTO;
import com.holis.san01.model.PedVenda;
import com.holis.san01.repository.EntidadeRepository;
import com.holis.san01.repository.PedVendaRepository;
import com.holis.san01.util.Mapper;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Service para tratamento de Entidade
 */
@Service
@Transactional
public class EntidadeService {

    private final SequenciaService sequenciaService;
    private final EntidadeRepository entidadeRepository;
    private final PedVendaRepository pedVendaRepository;

    public EntidadeService(SequenciaService sequenciaService, EntidadeRepository entidadeRepository, PedVendaRepository pedVendaRepository) {
        this.sequenciaService = sequenciaService;
        this.entidadeRepository = entidadeRepository;
        this.pedVendaRepository = pedVendaRepository;
    }

    /**
     * Ler uma ENTIDADE pelo codEntd
     *
     * @param codEntd
     * @return
     */
    public EntidadeDTO lerEntidade(final Long codEntd) {
        Entidade entidade = entidadeRepository.findEntidade(codEntd)
                .orElseThrow(() -> new NotFoundRequestException("Entidade não encontrada"));
        return Mapper.mapTo(entidade, EntidadeDTO.class);
    }

    /**
     * Listar Entidades
     *
     * @param tipo
     * @param archive
     * @param filterText
     * @param pageable
     * @return
     */
    public Page<EntidadeDTO> listarEntidades(final String tipo, final String archive, final String filterText, final Pageable pageable) {
        Page<Entidade> entidades = null;

        if (tipo.equalsIgnoreCase("todos")) {
            if (StringUtils.isBlank(filterText)) {
                entidades = entidadeRepository.listEntidades(archive, pageable);
            } else {
                entidades = entidadeRepository.listEntidades(archive, filterText, pageable);
            }
        }

        if (tipo.equalsIgnoreCase("clientes")) {
            if (StringUtils.isBlank(filterText)) {
                entidades = entidadeRepository.listClientes(archive, pageable);
            } else {
                entidades = entidadeRepository.listClientes(archive, filterText, pageable);
            }
        }

        if (tipo.equalsIgnoreCase("fornecedores")) {
            if (StringUtils.isBlank(filterText)) {
                entidades = entidadeRepository.listFornecedores(archive, pageable);
            } else {
                entidades = entidadeRepository.listFornecedores(archive, filterText, pageable);
            }
        }

        if (entidades == null) {
            throw new ApiRequestException("Parametros invalidos no HTTP!");
        }

        return entidades.map(this::toDto);
    }

    /**
     * Criar nova Entidade
     *
     * @param dto
     * @return
     */
    public EntidadeDTO incluirEntidade(final EntidadeDTO dto) {
        Optional<Entidade> opt = entidadeRepository.findEntidade(dto.getCodEntd());

        if (opt.isPresent()) {
            throw new ApiRequestException("Este código de Entidade já existe!");
        }

        Entidade entidade = Mapper.mapTo(dto, Entidade.class);

        // Campos chave estrangeira tem que ser convertidos para NULL
        if (StringUtils.isBlank(entidade.getCodCondPag())) {
            entidade.setCodCondPag(null);
        }

        if (entidade.getTipoFinIdEnt() == 0) {
            entidade.setTipoFinIdEnt(null);
        }

        if (entidade.getTipoFinIdSai() == 0) {
            entidade.setTipoFinIdSai(null);
        }
        // ----------------------------------------------------------

        entidade.setDtCriacao(new Date());
        entidade.setArchive("N");
        entidade = entidadeRepository.saveAndFlush(entidade);
        return Mapper.mapTo(entidade, EntidadeDTO.class);
    }

    public EntidadeDTO alterarEntidade(final EntidadeDTO dto) {
        Entidade entidade = entidadeRepository.findEntidade(dto.getCodEntd())
                .orElseThrow(() -> new NotFoundRequestException("Entidade não encontrado"));

        entidade.setNome(dto.getNome());
        entidade.setTpPessoa(dto.getTpPessoa());
        entidade.setCgc(dto.getCgc());
        entidade.setRg(dto.getRg());
        entidade.setIndCliente(dto.getIndCliente());
        entidade.setIndFornec(dto.getIndFornec());
        entidade.setIndTransp(dto.getIndTransp());
        entidade.setCep(dto.getCep());
        entidade.setLocalidade(dto.getLocalidade());
        entidade.setLogradouro(dto.getLogradouro());
        entidade.setNumero(dto.getNumero());
        entidade.setComplemento(dto.getComplemento());
        entidade.setBairro(dto.getBairro());
        entidade.setEstado(dto.getEstado());
        entidade.setPais(dto.getPais());
        entidade.setContato(dto.getContato());
        entidade.setEmail(dto.getEmail());
        entidade.setTelefone(dto.getTelefone());
        entidade.setCodCondPag(dto.getCodCondPag());
        entidade.setTipoFinIdEnt(dto.getTipoFinIdEnt());
        entidade.setTipoFinIdSai(dto.getTipoFinIdSai());
        entidade.setObsEntrega(dto.getObsEntrega());
        entidade.setObservacoes(dto.getObservacoes());
        entidade.setSituacao(dto.getSituacao());
        entidade.setArchive(dto.getArchive());

        // Campos chave estrangeira tem que ser convertidos para NULL
        if (StringUtils.isBlank(entidade.getCodCondPag())) {
            entidade.setCodCondPag(null);
        }

        if (entidade.getTipoFinIdEnt() == 0) {
            entidade.setTipoFinIdEnt(null);
        }

        if (entidade.getTipoFinIdSai() == 0) {
            entidade.setTipoFinIdSai(null);
        }

        entidade = entidadeRepository.saveAndFlush(entidade);
        return Mapper.mapTo(entidade, EntidadeDTO.class);
    }

    /**
     * Excluir um registro de Entidade
     *
     * @param codEntd
     */
    public void excluirEntidade(final Long codEntd) {
        Entidade entidade = entidadeRepository.findEntidade(codEntd)
                .orElseThrow(() -> new NotFoundRequestException(
                        "Entidade não encontrado para efetuar exclusão"));

        //  Verifica se há pedido de venda relacionado
        List<PedVenda> pedidos = pedVendaRepository.listPedVendas(entidade.getCodEntd());

        if (pedidos == null || pedidos.isEmpty()) {
            entidadeRepository.deleteById(entidade.getId());
        } else {
            throw new ApiDeleteException("Não é possível excluir o cliente. Existem pedidos de vendas associados.");
        }
    }

    /**
     * Obter o próximo código de entidade disponivel
     *
     * @return
     */
    public Long obterProximoCodigo() {
        Long codigo = sequenciaService.proximoNumero("cod_entd");

        while (true) {
            Optional<Entidade> opt = entidadeRepository.findEntidade(codigo);

            if (opt.isEmpty()) {
                return codigo;
            }
            codigo++;
        }
    }

    private EntidadeDTO toDto(final Entidade entidade) {
        return Mapper.mapTo(entidade, EntidadeDTO.class);
    }
}