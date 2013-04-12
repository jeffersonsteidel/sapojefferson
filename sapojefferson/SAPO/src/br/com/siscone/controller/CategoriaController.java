package br.com.siscone.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import br.com.siscone.dao.DAO;
import br.com.siscone.entity.Categoria;

public class CategoriaController {

	private Categoria categoria;
	private List<Categoria> categoriaList;

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public List<Categoria> getCategoriaList() {
		return categoriaList;
	}

	public void setCategoriaList(List<Categoria> categoriaList) {
		this.categoriaList = categoriaList;
	}

	public void cadastrar() throws Exception {
		try {
			categoria = new Categoria();
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("cadastroCategoria.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Categoria editar() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		categoria = (Categoria) context.getExternalContext().getRequestMap()
				.get("list");
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("cadastroCategoria.jsp");
		return categoria;
	}

	public void salvar() throws Exception {
		DAO.getInstance().saveOrUpdate(categoria);
		categoria = new Categoria();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Categoria> listar() throws IOException{
		categoriaList = new ArrayList<Categoria>();
		categoriaList = DAO.getInstance().list(Categoria.class);
		FacesContext.getCurrentInstance().getExternalContext()
		.redirect("listaCategoria.jsp");
		return categoriaList;
	}

}
