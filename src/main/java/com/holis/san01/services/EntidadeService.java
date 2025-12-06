package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.model.Entidade;
import com.holis.san01.repository.EntidadeRepository;
import com.holis.san01.repository.PedVendaRepository;
import com.holis.san01.repository.TituloApRepository;
import com.holis.san01.utils.SpecificationUtils;
import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Service para tratamento de Entidade
 */
@Service
@RequiredArgsConstructor
public class EntidadeService implements BaseService<Entidade, Integer, Entidade> {

    private final EntidadeRepository entidadeRepository;
    private final PedVendaRepository pedVendaRepository;
    private final TituloApRepository tituloApRepository;

    @Override
    public Optional<Entidade> findById(Integer id) {
        return entidadeRepository.findByCodEntd(id);
    }

    @Override
    @Transactional
    public Entidade save(@Nonnull Entidade entidade) {
        if (entidadeRepository.existsByCodEntd(entidade.getCodEntd())) {
            throw new ApiRequestException("Este código de item já existe!");
        }

        // Campos chave estrangeira tem que ser convertidos para NULL
        if (StringUtils.isBlank(entidade.getCodCondPag())) {
            entidade.setCodCondPag(null);
        }

        entidade.setDtCriacao(LocalDate.now());
        entidade.setStatus(0);
        return entidadeRepository.saveAndFlush(entidade);
    }

    @Override
    @Transactional
    public Entidade update(@Nonnull final Entidade entidadeInput) {
        Entidade entidade = entidadeRepository.findByCodEntd(entidadeInput.getCodEntd())
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

    @Override
    @Transactional
    public void deleteById(@Nonnull final Integer codEntd) {
        checkDelete(codEntd);
        entidadeRepository.deleteById(codEntd);
    }

    @Override
    public List<Entidade> findList(Map<String, String> filters) {
        Specification<Entidade> spec = SpecificationUtils.createSpecification(filters);
        return entidadeRepository.findAll(spec);
    }

    @Override
    public Page<Entidade> findPage(Pageable pageable, Map<String, String> filtros) {
        Specification<Entidade> spec = SpecificationUtils.createSpecification(
                filtros,                                    // Map com parâmetros da requisição
                "nome", "razaoSocial"     // campos que serão usados no OR do filterText
        );

        return entidadeRepository.findAll(spec, pageable);
    }

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