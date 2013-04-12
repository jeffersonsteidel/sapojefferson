package br.com.siscone.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.siscone.dao.DAO;
import br.com.siscone.entity.Lotacao;
import br.com.siscone.util.MoedaUtil;

public class LotacaoController {

	private Lotacao lotacao;
	List<SelectItem> lotacoes = new ArrayList<SelectItem>();
	List<Lotacao> lotacaoList = new ArrayList<Lotacao>();

	public Lotacao getLotacao() {
		return lotacao;
	}

	public void setLotacao(Lotacao lotacao) {
		this.lotacao = lotacao;
	}

	public List<SelectItem> getLotacoes() {
		return lotacoes;
	}

	public void setLotacoes(List<SelectItem> lotacoes) {
		this.lotacoes = lotacoes;
	}

	public List<Lotacao> getLotacaoList() {
		return lotacaoList;
	}

	public void setLotacaoList(List<Lotacao> lotacaoList) {
		this.lotacaoList = lotacaoList;
	}

	public void cadastrar() throws Exception {
		try {
			listarLotacoes();
			lotacao = new Lotacao();
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("cadastroOrcamento.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cadastrarLotacao() throws Exception {
		try {
			lotacao = new Lotacao();
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("cadastroLotacao.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Lotacao buscarLotacao() {
		lotacao = (Lotacao) DAO.getInstance().refresh(lotacao);
		if (lotacao.getOrcamentoCapital() != null) {
			lotacao.setOrcamentoCapitalString(MoedaUtil.mascaraDinheiro(
					lotacao.getOrcamentoCapital(), MoedaUtil.DINHEIRO_REAL));
		}
		if (lotacao.getOrcamentoCusteio() != null) {
			lotacao.setOrcamentoCusteioString(MoedaUtil.mascaraDinheiro(
					lotacao.getOrcamentoCusteio(), MoedaUtil.DINHEIRO_REAL));
		}
		return lotacao;
	}

	@SuppressWarnings("unchecked")
	public List<SelectItem> listarLotacoes() {
		lotacoes = new ArrayList<SelectItem>();
		List<Lotacao> lotacaoList = new ArrayList<Lotacao>();
		lotacaoList = DAO.getInstance().list(Lotacao.class, "descricao");
		for (Lotacao lotacao : lotacaoList) {
			lotacoes.add(new SelectItem(lotacao.getCodigo(), lotacao
					.getDescricao()));
		}
		return lotacoes;
	}

	@SuppressWarnings("unchecked")
	public List<Lotacao> listar() throws IOException {
		lotacaoList = new ArrayList<Lotacao>();
		lotacaoList = DAO.getInstance().list(Lotacao.class);
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("listaOrcamento.jsp");
		return lotacaoList;
	}

	@SuppressWarnings("unchecked")
	public List<Lotacao> listarLotacao() throws IOException {
		lotacaoList = new ArrayList<Lotacao>();
		lotacaoList = DAO.getInstance().list(Lotacao.class);
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("listaLotacao.jsp");
		return lotacaoList;
	}

	public Lotacao editar() throws IOException {
		if (lotacao.getOrcamentoCapital() != null) {
			lotacao.setOrcamentoCapitalString(MoedaUtil.mascaraDinheiro(
					lotacao.getOrcamentoCapital(), MoedaUtil.DINHEIRO_REAL));
		}
		if (lotacao.getOrcamentoCusteio() != null) {
			lotacao.setOrcamentoCusteioString(MoedaUtil.mascaraDinheiro(
					lotacao.getOrcamentoCusteio(), MoedaUtil.DINHEIRO_REAL));
		}
		FacesContext context = FacesContext.getCurrentInstance();
		lotacao = (Lotacao) context.getExternalContext().getRequestMap()
				.get("list");
		listarLotacoes();
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("cadastroOrcamento.jsp");
		return lotacao;
	}

	public Lotacao editarLotacao() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		lotacao = (Lotacao) context.getExternalContext().getRequestMap()
				.get("list");
		listarLotacoes();
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("cadastroLotacao.jsp");
		return lotacao;
	}

	public void salvar() throws Exception {
		if (lotacao.getOrcamentoCapitalString() != null) {
			lotacao.setOrcamentoCapital(new BigDecimal(lotacao
					.getOrcamentoCapitalString().replace(".", "")
					.replace(",", ".")));
		}
		if (lotacao.getOrcamentoCusteioString() != null) {
			lotacao.setOrcamentoCusteio(new BigDecimal(lotacao
					.getOrcamentoCusteioString().replace(".", "")
					.replace(",", ".")));
		}
		DAO.getInstance().saveOrUpdate(lotacao);
		lotacao = new Lotacao();
	}

	@SuppressWarnings("unchecked")
	public void excluir() throws IOException {
		DAO.getInstance().delete(lotacao);
		lotacaoList = DAO.getInstance().list(Lotacao.class);
	}

}
