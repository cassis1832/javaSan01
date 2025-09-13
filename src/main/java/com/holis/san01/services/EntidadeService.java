package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.exceptions.NotFoundRequestException;
import com.holis.san01.model.Entidade;
import com.holis.san01.model.PedVenda;
import com.holis.san01.repository.EntidadeRepository;
import com.holis.san01.repository.PedVendaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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
@RequiredArgsConstructor
public class EntidadeService {

    private final EntidadeRepository entidadeRepository;
    private final PedVendaRepository pedVendaRepository;

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
    public Page<Entidade> pageEntidade(
            final String criteria, final int status, final String filterText, final Pageable pageable) {

        Page<Entidade> entidades = null;

        if (criteria.equalsIgnoreCase("")) {
            if (StringUtils.isBlank(filterText)) {
                entidades = entidadeRepository.pageNenhumTipo(status, pageable);
            } else {
                entidades = entidadeRepository.pageNenhumTipo(status, filterText, pageable);
            }
        }

        if (criteria.equalsIgnoreCase("todos")) {
            if (StringUtils.isBlank(filterText)) {
                entidades = entidadeRepository.pageEntidades(status, pageable);
            } else {
                entidades = entidadeRepository.pageEntidades(status, filterText, pageable);
            }
        }

        if (criteria.equalsIgnoreCase("clientes")) {
            if (StringUtils.isBlank(filterText)) {
                entidades = entidadeRepository.pageClientes(status, pageable);
            } else {
                entidades = entidadeRepository.pageClientes(status, filterText, pageable);
            }
        }

        if (criteria.equalsIgnoreCase("fornecedores")) {
            if (StringUtils.isBlank(filterText)) {
                entidades = entidadeRepository.pageFornecedores(status, pageable);
            } else {
                entidades = entidadeRepository.pageFornecedores(status, filterText, pageable);
            }
        }

        if (entidades == null) {
            throw new ApiRequestException("Parametros invalidos no HTTP!");
        }

        return entidades;
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
        entidade.setIndCliente(entidadeInput.isIndCliente());
        entidade.setIndFornec(entidadeInput.isIndFornec());
        entidade.setIndTransp(entidadeInput.isIndTransp());
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

        Entidade entidade = entidadeRepository.findEntidadeByCodEntd(codEntd)
                .orElseThrow(() -> new ApiRequestException(
                        "Cliente/fornecedor não encontrado para exclusão"));

        //  Verifica se há pedido de venda relacionado
        List<PedVenda> pedidos = pedVendaRepository.listPedVendas(entidade.getCodEntd());

        if (!pedidos.isEmpty()) {
            throw new ApiRequestException("Não é possível excluir o cliente. Existem pedidos de vendas associados.");
        }
    }
}