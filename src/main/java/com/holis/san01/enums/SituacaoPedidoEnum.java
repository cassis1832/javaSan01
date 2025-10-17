package com.holis.san01.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum SituacaoPedidoEnum {
    ABERTO(0),
    SUSPENSO(1),
    PROCESSANDO(2),
    CONCLUIDO(9),
    CANCELADO(10);

    // ✅ Mapa estático: criado UMA VEZ quando a classe é carregada
    private static final Map<Integer, SituacaoPedidoEnum> BY_CODIGO = new HashMap<>();
    static {
        // Preenche o mapa na inicialização da classe
        for (SituacaoPedidoEnum e : values()) {
            BY_CODIGO.put(e.codigo, e);
        }
    }
    private final int codigo;

    SituacaoPedidoEnum(int codigo) {
        this.codigo = codigo;
    }

    public static SituacaoPedidoEnum fromCodigo(Integer codigo) {
        if (codigo == null) {
            return null;
        }

        SituacaoPedidoEnum situacao = BY_CODIGO.get(codigo);

        if (situacao == null) {
            throw new IllegalArgumentException("Código inválido: " + codigo);
        }

        return situacao;
    }
}

