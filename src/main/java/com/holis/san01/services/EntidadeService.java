package com.holis.san01.services;

import com.holis.san01.exceptions.ApiDeleteException;
import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.exceptions.NotFoundRequestException;
import com.holis.san01.model.Entidade;
import com.holis.san01.model.EntidadeDTO;
import com.holis.san01.model.PedVenda;
import com.holis.san01.repository.EntidadeRepository;
import com.holis.san01.repository.PedVendaRepository;
import com.holis.san01.util.Converter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class EntidadeService {

    private final SequenciaService sequenciaService;
    private final EntidadeRepository entidadeRepository;
    private final PedVendaRepository pedVendaRepository;

    public EntidadeDTO lerEntidade(Integer codEntd) {

        Entidade entidade = entidadeRepository.findEntidade(codEntd)
                .orElseThrow(() -> new NotFoundRequestException(
                        "Entidade não encontrado"));

        return Converter.mapTo(entidade, EntidadeDTO.class);
    }

    public Page<EntidadeDTO> listarEntidades(
            String tipo, String status, String filterText, Pageable pageable) {

        Page<Entidade> Entidades = null;

        if (tipo.equals("todos")) {
            if (StringUtils.isBlank(filterText)) {
                Entidades = entidadeRepository.findEntidades(status, pageable);
            } else {
                Entidades = entidadeRepository.findEntidades(status, filterText, pageable);
            }
        }

        if (tipo.equals("clientes")) {
            if (StringUtils.isBlank(filterText)) {
                Entidades = entidadeRepository.findClientes(status, pageable);
            } else {
                Entidades = entidadeRepository.findClientes(status, filterText, pageable);
            }
        }

        if (tipo.equals("fornecedores")) {
            if (StringUtils.isBlank(filterText)) {
                Entidades = entidadeRepository.findFornecedores(status, pageable);
            } else {
                Entidades = entidadeRepository.findFornecedores(status, filterText, pageable);
            }
        }

        if (Entidades != null) {
            return Entidades.map(this::toDto);
        }

        throw new ApiRequestException("Parametros invalidos no HTTP!");
    }

    public EntidadeDTO incluirEntidade(EntidadeDTO dto) {

        Optional<Entidade> opt = entidadeRepository.findEntidade(dto.getCodEntd());

        if (opt.isPresent()) {
            throw new ApiRequestException("Este código de Entidade já existe!");
        }

        Entidade entidade = Converter.mapTo(dto, Entidade.class);

        // Campos chave estrangeira tem que ser convertidos para NULL
        if (entidade.getCodCondPag() == null || entidade.getCodCondPag().isEmpty()) {
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
        entidade.setStatus("A");
        entidade = entidadeRepository.saveAndFlush(entidade);
        return Converter.mapTo(entidade, EntidadeDTO.class);
    }

    public EntidadeDTO alterarEntidade(EntidadeDTO dto) {

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
        entidade.setStatus(dto.getStatus());

        // Campos chave estrangeira tem que ser convertidos para NULL
        if (entidade.getCodCondPag() == null || entidade.getCodCondPag().isEmpty()) {
            entidade.setCodCondPag(null);
        }

        if (entidade.getTipoFinIdEnt() == 0) {
            entidade.setTipoFinIdEnt(null);
        }

        if (entidade.getTipoFinIdSai() == 0) {
            entidade.setTipoFinIdSai(null);
        }

        entidade = entidadeRepository.saveAndFlush(entidade);
        return Converter.mapTo(entidade, EntidadeDTO.class);
    }

    public void excluirEntidade(Integer codEntd) {

        Entidade entidade = entidadeRepository.findEntidade(codEntd)
                .orElseThrow(() -> new NotFoundRequestException(
                        "Entidade não encontrado para efetuar exclusão"));

        //  Verifica se há pedido de venda relacionado
        List<PedVenda> pedidos = pedVendaRepository.ListPedVendas(entidade.getCodEntd());

        if (pedidos == null || pedidos.isEmpty()) {
            entidadeRepository.deleteById(entidade.getCodEntd());
        } else {
            throw new ApiDeleteException("Não é possível excluir o cliente. Existem pedidos de vendas associados.");
        }
    }


    public Integer obterProximoCodigo() {

        Integer codigo = sequenciaService.proximoNumero("cod_entd");

        while (true) {
            Optional<Entidade> opt = entidadeRepository.findEntidade(codigo);

            if (opt.isEmpty()) {
                return codigo;
            }
            codigo++;
        }
    }

    private EntidadeDTO toDto(Entidade Entidade) {
        return Converter.mapTo(Entidade, EntidadeDTO.class);
    }
}