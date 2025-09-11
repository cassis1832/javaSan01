package com.holis.san01.services;

import com.holis.san01.exceptions.ApiRequestException;
import com.holis.san01.exceptions.NotFoundRequestException;
import com.holis.san01.model.*;
import com.holis.san01.repository.PedVendaRepository;
import com.holis.san01.repository.VwPedVendaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service para tratamento de pedidos de vendas
 */
@Service
@RequiredArgsConstructor
public class PedVendaService {

    private final PedVendaRepository pedVendaRepository;
    private final VwPedVendaRepository vwPedVendaRepository;

    /**
     * Ler o pedido de venda pelo numero do pedido
     */
    public PedVenda getPedVenda(final Integer nrPedido) {
        return pedVendaRepository.getPedVenda(nrPedido)
                .orElseThrow(() -> new NotFoundRequestException("Pedido de venda não encontrado"));
    }

    public Page<VwPedVenda> pageVwPedVenda(
            final Integer status, final String filterText, final Pageable pageable) {
        if (StringUtils.isBlank(filterText)) {
            return vwPedVendaRepository.pageVwPedVenda(status, pageable);
        } else {
            return vwPedVendaRepository.pageVwPedVendaByFilter(status, filterText, pageable);
        }
    }

    @Transactional
    public PedVenda incluirPedVenda(final PedVenda pedVenda) {
        return new PedVenda();
    }

    @Transactional
    public PedVenda alterarPedVenda(final PedVenda pedVenda) {
        return new PedVenda();
    }

    /**
     * Verificar se o item pode ser deletado
     */
    public void checkDelete(String numPedido) {
    }

    @Transactional
    public void excluirPedVenda(Integer nrPedido) {
        pedVendaRepository.getPedVenda(nrPedido)
                .orElseThrow(() -> new ApiRequestException("Pedido de venda não encontrado"));

        //  Verifica se o registro pode ser deletado
        boolean podeDeletar = true;

        if (podeDeletar) {
            pedVendaRepository.deleteById(nrPedido);
        } else {
            throw new ApiRequestException("Não é possível excluir o pedido. Existem coisas relacionadas.");
        }
    }
}