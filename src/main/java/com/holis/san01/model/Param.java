package com.holis.san01.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode e @RequiredArgsConstructor
@Entity
@Table(name = "parametro")
public class Param {
    @Id
    @Column(nullable = false)
    private String codParam;

    @Column(nullable = false)
    private String descricao;

    private String cpoTexto;

    private Integer cpoInteiro;

    private BigDecimal cpoDecimal;

    private LocalDate data;
}
