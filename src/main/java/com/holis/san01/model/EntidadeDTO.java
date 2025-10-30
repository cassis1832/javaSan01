package com.holis.san01.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode e @RequiredArgsConstructor
public class EntidadeDTO {

    private int codEntd;
    @NotNull(message = "Nome da entidade é obrigatório")
    @Size(max = 20, message = "O nome deve ter até 20 caracteres")
    private String nome;
    @Size(max = 80, message = "A razão social deve ter até 80 caracteres")
    @NotNull(message = "Razão Social é obrigatório")
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
    private Boolean libCompra;
    private Boolean libVenda;
    private Boolean libPagamento;
    private LocalDate dtCriacao;
    private int status;
}
