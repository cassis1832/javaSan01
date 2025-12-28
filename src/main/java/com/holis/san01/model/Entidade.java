package com.holis.san01.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode e @RequiredArgsConstructor
@Entity
@Table(name = "entidade")
public class Entidade {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codEntd;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String razaoSocial;
    private Boolean indCliente;
    private Boolean indFornec;
    private Boolean indTransp;
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
    @Column(length = 100)
    private String obsEndereco;
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
    private String situacao;
    @Column(nullable = false)
    private Boolean libCompra;
    @Column(nullable = false)
    private Boolean libVenda;
    @Column(nullable = false)
    private Boolean libPagamento;
    private Instant dtCriacao;
    @Column(nullable = false)
    private Integer status;
}
