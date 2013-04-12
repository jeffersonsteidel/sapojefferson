package br.com.siscone.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import br.com.siscone.dao.DAO;
import br.com.siscone.entity.Unidade;

public class UnidadeController {

	private Unidade unidade;
	private List<Unidade> unidadeList;

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public List<Unidade> getUnidadeList() {
		return unidadeList;
	}

	public void setUnidadeList(List<Unidade> unidadeList) {
		this.unidadeList = unidadeList;
	}

	public void cadastrar() throws Exception {
		try {
			unidade = new Unidade();
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("cadastroUnidade.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Unidade editar() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		unidade = (Unidade) context.getExternalContext().getRequestMap()
				.get("list");
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("cadastroUnidade.jsp");
		return unidade;
	}
	
	 @SuppressWarnings("unchecked")
	public void excluir() throws IOException {
			DAO.getInstance().delete(unidade);
			unidadeList = DAO.getInstance().list(Unidade.class);
 }

	public void salvar() throws Exception {
		DAO.getInstance().saveOrUpdate(unidade);
		unidade = new Unidade();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Unidade> listar() throws IOException{
		unidadeList = new ArrayList<Unidade>();
		unidadeList = DAO.getInstance().list(Unidade.class);
		FacesContext.getCurrentInstance().getExternalContext()
		.redirect("listaUnidade.jsp");
		return unidadeList;
	}

}
