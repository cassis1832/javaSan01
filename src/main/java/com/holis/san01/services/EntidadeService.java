package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.exceptions.NotFoundRequestException;
import com.holis.san01.model.Entidade;
import com.holis.san01.model.local.FiltroPesquisa;
import com.holis.san01.repository.EntidadeRepository;
import com.holis.san01.repository.PedVendaRepository;
import com.holis.san01.repository.TituloApRepository;
import com.holis.san01.specs.EntidadeSpecifications;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Service para tratamento de Entidade
 */
@Service
@RequiredArgsConstructor
public class EntidadeService {

    private final EntidadeRepository entidadeRepository;
    private final PedVendaRepository pedVendaRepository;
    private final TituloApRepository tituloApRepository;

    /**
     * Ler uma ENTIDADE pelo codEntd
     */
    public Entidade getEntidade(final Integer codEntd) {

        return entidadeRepository.findEntidadeByCodEntd(codEntd)
                .orElseThrow(() -> new NotFoundRequestException("Cliente/fornecedor não encontrado"));
    }

    /**
     * Listar Entidades
     */
    public Page<Entidade> pageEntidade(FiltroPesquisa filtro) {

        Specification<Entidade> spec = Specification.where(null);

        if (filtro.getStatus() != null)
            spec = spec.and(EntidadeSpecifications.hasStatus(filtro.getStatus()));

        if (!StringUtils.isBlank(filtro.getFilterText()))
            spec = spec.and(EntidadeSpecifications.hasFiltro(filtro.getFilterText()));

        if (filtro.getTipo().equalsIgnoreCase("nenhum"))
            spec = spec.and(EntidadeSpecifications.nenhumTipo());

        if (filtro.getTipo().equalsIgnoreCase("todos"))
            spec = spec.and(EntidadeSpecifications.ambos());

        if (filtro.getTipo().equalsIgnoreCase("clientes"))
            spec = spec.and(EntidadeSpecifications.clientes());

        if (filtro.getTipo().equalsIgnoreCase("fornecedores"))
            spec = spec.and(EntidadeSpecifications.fornecedores());

        Sort sort = Sort.by(Sort.Direction.fromString(filtro.getSortDirection()), filtro.getSortField());

        Pageable pageable = PageRequest.of(filtro.getPageIndex(), filtro.getSize(), sort);
        return entidadeRepository.findAll(spec, pageable);
    }

    /**
     * Criar nova Entidade
     */
    @Transactional
    public Entidade create(final Entidade entidadeInput) {

        Optional<Entidade> opt = entidadeRepository.findEntidadeByCodEntd(entidadeInput.getCodEntd());

        if (opt.isPresent()) {
            throw new ApiRequestException("Este código de cliente/fornecedor já existe!");
        }

        // Campos chave estrangeira tem que ser convertidos para NULL
        if (StringUtils.isBlank(entidadeInput.getCodCondPag())) {
            entidadeInput.setCodCondPag(null);
        }

        entidadeInput.setDtCriacao(LocalDate.now());
        entidadeInput.setStatus(0);
        return entidadeRepository.saveAndFlush(entidadeInput);
    }

    /**
     * Alterar Entidade
     */
    @Transactional
    public Entidade update(final Entidade entidadeInput) {

        Entidade entidade = entidadeRepository.findEntidadeByCodEntd(entidadeInput.getCodEntd())
                .orElseThrow(() -> new ApiRequestException("Entidade não encontrado"));

        entidade.setNome(entidadeInput.getNome());
        entidade.setRazaoSocial(entidadeInput.getRazaoSocial());
        entidade.setTpPessoa(entidadeInput.getTpPessoa());
        entidade.setCgc(entidadeInput.getCgc());
        entidade.setRg(entidadeInput.getRg());
        entidade.setIndCliente(entidadeInput.getIndCliente());
        entidade.setIndFornec(entidadeInput.getIndFornec());
        entidade.setIndTransp(entidadeInput.getIndTransp());
        entidade.setCep(entidadeInput.getCep());
        entidade.setLocalidade(entidadeInput.getLocalidade());
        entidade.setLogradouro(entidadeInput.getLogradouro());
        entidade.setNumero(entidadeInput.getNumero());
        entidade.setComplemento(entidadeInput.getComplemento());
        entidade.setBairro(entidadeInput.getBairro());
        entidade.setEstado(entidadeInput.getEstado());
        entidade.setPais(entidadeInput.getPais());
        entidade.setContato(entidadeInput.getContato());
        entidade.setEmail(entidadeInput.getEmail());
        entidade.setTelefone(entidadeInput.getTelefone());
        entidade.setCodCondPag(entidadeInput.getCodCondPag());
        entidade.setTipoFinIdEnt(entidadeInput.getTipoFinIdEnt());
        entidade.setTipoFinIdSai(entidadeInput.getTipoFinIdSai());
        entidade.setObsEntrega(entidadeInput.getObsEntrega());
        entidade.setObservacoes(entidadeInput.getObservacoes());
        entidade.setSituacao(entidadeInput.getSituacao());

        entidade.setLibCompra(entidadeInput.getLibCompra());
        entidade.setLibVenda(entidadeInput.getLibVenda());
        entidade.setLibPagamento(entidadeInput.getLibPagamento());

        entidade.setStatus(entidadeInput.getStatus());

        // Campos chave estrangeira tem que ser convertidos para NULL
        if (StringUtils.isBlank(entidade.getCodCondPag())) {
            entidade.setCodCondPag(null);
        }

        return entidadeRepository.saveAndFlush(entidade);
    }

    /**
     * Excluir um registro de Entidade
     */
    @Transactional
    public void delete(final Integer codEntd) {

        checkDelete(codEntd);
        entidadeRepository.deleteById(codEntd);
    }

    /**
     * Verificar se o cliente pode ser deletado
     */
    public void checkDelete(Integer codEntd) {

        if (!entidadeRepository.existsByCodEntd(codEntd))
            throw new ApiRequestException("Cliente/fornecedor não encontrado para exclusão");

        //  Verifica se há pedido de venda relacionado
        if (pedVendaRepository.existsByCodEntd(codEntd))
            throw new ApiRequestException("Não é possível excluir o cliente. Existem pedidos de vendas associados.");

        //  Verifica se há titulos de contas a pagar relacionados ao cliente
        if (tituloApRepository.existsByCodEntd(codEntd))
            throw new ApiRequestException("Não é possível excluir o cliente. Existem títulos financeiros.");
    }
}