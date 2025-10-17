package com.holis.san01.model;

import jakarta.persistence.*;
import lombok.Data;

@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode e @RequiredArgsConstructor
@Entity
@Table(name = "situacao")
public class Situacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String objeto;

    @Column(nullable = false)
    private Integer sequencia;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Integer situacao;
}
