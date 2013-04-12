package br.com.siscone.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.siscone.dao.CronogramaDAO;
import br.com.siscone.dao.DAO;
import br.com.siscone.dao.ReplanejamentoDAO;
import br.com.siscone.entity.Categoria;
import br.com.siscone.entity.Cronograma;
import br.com.siscone.entity.Elemento;
import br.com.siscone.entity.Lotacao;
import br.com.siscone.entity.Material;
import br.com.siscone.entity.Replanejamento;
import br.com.siscone.entity.SubElemento;
import br.com.siscone.entity.SubLotacao;
import br.com.siscone.entity.Unidade;
import br.com.siscone.entity.Usuario;
import br.com.siscone.jsfUtil.DataUtil;
import br.com.siscone.util.MoedaUtil;

public class ReplanejamentoController {

	private Replanejamento replanejamento;
	private List<Replanejamento> replanejamentoList;
	private List<SelectItem> lotacoes = new ArrayList<SelectItem>();
	private List<SelectItem> categorias = new ArrayList<SelectItem>();
	private BigDecimal totalMaterial = new BigDecimal(0);
	private BigDecimal totalNovoSaldo = new BigDecimal(0);

	public List<SelectItem> getLotacoes() {
		return lotacoes;
	}

	public void setLotacoes(List<SelectItem> lotacoes) {
		this.lotacoes = lotacoes;
	}

