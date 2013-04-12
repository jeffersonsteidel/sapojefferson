package br.com.siscone.entity;

import java.io.Serializable;
import java.util.Date;

public class Mensagem implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codigo;
	private Date data;
	private String titulo; 
	private String descricao;

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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
