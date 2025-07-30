package com.holis.san01.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode e @RequiredArgsConstructor
@Entity
@Table(name = "unimed")
public class Unimed {
    @Id
    @Column(nullable = false)
    private String codUnimed;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String sequencia;

    @Column(nullable = false)
    private String fraciona;

    @Column(nullable = false)
    @Pattern(regexp = "[SN]", message = "O campo 'archive' deve ser 'S' ou 'N'.")
    private String archive;
}
