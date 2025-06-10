package com.holis.san01.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sequencia")
public class Sequencia {
    @Id
    @Column(nullable = false)
    private String codSeq;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private Long numeroAtual;
    @Column(nullable = false)
    private Integer passo;
    private Long numeroSeguinte;
}
