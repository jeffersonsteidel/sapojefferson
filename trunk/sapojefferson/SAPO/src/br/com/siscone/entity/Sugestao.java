package br.com.siscone.entity;

import java.io.Serializable;
import java.util.Date;

public class Sugestao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codigo;
	private Usuario usuario;
	private String telefone;
	private String email;
	private String sugestao;
	private Boolean lida;
	private Boolean arquivada;
	private Date data;
	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSugestao() {
		return sugestao;
	}
	public void setSugestao(String sugestao) {
		this.sugestao = sugestao;
	}
	public Boolean getLida() {
		return lida;
	}
	public void setLida(Boolean lida) {
		this.lida = lida;
	}
	public Boolean getArquivada() {
		return arquivada;
	}
	public void setArquivada(Boolean arquivada) {
		this.arquivada = arquivada;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}

	
}
