package br.com.siscone.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.siscone.dao.DAO;
import br.com.siscone.entity.Lotacao;
import br.com.siscone.entity.SubLotacao;

public class SubLotacaoController {

	private SubLotacao subLotacao;
	private List<SelectItem> lotacoes = new ArrayList<SelectItem>();
	private List<SubLotacao> subLotacaoList;

	public SubLotacao getSubLotacao() {
		return subLotacao;
	}

	public void setSubLotacao(SubLotacao subLotacao) {
		this.subLotacao = subLotacao;
	}

	public List<SelectItem> getLotacoes() {
		return lotacoes;
	}

	public void setLotacoes(List<SelectItem> lotacoes) {
		this.lotacoes = lotacoes;
	}

	public List<SubLotacao> getSubLotacaoList() {
		return subLotacaoList;
	}

	public void setSubLotacaoList(List<SubLotacao> subLotacaoList) {
		this.subLotacaoList = subLotacaoList;
	}

	public void cadastrar() throws Exception {
		try {
			subLotacao = new SubLotacao();
			subLotacao.setLotacao(new Lotacao());
			listarLotacoes();
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("cadastroSubLotacao.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<SelectItem> listarLotacoes() {
		lotacoes = new ArrayList<SelectItem>();
		List<Lotacao> lotacaoList = new ArrayList<Lotacao>();
		lotacaoList = DAO.getInstance().list(Lotacao.class);
		for (Lotacao lotacao : lotacaoList) {
			lotacoes.add(new SelectItem(lotacao.getCodigo(), lotacao
					.getDescricao()));
		}
		return lotacoes;
	}


	public SubLotacao editar() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		subLotacao = (SubLotacao) context.getExternalContext().getRequestMap()
				.get("list");
		listarLotacoes();
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("cadastroSubLotacao.jsp");
		return subLotacao;
	}

	public void salvar() throws Exception {
		DAO.getInstance().saveOrUpdate(subLotacao);
		subLotacao = new SubLotacao();
		subLotacao.setLotacao(new Lotacao());
	}

	@SuppressWarnings("unchecked")
	public List<SubLotacao> listar() throws IOException {
		subLotacaoList = new ArrayList<SubLotacao>();
		subLotacaoList = DAO.getInstance().list(SubLotacao.class);
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("listaSubLotacao.jsp");
		return subLotacaoList;
	}
	
	
	@SuppressWarnings("unchecked")
	public void excluir() throws IOException {
		DAO.getInstance().delete(subLotacao);
		subLotacaoList = DAO.getInstance().list(SubLotacao.class);
	}


}
