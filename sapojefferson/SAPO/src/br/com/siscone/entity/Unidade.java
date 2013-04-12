package br.com.siscone.entity;

import java.io.Serializable;

public class Unidade implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codigo;
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

}
