package com.holis.san01.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class EntidadeDTO {
    @NotNull(message = "Código do cliente/fornecedor é obrigatório")
    private Long codEntd;

    @NotBlank(message = "Nome abreviado/fantasia é obrigatório")
    @Size(min = 3, message = "Nome deve ter no mínimo 3 caracteres")
    private String nome;
    @NotBlank(message = "Razão social é obrigatório")
    private String razaoSocial;
    private String indCliente;
    private String indFornec;
    private String indTransp;
    @NotNull(message = "Tipo de pessoa é obrigatório (física/jurídica/estrangeiro)")
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
    private String situacao;
    private Date dtCriacao;
    @NotBlank
    @Size(min = 1, max = 1)
    @Pattern(regexp = "[SN]", message = "O campo 'archive' deve ser 'S' ou 'N'.")
    private String archive;
}
