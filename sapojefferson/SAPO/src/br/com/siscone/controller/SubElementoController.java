package br.com.siscone.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.siscone.dao.DAO;
import br.com.siscone.dao.ElementoDAO;
import br.com.siscone.entity.Categoria;
import br.com.siscone.entity.Elemento;
import br.com.siscone.entity.SubElemento;

public class SubElementoController {

	private SubElemento subElemento;
	private List<SelectItem> categorias = new ArrayList<SelectItem>();
	private List<SelectItem> elementos = new ArrayList<SelectItem>();
	private List<SubElemento> subElementoList;

	public SubElemento getSubElemento() {
		return subElemento;
	}

	public void setSubElemento(SubElemento subElemento) {
		this.subElemento = subElemento;
	}

	public List<SelectItem> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<SelectItem> categorias) {
		this.categorias = categorias;
	}

	public List<SelectItem> getElementos() {
		return elementos;
	}

	public void setElementos(List<SelectItem> elementos) {
		this.elementos = elementos;
	}

	public List<SubElemento> getSubElementoList() {
		return subElementoList;
	}

	public void setSubElementoList(List<SubElemento> subElementoList) {
		this.subElementoList = subElementoList;
	}

	public void cadastrar() throws Exception {
		try {
			subElemento = new SubElemento();
			subElemento.setElemento(new Elemento());
			subElemento.getElemento().setCategoria(new Categoria());
			listarCategorias();
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("cadastroSubElemento.jsp");
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

	public List<SelectItem> listarElementos() {
		elementos = new ArrayList<SelectItem>();
		List<Elemento> elementoList = new ArrayList<Elemento>();
		elementoList = ElementoDAO.getInstance().listByCategoria(
				subElemento.getElemento().getCategoria());
		for (Elemento elemento : elementoList) {
			elementos.add(new SelectItem(elemento.getCodigo(), elemento
					.getDescricao()));
		}
		return elementos;
	}

	public SubElemento editar() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		subElemento = (SubElemento) context.getExternalContext().getRequestMap()
				.get("list");
		listarCategorias();
		listarElementos();
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("cadastroSubElemento.jsp");
		return subElemento;
	}

	public void salvar() throws Exception {
		DAO.getInstance().saveOrUpdate(subElemento);
		subElemento = new SubElemento();
		subElemento.setElemento(new Elemento());
		subElemento.getElemento().setCategoria(new Categoria());
	}

	@SuppressWarnings("unchecked")
	public List<SubElemento> listar() throws IOException {
		subElementoList = new ArrayList<SubElemento>();
		subElementoList = DAO.getInstance().list(SubElemento.class);
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("listaSubElemento.jsp");
		return subElementoList;
	}
	
	
	@SuppressWarnings("unchecked")
	public void excluir() throws IOException {
		DAO.getInstance().delete(subElemento);
		subElementoList = DAO.getInstance().list(SubElemento.class);
	}


}
