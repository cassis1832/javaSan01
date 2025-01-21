package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.exceptions.NotFoundRequestException;
import com.holis.san01.model.Entidade;
import com.holis.san01.model.EntidadeDTO;
import com.holis.san01.repository.EntidadeRepository;
import com.holis.san01.util.Converter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public EntidadeDTO lerEntidade(Integer codEntd) {

        Entidade entidade = entidadeRepository.findEntidade(codEntd)
                .orElseThrow(() -> new NotFoundRequestException(
                        "Entidade não encontrado"));

        return Converter.mapTo(entidade, EntidadeDTO.class);
    }

    public Page<EntidadeDTO> listarEntidades(String codSit, String filterText, Pageable pageable) {

        Page<Entidade> Entidades;

        if (StringUtils.isBlank(filterText)) {
            Entidades = entidadeRepository.findEntidades(codSit, pageable);
        } else {
            Entidades = entidadeRepository.findEntidades(codSit, filterText, pageable);
        }

        return Entidades.map(this::toDto);
    }

    public EntidadeDTO incluirEntidade(EntidadeDTO dto) {

        Optional<Entidade> opt = entidadeRepository.findEntidade(dto.getCodEntd());

        if (opt.isPresent()) {
            throw new ApiRequestException("Este código de Entidade já existe!");
        }

        Entidade entidade = Converter.mapTo(dto, Entidade.class);

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
        entidade = entidadeRepository.saveAndFlush(entidade);
        return Converter.mapTo(entidade, EntidadeDTO.class);
    }

    public void excluirEntidade(Integer codEntd) {

        Entidade entidade = entidadeRepository.findEntidade(codEntd)
                .orElseThrow(() -> new NotFoundRequestException(
                        "Entidade não encontrado para efetuar exclusão"));

        entidadeRepository.delete(entidade);
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