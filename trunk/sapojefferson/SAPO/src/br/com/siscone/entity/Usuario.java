package br.com.siscone.entity;

import java.io.Serializable;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private String login;
	private String senha;
	private Boolean indAdministrador;
	private Boolean indCadastrador;
	private Boolean indCadastradorSubLotacao;
	private Boolean indPriorizador;
	private Boolean indAprovador;
	private SubLotacao subLotacao;
	private Boolean indReplanejador;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public SubLotacao getSubLotacao() {
		return subLotacao;
	}

	public void setSubLotacao(SubLotacao subLotacao) {
		this.subLotacao = subLotacao;
	}

	public Boolean getIndAdministrador() {
		return indAdministrador;
	}

	public void setIndAdministrador(Boolean indAdministrador) {
		this.indAdministrador = indAdministrador;
	}

	public Boolean getIndCadastrador() {
		return indCadastrador;
	}

	public void setIndCadastrador(Boolean indCadastrador) {
		this.indCadastrador = indCadastrador;
	}

	public Boolean getIndPriorizador() {
		return indPriorizador;
	}

	public void setIndPriorizador(Boolean indPriorizador) {
		this.indPriorizador = indPriorizador;
	}


	public Boolean getIndAprovador() {
		return indAprovador;
	}

	public void setIndAprovador(Boolean indAprovador) {
		this.indAprovador = indAprovador;
	}

	public Boolean getIndCadastradorSubLotacao() {
		return indCadastradorSubLotacao;
	}

	public void setIndCadastradorSubLotacao(Boolean indCadastradorSubLotacao) {
		this.indCadastradorSubLotacao = indCadastradorSubLotacao;
	}

	public Boolean getIndReplanejador() {
		return indReplanejador;
	}

	public void setIndReplanejador(Boolean indReplanejador) {
		this.indReplanejador = indReplanejador;
	}
}
