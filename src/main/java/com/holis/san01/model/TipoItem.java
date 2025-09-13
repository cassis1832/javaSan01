package com.holis.san01.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode e @RequiredArgsConstructor
@Entity
@Table(name = "tipo_item")
public class TipoItem {

    @Id
    @Column(nullable = false)
    private String codTipoItem;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String tpProd;

    @Column(nullable = false)
    private int status;
}
