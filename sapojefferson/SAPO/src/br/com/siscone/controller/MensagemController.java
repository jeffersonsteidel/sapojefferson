package br.com.siscone.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;

import br.com.siscone.dao.DAO;
import br.com.siscone.entity.Mensagem;

public class MensagemController {

	private Mensagem mensagem;
	private List<Mensagem> mensagemList;


	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

	public List<Mensagem> getMensagemList() {
		return mensagemList;
	}

	public void setMensagemList(List<Mensagem> mensagemList) {
		this.mensagemList = mensagemList;
	}

	public void cadastrar() throws Exception {
		try {
			mensagem = new Mensagem();
			mensagem.setData(new Date());
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("cadastroMensagem.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Mensagem editar() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		mensagem = (Mensagem) context.getExternalContext().getRequestMap()
				.get("list");
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("cadastroMensagem.jsp");
		return mensagem;
	}
	
	public Mensagem visualizar() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		mensagem = (Mensagem) context.getExternalContext().getRequestMap()
				.get("list");
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("visualizarMensagem.jsp");
		return mensagem;
	}

	public void salvar() throws Exception {
		DAO.getInstance().saveOrUpdate(mensagem);
		mensagem = new Mensagem();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Mensagem> listar() throws IOException{
		mensagemList = new ArrayList<Mensagem>();
		mensagemList = DAO.getInstance().list(Mensagem.class);
		FacesContext.getCurrentInstance().getExternalContext()
		.redirect("listaMensagem.jsp");
		return mensagemList;
	}
	
	
	@SuppressWarnings("unchecked")
	public void excluir() throws IOException {
		DAO.getInstance().delete(mensagem);
		mensagemList = DAO.getInstance().list(Mensagem.class);
	}

}
