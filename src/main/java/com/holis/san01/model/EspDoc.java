package com.holis.san01.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode e @RequiredArgsConstructor
@Entity
@Table(name = "esp_docto")
public class EspDoc {
    @Id
    @Column(nullable = false)
    private String codEspDoc;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private boolean tipoMovto;

    @Column(nullable = false)
    private boolean indManual;

    @Column(nullable = false)
    private boolean usaPrev;

    @Column(nullable = false)
    private boolean usaAntec;

    @Column(nullable = false)
    private boolean emiteDup;

    @Column(nullable = false)
    private boolean fluxoCaixa;

    @Column(nullable = false)
    private boolean indAbatFt;

    @Column(nullable = false)
    private boolean geraComis;

}
