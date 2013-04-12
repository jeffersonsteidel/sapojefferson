package br.com.siscone.entity;

import java.io.Serializable;
import java.util.Date;

public class Cronograma implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long codigo;
	private Date dataIniCadastrar;
	private Date dataFimCadastrar;
	private Date dataIniPriorizar;
	private Date dataFimPriorizar;
	private Date dataIniAprovar;
	private Date dataFimAprovar;
	private Date dataIniReplanejamento;
	private Date dataFimReplanejamento;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Date getDataIniCadastrar() {
		return dataIniCadastrar;
	}
	public void setDataIniCadastrar(Date dataIniCadastrar) {
		this.dataIniCadastrar = dataIniCadastrar;
	}
	public Date getDataFimCadastrar() {
		return dataFimCadastrar;
	}
	public void setDataFimCadastrar(Date dataFimCadastrar) {
		this.dataFimCadastrar = dataFimCadastrar;
	}
	public Date getDataIniPriorizar() {
		return dataIniPriorizar;
	}
	public void setDataIniPriorizar(Date dataIniPriorizar) {
		this.dataIniPriorizar = dataIniPriorizar;
	}
	public Date getDataFimPriorizar() {
		return dataFimPriorizar;
	}
	public void setDataFimPriorizar(Date dataFimPriorizar) {
		this.dataFimPriorizar = dataFimPriorizar;
	}
	public Date getDataIniAprovar() {
		return dataIniAprovar;
	}
	public void setDataIniAprovar(Date dataIniAprovar) {
		this.dataIniAprovar = dataIniAprovar;
	}
	public Date getDataFimAprovar() {
		return dataFimAprovar;
	}
	public void setDataFimAprovar(Date dataFimAprovar) {
		this.dataFimAprovar = dataFimAprovar;
	}
	public Date getDataIniReplanejamento() {
		return dataIniReplanejamento;
	}
	public void setDataIniReplanejamento(Date dataIniReplanejamento) {
		this.dataIniReplanejamento = dataIniReplanejamento;
	}
	public Date getDataFimReplanejamento() {
		return dataFimReplanejamento;
	}
	public void setDataFimReplanejamento(Date dataFimReplanejamento) {
		this.dataFimReplanejamento = dataFimReplanejamento;
	}

	
}
