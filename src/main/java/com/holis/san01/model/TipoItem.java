package com.holis.san01.model;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.Date;

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
}
