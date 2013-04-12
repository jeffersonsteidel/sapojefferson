package br.com.siscone.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Lotacao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codigo;
	private String descricao;
	private BigDecimal orcamentoCapital;
	private BigDecimal orcamentoCusteio;
	private String orcamentoCapitalString;
	private String orcamentoCusteioString;

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

	public BigDecimal getOrcamentoCapital() {
		return orcamentoCapital;
	}

	public void setOrcamentoCapital(BigDecimal orcamentoCapital) {
		this.orcamentoCapital = orcamentoCapital;
	}

	public BigDecimal getOrcamentoCusteio() {
		return orcamentoCusteio;
	}

	public void setOrcamentoCusteio(BigDecimal orcamentoCusteio) {
		this.orcamentoCusteio = orcamentoCusteio;
	}

	public String getOrcamentoCapitalString() {
		return orcamentoCapitalString;
	}

	public void setOrcamentoCapitalString(String orcamentoCapitalString) {
		this.orcamentoCapitalString = orcamentoCapitalString;
	}

	public String getOrcamentoCusteioString() {
		return orcamentoCusteioString;
	}

	public void setOrcamentoCusteioString(String orcamentoCusteioString) {
		this.orcamentoCusteioString = orcamentoCusteioString;
	}

}
