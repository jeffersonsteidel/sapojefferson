package br.com.siscone.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Material implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codigo;
	private String descricao;
	private String justificativa;
	private Long quantidade;
	private Unidade unidade;
	private BigDecimal vlrUnitario;
	private BigDecimal vlrTotal;
	private int prioridade;
	private SubElemento subElemento;
	//private Lotacao lotacao;
	private String tipo;
	private String execucao;
	private String vlrUnitarioString;
	private SubLotacao subLotacao;
	private String projetoVinculado;
	private String responsavelProjeto;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public BigDecimal getVlrUnitario() {
		return vlrUnitario;
	}

	public void setVlrUnitario(BigDecimal vlrUnitario) {
		this.vlrUnitario = vlrUnitario;
	}

	public BigDecimal getVlrTotal() {
		return vlrTotal;
	}

	public void setVlrTotal(BigDecimal vlrTotal) {
		this.vlrTotal = vlrTotal;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	public SubElemento getSubElemento() {
		return subElemento;
	}

	public void setSubElemento(SubElemento subElemento) {
		this.subElemento = subElemento;
	}

//	public Lotacao getLotacao() {
//		return lotacao;
//	}

//	public void setLotacao(Lotacao lotacao) {
//		this.lotacao = lotacao;
//	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getExecucao() {
		return execucao;
	}

	public void setExecucao(String execucao) {
		this.execucao = execucao;
	}

	public String getVlrUnitarioString() {
		return vlrUnitarioString;
	}

	public void setVlrUnitarioString(String vlrUnitarioString) {
		this.vlrUnitarioString = vlrUnitarioString;
	}

	public SubLotacao getSubLotacao() {
		return subLotacao;
	}

	public void setSubLotacao(SubLotacao subLotacao) {
		this.subLotacao = subLotacao;
	}

	public String getProjetoVinculado() {
		return projetoVinculado;
	}

	public void setProjetoVinculado(String projetoVinculado) {
		this.projetoVinculado = projetoVinculado;
	}

	public String getResponsavelProjeto() {
		return responsavelProjeto;
	}

	public void setResponsavelProjeto(String responsavelProjeto) {
		this.responsavelProjeto = responsavelProjeto;
	}
}
