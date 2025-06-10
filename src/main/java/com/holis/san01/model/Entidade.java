package com.holis.san01.model;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.Date;

@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode e @RequiredArgsConstructor
@Entity
@Table(name = "entidade")
public class Entidade {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Pattern(regexp = "[SN]", message = "O campo 'archive' deve ser 'S' ou 'N'.")
    private String archive;

    private String situacao;
    private Date dtCriacao;

    @Column(nullable = false)
    private Long codEntd;
    @Column(nullable = false)
    private String nome;
    private String razaoSocial;
    private String indCliente;
    private String indFornec;
    private String indTransp;
    private String tpPessoa;
    private String cgc;
    private String rg;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String localidade;
    private String estado;
    private String pais;
    private String cep;
    private String contato;
    private String email;
    private String telefone;
    private String caixaPostal;
    private String inscrEstadual;
    private String inscrMunicipal;
    private Integer indPagto;
    private Integer vencFeriado;
    private Integer vencDomingo;
    private Integer venSabado;
    private String homePage;
    private Boolean logPossuiNfEletro;
    private Boolean logNfEletro;
    private String codCondPag;
    private String natOperSai;
    private String natOperEnt;
    private String cgcCob;
    private String cepCob;
    private String estadoCob;
    private String cidadeCob;
    private String bairroCob;
    private String enderecoCob;
    private String paisCob;
    private String cxPostCob;
    private String insEstCob;
    private Integer codMensagem;
    private String emailNfe;
    private Integer tipoFinIdSai;
    private Integer tipoFinIdEnt;
    private String setor;
    private String segmento;
    private String ramo;
    private String obsEntrega;
    private String observacoes;
}
