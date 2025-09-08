package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.exceptions.NotFoundRequestException;
import com.holis.san01.model.ApiResponse;
import com.holis.san01.model.Entidade;
import com.holis.san01.model.PedVenda;
import com.holis.san01.repository.EntidadeRepository;
import com.holis.san01.repository.PedVendaRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service para tratamento de Entidade
 */
@Service
public class EntidadeService {
    @Autowired
    private EntidadeRepository entidadeRepository;

    @Autowired
    private PedVendaRepository pedVendaRepository;

    /**
     * Ler uma ENTIDADE pelo codEntd
     */
    public ApiResponse getEntidade(final Integer codEntd) {
        Entidade entidade = entidadeRepository.getEntidade(codEntd)
                .orElseThrow(() -> new NotFoundRequestException("Cliente/fornecedor não encontrado"));
        return new ApiResponse(true, entidade);
    }

    /**
     * Listar Entidades
     */
    public ApiResponse pageEntidade(final String criteria, final boolean archive, final String filterText, final Pageable pageable) {
        Page<Entidade> entidades = null;

        if (criteria.equalsIgnoreCase("")) {
            if (StringUtils.isBlank(filterText)) {
                entidades = entidadeRepository.pageNenhumTipo(archive, pageable);
            } else {
                entidades = entidadeRepository.pageNenhumTipo(archive, filterText, pageable);
            }
        }

        if (criteria.equalsIgnoreCase("todos")) {
            if (StringUtils.isBlank(filterText)) {
                entidades = entidadeRepository.pageEntidades(archive, pageable);
            } else {
                entidades = entidadeRepository.pageEntidades(archive, filterText, pageable);
            }
        }

        if (criteria.equalsIgnoreCase("clientes")) {
            if (StringUtils.isBlank(filterText)) {
                entidades = entidadeRepository.pageClientes(archive, pageable);
            } else {
                entidades = entidadeRepository.pageClientes(archive, filterText, pageable);
            }
        }

        if (criteria.equalsIgnoreCase("fornecedores")) {
            if (StringUtils.isBlank(filterText)) {
                entidades = entidadeRepository.pageFornecedores(archive, pageable);
            } else {
                entidades = entidadeRepository.pageFornecedores(archive, filterText, pageable);
            }
        }

        if (entidades == null) {
            throw new ApiRequestException("Parametros invalidos no HTTP!");
        }

        return new ApiResponse(true, entidades);
    }

    /**
     * Criar nova Entidade
     */
    @Transactional
    public ApiResponse create(final Entidade dto) {
        Optional<Entidade> opt = entidadeRepository.getEntidade(dto.getCodEntd());

        if (opt.isPresent()) {
            throw new ApiRequestException("Este código de cliente/fornecedor já existe!");
        }

        // Campos chave estrangeira tem que ser convertidos para NULL
        if (StringUtils.isBlank(dto.getCodCondPag())) {
            dto.setCodCondPag(null);
        }

        dto.setDtCriacao(LocalDate.now());
        dto.setArchive(false);
        Entidade entidade = entidadeRepository.saveAndFlush(dto);
        return new ApiResponse(true, entidade);
    }

    /**
     * Alterar Entidade
     */
    @Transactional
    public ApiResponse update(final Entidade dto) {
        Entidade entidade = entidadeRepository.getEntidade(dto.getCodEntd())
                .orElseThrow(() -> new ApiRequestException("Entidade não encontrado"));

        entidade.setNome(dto.getNome());
        entidade.setRazaoSocial(dto.getRazaoSocial());
        entidade.setTpPessoa(dto.getTpPessoa());
        entidade.setCgc(dto.getCgc());
        entidade.setRg(dto.getRg());
        entidade.setIndCliente(dto.isIndCliente());
        entidade.setIndFornec(dto.isIndFornec());
        entidade.setIndTransp(dto.isIndTransp());
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
        entidade.setArchive(dto.isArchive());

        // Campos chave estrangeira tem que ser convertidos para NULL
        if (StringUtils.isBlank(entidade.getCodCondPag())) {
            entidade.setCodCondPag(null);
        }

        entidade = entidadeRepository.saveAndFlush(entidade);
        return new ApiResponse(true, entidade);
    }

    /**
     * Excluir um registro de Entidade
     */
    @Transactional
    public ApiResponse delete(final Integer codEntd) {
        checkDelete(codEntd);
        entidadeRepository.deleteById(codEntd);
        return new ApiResponse(true, "Exclusão efetuada com sucesso");
    }

    /**
     * Verificar se o cliente pode ser deletado
     */
    public ApiResponse checkDelete(Integer codEntd) {
        Entidade entidade = entidadeRepository.getEntidade(codEntd)
                .orElseThrow(() -> new ApiRequestException(
                        "Cliente/fornecedor não encontrado para exclusão"));

        //  Verifica se há pedido de venda relacionado
        List<PedVenda> pedidos = pedVendaRepository.listPedVendas(entidade.getCodEntd());

        if (!pedidos.isEmpty()) {
            throw new ApiRequestException("Não é possível excluir o cliente. Existem pedidos de vendas associados.");
        }

        return new ApiResponse(true, "Exclusão pode ser efetuada");
    }

//    private EntidadeDTO toDto(final Entidade entidade) {
//        return Mapper.mapTo(entidade, EntidadeDTO.class);
//    }
}