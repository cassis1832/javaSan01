package com.holis.san01.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "ped_venda")
public class PedVenda {

    @Id
    @Column(name = "nr_pedido", nullable = false)
    private Integer nrPedido;

    @Column(name = "cod_filial", nullable = false)
    private int codFilial;

    @Column(name = "cod_entd", nullable = false)
    private int codEntd;

    @Column(length = 60)
    private String descricao;

    @Column(name = "tp_pedido")
    private Integer tpPedido;

    @Column(name = "nr_cotacao")
    private Integer nrCotacao;

    @Column(name = "nr_contrato")
    private Integer nrContrato;

    @Column(name = "nr_pedcli", length = 15)
    private String nrPedcli;

    @Column(name = "perc_desconto", precision = 5, scale = 2)
    private BigDecimal percDesconto;

    @Column(name = "vl_desconto", precision = 15, scale = 2)
    private BigDecimal vlDesconto;

    @Column(name = "vl_frete", precision = 14, scale = 2)
    private BigDecimal vlFrete;

    @Column(name = "vl_seguro", precision = 14, scale = 2)
    private BigDecimal vlSeguro;

    @Column(name = "vl_embal", precision = 14, scale = 2)
    private BigDecimal vlEmbal;

    @Column(name = "vl_tot_ped", precision = 20, scale = 2)
    private BigDecimal vlTotPed;

    @Column(name = "vl_tot_itens", precision = 13, scale = 5)
    private BigDecimal vlTotItens;

    @Column(name = "cod_cond_pag", length = 40)
    private String codCondPag;

    private Integer parcelas;

    @Column(name = "prazo_entrega", length = 30)
    private String prazoEntrega;

    @Column(name = "nat_operacao", length = 6)
    private String natOperacao;

    @Column(length = 25)
    private String contato;

    @Column(length = 40)
    private String telefone;

    @Column(length = 40)
    private String email;

    @Column(length = 2000)
    private String observacao;

    @Column(name = "dt_emissao")
    private LocalDate dtEmissao;

    @Column(name = "dt_entrega")
    private LocalDate dtEntrega;

    @Column(name = "ind_aprov", nullable = false)
    private boolean indAprov;

    @Column(name = "dt_aprovacao")
    private LocalDate dtAprovacao;

    @Column(name = "dt_val_cot")
    private LocalDate dtValCot;

    @Column(name = "prazo_val_cot", length = 30)
    private String prazoValCot;

    @Column(name = "dt_minfat")
    private LocalDate dtMinfat;

    @Column(name = "dt_lim_fat")
    private LocalDate dtLimFat;

    @Column(name = "dt_devolucao")
    private LocalDate dtDevolucao;

    @Column(name = "dt_suspensao")
    private LocalDate dtSuspensao;

    @Column(name = "cod_priori")
    private Integer codPriori;

    @Column(length = 80)
    private String logradouro;

    @Column(length = 15)
    private String numero;

    @Column(length = 45)
    private String complemento;

    @Column(length = 30)
    private String bairro;

    @Column(length = 50)
    private String localidade;

    @Column(length = 4)
    private String estado;

    @Column(length = 12)
    private String cep;

    @Column(length = 20)
    private String pais;

    @Column(length = 19)
    private String cgc;

    @Column(name = "ins_estadual", length = 19)
    private String insEstadual;

    @Column(name = "perc_desco2", precision = 5, scale = 2)
    private BigDecimal percDesco2;

    @Column(name = "cond_redespa", length = 2000)
    private String condRedespa;

    @Column(name = "cidade_cif", length = 50)
    private String cidadeCif;

    @Column(name = "cod_portador")
    private Integer codPortador;

    private Integer modalidade;

    @Column(name = "cod_mensagem")
    private Integer codMensagem;

    @Column(name = "cond_espec", length = 2000)
    private String condEspec;

    @Column(name = "cod_des_merc")
    private Integer codDesMerc;

    @Column(name = "nome_transp", length = 20)
    private String nomeTransp;

    @Column(name = "tp_preco", nullable = false)
    private Integer tpPreco;

    @Column(name = "ind_fat_par", nullable = false)
    private boolean indFatPar;

    @Column(name = "vl_liq_ped", precision = 20, scale = 2)
    private BigDecimal vlLiqPed;

    @Column(name = "vl_liq_abe", precision = 20, scale = 2)
    private BigDecimal vlLiqAbe;

    @Column(name = "ind_antecip", nullable = false)
    private boolean indAntecip;

    @Column(precision = 7, scale = 1)
    private BigDecimal distancia;

    @Column(name = "vl_mer_abe", precision = 21, scale = 2)
    private BigDecimal vlMerAbe;

    @Column(name = "desc_suspend", length = 2000)
    private String descSuspend;

    @Column(name = "desc_bloq_cr", length = 76)
    private String descBloqCr;

    @Column(name = "desc_forc_cr", length = 76)
    private String descForcCr;

    @Column(name = "nome_tr_red", length = 12)
    private String nomeTrRed;

    @Column(name = "tip_cob_desp")
    private Integer tipCobDesp;

    @Column(name = "cod_sit_pre")
    private Integer codSitPre;

    @Column(name = "per_des_icms", precision = 6, scale = 3)
    private BigDecimal perDesIcms;

    @Column(name = "vl_cred_lib", precision = 12, scale = 2)
    private BigDecimal vlCredLib;

    @Column(name = "inc_desc_txt", nullable = false)
    private boolean incDescTxt;

    @Column(name = "dt_base_ft")
    private LocalDate dtBaseFt;

    @Column(name = "ind_ent_completa", nullable = false)
    private boolean indEntCompleta;

    @Column(nullable = false)
    private boolean completo;

    @Column(name = "vl_desconto_total", precision = 19, scale = 2)
    private BigDecimal vlDescontoTotal;

    @Column(name = "desc_valor_ped", precision = 13, scale = 5)
    private BigDecimal descValorPed;

    @Column(name = "tipo_fin_id")
    private Integer tipoFinId;

    @Column(name = "cond_pag", length = 45)
    private String condPag;

    @Column(name = "dt_situacao")
    private LocalDate dtSituacao;

    @Column(length = 20)
    private String situacao;

    @Column(name = "dt_criacao")
    private LocalDate dtCriacao;

    @Column(nullable = false)
    private int status;
}