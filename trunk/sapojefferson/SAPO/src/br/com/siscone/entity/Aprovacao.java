package br.com.siscone.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Aprovacao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codigo;
	private SubLotacao subLotacao;
	private Categoria categoria;
	private BigDecimal total;
	private BigDecimal orcamento;
	private BigDecimal acumulado;
	private BigDecimal diferenca;
	private Boolean indAprovado;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public SubLotacao getSubLotacao() {
		return subLotacao;
	}

	public void setSubLotacao(SubLotacao subLotacao) {
		this.subLotacao = subLotacao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getOrcamento() {
		return orcamento;
	}

	public BigDecimal getAcumulado() {
		return acumulado;
	}

	public void setAcumulado(BigDecimal acumulado) {
		this.acumulado = acumulado;
	}

	public BigDecimal getDiferenca() {
		return diferenca;
	}

	public void setDiferenca(BigDecimal diferenca) {
		this.diferenca = diferenca;
	}

	public void setOrcamento(BigDecimal orcamento) {
		this.orcamento = orcamento;
	}

	public Boolean getIndAprovado() {
		return indAprovado;
	}

	public void setIndAprovado(Boolean indAprovado) {
		this.indAprovado = indAprovado;
	}

}