	public List<SelectItem> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<SelectItem> categorias) {
		this.categorias = categorias;
	}

	public Replanejamento getReplanejamento() {
		return replanejamento;
	}

	public void setReplanejamento(Replanejamento replanejamento) {
		this.replanejamento = replanejamento;
	}

	public List<Replanejamento> getReplanejamentoList() {
		return replanejamentoList;
	}

	public void setReplanejamentoList(List<Replanejamento> replanejamentoList) {
		this.replanejamentoList = replanejamentoList;
	}

	public BigDecimal getTotalMaterial() {
		return totalMaterial;
	}

	public void setTotalMaterial(BigDecimal totalMaterial) {
		this.totalMaterial = totalMaterial;
	}

	public BigDecimal getTotalNovoSaldo() {
		return totalNovoSaldo;
	}

	public void setTotalNovoSaldo(BigDecimal totalNovoSaldo) {
		this.totalNovoSaldo = totalNovoSaldo;
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
	public List<SelectItem> listarCategorias() {
		categorias = new ArrayList<SelectItem>();
		List<Categoria> categoriaList = new ArrayList<Categoria>();
		categoriaList = DAO.getInstance().list(Categoria.class, "descricao");
		for (Categoria categoria : categoriaList) {
			categorias.add(new SelectItem(categoria.getCodigo(), categoria
					.getDescricao()));
		}
		return categorias;
	}

	public void cadastrarExcutado() throws Exception {
		try {
			totalMaterial = new BigDecimal(0);
			replanejamentoList = new ArrayList<Replanejamento>();
			replanejamento = new Replanejamento();
			replanejamento.setMaterial(new Material());
			replanejamento.getMaterial().setSubElemento(new SubElemento());
			replanejamento.getMaterial().getSubElemento()
					.setElemento(new Elemento());
			replanejamento.getMaterial().setUnidade(new Unidade());
			replanejamento.getMaterial().setSubLotacao(new SubLotacao());
			replanejamento.getMaterial().getSubLotacao()
					.setLotacao(new Lotacao());
			replanejamento.getMaterial().getSubElemento().getElemento()
					.setCategoria(new Categoria());
			listarCategorias();
			listarLotacoes();
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("listaExecutado.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Replanejamento editar() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		replanejamento = (Replanejamento) context.getExternalContext()
				.getRequestMap().get("list");
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("cadastroReplanejamento.jsp");
		return replanejamento;
	}

	public void salvar() throws Exception {
		for (Replanejamento item : replanejamentoList) {
			if (item.getVlrExecutadoString() == null
					|| ("").equals(item.getVlrExecutadoString())) {
				item.setVlrExecutado(new BigDecimal(0.00));
			} else {
				item.setVlrExecutado(new BigDecimal(item
						.getVlrExecutadoString().replace(".", "")
						.replace(",", ".")));
				item.setVlrReplanejado(item.getMaterial().getVlrTotal()
						.subtract(item.getVlrExecutado()));
				item.setVlrReplanejado(item.getMaterial().getVlrTotal()
						.subtract(item.getVlrExecutado()));
			}
			if (item.getVlrCanceladoString() != null
					&& item.getVlrCanceladoString() != "") {
				item.setVlrCancelado(new BigDecimal(item
						.getVlrCanceladoString().replace(".", "")
						.replace(",", ".")));
			}
			if (item.getVlrSuplamentarString() != null
					&& item.getVlrSuplamentarString() != "") {
				item.setVlrSuplementar(new BigDecimal(item
						.getVlrSuplamentarString().replace(".", "")
						.replace(",", ".")));
			}
			DAO.getInstance().saveOrUpdate(item);
		}
	}

	public void salvarReplanejamento() throws Exception {
		if (totalMaterial.doubleValue() >= totalNovoSaldo.doubleValue()) {
			for (Replanejamento item : replanejamentoList) {
				if (item.getVlrCanceladoString() != null
						&& item.getVlrCanceladoString() != "") {
					item.setVlrCancelado(new BigDecimal(item
							.getVlrCanceladoString().replace(".", "")
							.replace(",", ".")));
				}
				if (item.getVlrSuplamentarString() != null
						&& item.getVlrSuplamentarString() != "") {
					item.setVlrSuplementar(new BigDecimal(item
							.getVlrSuplamentarString().replace(".", "")
							.replace(",", ".")));
				}
				DAO.getInstance().saveOrUpdate(item);
			}
		} else {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"O campo Valor Total Material deve ser maior ou igual ao Total Novo Saldo!",
					"O campo Valor Total Material deve ser maior ou igual ao Total Novo Saldo!");
			FacesContext.getCurrentInstance().addMessage("", message);
		}

	}

	@SuppressWarnings("unchecked")
	public List<Replanejamento> listar() throws IOException {
		replanejamentoList = new ArrayList<Replanejamento>();
		replanejamentoList = DAO.getInstance().list(Replanejamento.class);
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("listaReplanejamento.jsp");
		return replanejamentoList;
	}

	@SuppressWarnings("unchecked")
	public void carregarTabela() {
		List<Material> materialList = new ArrayList<Material>();
		List<Replanejamento> replanejamentoList = new ArrayList<Replanejamento>();
		replanejamentoList = DAO.getInstance().list(Replanejamento.class);
		if (!replanejamentoList.isEmpty()) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Tabela já carregada!",
					"Tabela já carregada!");
			FacesContext.getCurrentInstance().addMessage("", message);

		} else {
			materialList = DAO.getInstance().list(Material.class);
			for (Material m : materialList) {
				Replanejamento replanejamento = new Replanejamento();
				replanejamento.setMaterial(new Material());
				replanejamento.setMaterial(m);
				DAO.getInstance().save(replanejamento);
			}
		}
	}

	public void cadastrarReplanejamento() throws Exception {
		try {
			Cronograma cronograma = new Cronograma();
			cronograma = CronogramaDAO.getInstance().buscar();
			if (cronograma != null) {
				if (cronograma.getDataIniReplanejamento().compareTo(new Date()) == 1
						|| cronograma.getDataFimReplanejamento().compareTo(
								new Date()) == -1) {
					FacesMessage message = new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"O periodo de replanejamento é de "
									+ DataUtil.format(cronograma
											.getDataIniReplanejamento(),
											"dd/MM/yyyy")
									+ " a "
									+ DataUtil.format(cronograma
											.getDataFimReplanejamento(),
											"dd/MM/yyyy") + "!",

							"O periodo de replanejamento era de "
									+ DataUtil.format(cronograma
											.getDataIniReplanejamento(),
											"dd/MM/yyyy")
									+ " a "
									+ DataUtil.format(cronograma
											.getDataFimReplanejamento(),
											"dd/MM/yyyy") + "!");
					FacesContext.getCurrentInstance().addMessage("", message);
				} else {
					replanejamentoList = new ArrayList<Replanejamento>();
					replanejamento = new Replanejamento();
					replanejamento.setMaterial(new Material());
					replanejamento.getMaterial().setSubElemento(
							new SubElemento());
					replanejamento.getMaterial().getSubElemento()
							.setElemento(new Elemento());
					replanejamento.getMaterial().setUnidade(new Unidade());
					replanejamento.getMaterial()
							.setSubLotacao(new SubLotacao());
					replanejamento.getMaterial().getSubLotacao()
							.setLotacao(new Lotacao());
					replanejamento.getMaterial().getSubElemento().getElemento()
							.setCategoria(new Categoria());
					listarCategorias();
					listarLotacoes();
					Usuario usuarioLogado = (Usuario) FacesContext
							.getCurrentInstance().getExternalContext()
							.getSessionMap().get("usuarioLogado");
					replanejamento
							.getMaterial()
							.getSubLotacao()
							.getLotacao()
							.setCodigo(
									usuarioLogado.getSubLotacao().getLotacao()
											.getCodigo());
					FacesContext.getCurrentInstance().getExternalContext()
							.redirect("listaReplanejamento.jsp");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Replanejamento> pesquisar() {
		totalMaterial = new BigDecimal(0);
		totalNovoSaldo = new BigDecimal(0);
		replanejamentoList = new ArrayList<Replanejamento>();
		if (replanejamento.getMaterial().getSubLotacao().getLotacao() != null
				&& replanejamento.getMaterial().getSubElemento().getElemento()
						.getCategoria().getCodigo() != null
				&& replanejamento.getMaterial().getSubLotacao().getLotacao()
						.getCodigo() != null
				&& replanejamento.getMaterial().getSubElemento().getElemento()
						.getCategoria().getCodigo() != null) {
			replanejamentoList = ReplanejamentoDAO.getInstance().listByFilter(
					replanejamento);
			for (Replanejamento item : replanejamentoList) {
				totalMaterial = totalMaterial.add(item.getMaterial()
						.getVlrTotal());
				if (item.getVlrNovoSaldo() != null) {
					totalNovoSaldo = totalNovoSaldo.add(item.getVlrNovoSaldo());
				}
				if (item.getVlrExecutado() != null) {
					item.setVlrExecutadoString(MoedaUtil.mascaraDinheiro(
							item.getVlrExecutado(), MoedaUtil.DINHEIRO_REAL));
				}
				if (item.getVlrReplanejado() != null) {
					item.setVlrReplanejado(item.getVlrReplanejado());
				}
				if (item.getVlrSuplementar() != null) {
					item.setVlrSuplamentarString(MoedaUtil.mascaraDinheiro(
							item.getVlrSuplementar(), MoedaUtil.DINHEIRO_REAL));
				}
				if (item.getVlrCancelado() != null) {
					item.setVlrCanceladoString(MoedaUtil.mascaraDinheiro(
							item.getVlrCancelado(), MoedaUtil.DINHEIRO_REAL));
				}
			}
			if (replanejamentoList.isEmpty()) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"Nenhum registro cadastrado para o filtro informado!",
						"Nenhum registro cadastrado para o filtro informado!");
				FacesContext.getCurrentInstance().addMessage("", message);
			}
		}

		return replanejamentoList;
	}

	public void calcularNovoSaldo() {
		FacesContext context = FacesContext.getCurrentInstance();
		replanejamento = (Replanejamento) context.getExternalContext()
				.getRequestMap().get("list");

		if (replanejamento.getVlrCanceladoString() != null
				&& replanejamento.getVlrCanceladoString() != "") {
			replanejamento.setVlrNovoSaldo(replanejamento.getVlrReplanejado()
					.subtract(
							new BigDecimal(replanejamento
									.getVlrCanceladoString().replace(".", "")
									.replace(",", "."))));
			totalNovoSaldo = totalNovoSaldo.add(replanejamento
					.getVlrNovoSaldo());
		}
		if (replanejamento.getVlrSuplamentarString() != null
				&& replanejamento.getVlrSuplamentarString() != "") {
			replanejamento.setVlrNovoSaldo(replanejamento.getVlrReplanejado()
					.add(new BigDecimal(replanejamento
							.getVlrSuplamentarString().replace(".", "")
							.replace(",", "."))));
			totalNovoSaldo = totalNovoSaldo.add(replanejamento
					.getVlrNovoSaldo());
		}

	}
}
