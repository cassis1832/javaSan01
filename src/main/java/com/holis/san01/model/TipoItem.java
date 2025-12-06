package com.holis.san01.model;

import jakarta.persistence.*;
import lombok.Data;

@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode e @RequiredArgsConstructor
@Entity
@Table(name = "tipo_item")
public class TipoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer empresa;

    @Column(nullable = false)
    private String codTipoItem;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String tpProd;

    @Column(nullable = false)
    private int status;
}
