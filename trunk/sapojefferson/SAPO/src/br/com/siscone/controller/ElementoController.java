package br.com.siscone.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.siscone.dao.DAO;
import br.com.siscone.entity.Categoria;
import br.com.siscone.entity.Elemento;

public class ElementoController {

	private Elemento elemento;
	private List<SelectItem> categorias = new ArrayList<SelectItem>();
	private List<Elemento> elementoList;

	public Elemento getElemento() {
		return elemento;
	}

	public void setElemento(Elemento elemento) {
		this.elemento = elemento;
	}

	public List<Elemento> getElementoList() {
		return elementoList;
	}

	public void setElementoList(List<Elemento> elementoList) {
		this.elementoList = elementoList;
	}

	public List<SelectItem> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<SelectItem> categorias) {
		this.categorias = categorias;
	}

	public void cadastrar() throws Exception {
		try {
			elemento = new Elemento();
			elemento.setCategoria(new Categoria());
			listarCategorias();
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("cadastroElemento.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<SelectItem> listarCategorias() {
		categorias = new ArrayList<SelectItem>();
		List<Categoria> categoriaList = new ArrayList<Categoria>();
		categoriaList = DAO.getInstance().list(Categoria.class);
		for (Categoria categoria : categoriaList) {
			categorias.add(new SelectItem(categoria.getCodigo(), categoria
					.getDescricao()));
		}
		return categorias;
	}

	public Elemento editar() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		elemento = (Elemento) context.getExternalContext().getRequestMap()
				.get("list");
		listarCategorias();
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("cadastroElemento.jsp");
		return elemento;
	}

	public void salvar() throws Exception {
		DAO.getInstance().saveOrUpdate(elemento);
		elemento = new Elemento();
		elemento.setCategoria(new Categoria());
	}

	@SuppressWarnings("unchecked")
	public List<Elemento> listar() throws IOException {
		elementoList = new ArrayList<Elemento>();
		elementoList = DAO.getInstance().list(Elemento.class);
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("listaElemento.jsp");
		return elementoList;
	}

	@SuppressWarnings("unchecked")
	public void excluir() throws IOException {
		DAO.getInstance().delete(elemento);
		elementoList = DAO.getInstance().list(Elemento.class);
	}

}
