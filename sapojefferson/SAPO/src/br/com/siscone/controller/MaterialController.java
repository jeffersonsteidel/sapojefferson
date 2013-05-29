package br.com.siscone.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.siscone.constantes.Constantes;
import br.com.siscone.dao.AprovacaoDAO;
import br.com.siscone.dao.CronogramaDAO;
import br.com.siscone.dao.DAO;
import br.com.siscone.dao.ElementoDAO;
import br.com.siscone.dao.MaterialDAO;
import br.com.siscone.dao.SubElementoDAO;
import br.com.siscone.dao.SubLotacaoDAO;
import br.com.siscone.entity.Aprovacao;
import br.com.siscone.entity.Categoria;
import br.com.siscone.entity.Cronograma;
import br.com.siscone.entity.Elemento;
import br.com.siscone.entity.Lotacao;
import br.com.siscone.entity.Material;
import br.com.siscone.entity.SubElemento;
import br.com.siscone.entity.SubLotacao;
import br.com.siscone.entity.Unidade;
import br.com.siscone.entity.Usuario;
import br.com.siscone.jsfUtil.DataUtil;
import br.com.siscone.util.MoedaUtil;

public class MaterialController {

	private Material material;
	private Material materialTemp;
	private List<SelectItem> elementos = new ArrayList<SelectItem>();
	private List<SelectItem> lotacoes = new ArrayList<SelectItem>();
	private List<SelectItem> subLotacoes = new ArrayList<SelectItem>();
	private List<SelectItem> categorias = new ArrayList<SelectItem>();
	private List<SelectItem> subElementos = new ArrayList<SelectItem>();
	private ArrayList<Material> materialList = new ArrayList<Material>();
	private ArrayList<SelectItem> unidades = new ArrayList<SelectItem>();
	private BigDecimal total;
	private BigDecimal diferenca;
	private BigDecimal acumulado;
	private Integer prioridade;
	private Integer ultimoNumero;
	private Boolean botaoSalvar;
	private Aprovacao aprovacao;

	private Boolean botao;
	private Boolean comboSubLotacao;

	private List<Aprovacao> aprovacaoList = new ArrayList<Aprovacao>();

	public List<SelectItem> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<SelectItem> categorias) {
		this.categorias = categorias;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public List<SelectItem> getElementos() {
		return elementos;
	}

	public void setElementos(List<SelectItem> elementos) {
		this.elementos = elementos;
	}

	public List<SelectItem> getLotacoes() {
		return lotacoes;
	}

	public void setLotacoes(List<SelectItem> lotacoes) {
		this.lotacoes = lotacoes;
	}

	public List<SelectItem> getSubElementos() {
		return subElementos;
	}

	public void setSubElementos(List<SelectItem> subElementos) {
		this.subElementos = subElementos;
	}

	public ArrayList<Material> getMaterialList() {
		return materialList;
	}

	public void setMaterialList(ArrayList<Material> materialList) {
		this.materialList = materialList;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public BigDecimal getDiferenca() {
		return diferenca;
	}

	public void setDiferenca(BigDecimal diferenca) {
		this.diferenca = diferenca;
	}

	public BigDecimal getAcumulado() {
		return acumulado;
	}

	public void setAcumulado(BigDecimal acumulado) {
		this.acumulado = acumulado;
	}

	public Integer getUltimoNumero() {
		return ultimoNumero;
	}

	public void setUltimoNumero(Integer ultimoNumero) {
		this.ultimoNumero = ultimoNumero;
	}

	public Boolean getBotaoSalvar() {
		return botaoSalvar;
	}

	public void setBotaoSalvar(Boolean botaoSalvar) {
		this.botaoSalvar = botaoSalvar;
	}

	public ArrayList<SelectItem> getUnidades() {
		return unidades;
	}

	public void setUnidades(ArrayList<SelectItem> unidades) {
		this.unidades = unidades;
	}

	public List<Aprovacao> getAprovacaoList() {
		return aprovacaoList;
	}

	public void setAprovacaoList(List<Aprovacao> aprovacaoList) {
		this.aprovacaoList = aprovacaoList;
	}

	public Aprovacao getAprovacao() {
		return aprovacao;
	}

	public void setAprovacao(Aprovacao aprovacao) {
		this.aprovacao = aprovacao;
	}

	public Material getMaterialTemp() {
		return materialTemp;
	}

	public void setMaterialTemp(Material materialTemp) {
		this.materialTemp = materialTemp;
	}

	public Boolean getBotao() {
		return botao;
	}

	public void setBotao(Boolean botao) {
		this.botao = botao;
	}

	public List<SelectItem> getSubLotacoes() {
		return subLotacoes;
	}

	public void setSubLotacoes(List<SelectItem> subLotacoes) {
		this.subLotacoes = subLotacoes;
	}

	public Boolean getComboSubLotacao() {
		return comboSubLotacao;
	}

	public void setComboSubLotacao(Boolean comboSubLotacao) {
		this.comboSubLotacao = comboSubLotacao;
	}

	public void cadastrar() throws Exception {
		try {
			Cronograma cronograma = new Cronograma();
			cronograma = CronogramaDAO.getInstance().buscar();
			if (cronograma != null) {
				if (cronograma.getDataIniCadastrar().compareTo(new Date()) == 1
						|| cronograma.getDataFimCadastrar().compareTo(
								new Date()) == -1) {
					FacesMessage message = new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"O periodo de cadastro é de "
									+ DataUtil.format(
											cronograma.getDataIniCadastrar(),
											"dd/MM/yyyy")
									+ " a "
									+ DataUtil.format(
											cronograma.getDataFimCadastrar(),
											"dd/MM/yyyy") + "!",

							"O periodo de cadastro era de "
									+ DataUtil.format(
											cronograma.getDataIniCadastrar(),
											"dd/MM/yyyy")
									+ " a "
									+ DataUtil.format(
											cronograma.getDataFimCadastrar(),
											"dd/MM/yyyy") + "!")

					;
					FacesContext.getCurrentInstance().addMessage("", message);
				} else {
					comboSubLotacao = true;
					material = new Material();
					material.setSubElemento(new SubElemento());
					material.getSubElemento().setElemento(new Elemento());
					material.setUnidade(new Unidade());
					material.setSubLotacao(new SubLotacao());
					material.getSubLotacao().setLotacao(new Lotacao());
					material.getSubElemento().getElemento()
							.setCategoria(new Categoria());
					lotacoes = new ArrayList<SelectItem>();
					elementos = new ArrayList<SelectItem>();
					categorias = new ArrayList<SelectItem>();
					subElementos = new ArrayList<SelectItem>();
					listarUnidades();
					listarLotacoes();
					listarCategorias();
					Usuario usuarioLogado = (Usuario) FacesContext
							.getCurrentInstance().getExternalContext()
							.getSessionMap().get("usuarioLogado");
					material.getSubLotacao()
							.getLotacao()
							.setCodigo(
									usuarioLogado.getSubLotacao().getLotacao()
											.getCodigo());
					if (!usuarioLogado.getIndAdministrador()
							|| !usuarioLogado.getIndCadastrador()) {
						material.getSubLotacao().setCodigo(
								usuarioLogado.getSubLotacao().getCodigo());
					}
					if (usuarioLogado.getIndAdministrador()
							|| usuarioLogado.getIndCadastrador()) {
						comboSubLotacao = false;
					}
					listarSubLotacoes();
					botao = Boolean.TRUE;
					FacesContext.getCurrentInstance().getExternalContext()
							.redirect("cadastro.jsp");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	public List<SelectItem> listarUnidades() {
		unidades = new ArrayList<SelectItem>();
		List<Unidade> unidadeList = new ArrayList<Unidade>();
		unidadeList = DAO.getInstance().list(Unidade.class, "descricao");
		for (Unidade unidade : unidadeList) {
			unidades.add(new SelectItem(unidade.getCodigo(), unidade
					.getDescricao()));
		}
		return unidades;
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

	public List<SelectItem> listarElementos() {
		elementos = new ArrayList<SelectItem>();
		List<Elemento> elementoList = new ArrayList<Elemento>();
		elementoList = ElementoDAO.getInstance().listByCategoria(
				material.getSubElemento().getElemento().getCategoria());
		for (Elemento elemento : elementoList) {
			elementos.add(new SelectItem(elemento.getCodigo(), elemento
					.getDescricao()));
		}
		return elementos;
	}

	public void calcularTotal() {
		if (material.getVlrUnitarioString() != null
				&& material.getQuantidade() != null) {
			material.setVlrUnitario(new BigDecimal(material
					.getVlrUnitarioString().replace(".", "").replace(",", ".")));
			material.setVlrTotal(material.getVlrUnitario().multiply(
					new BigDecimal(material.getQuantidade())));
			botao = Boolean.FALSE;
		}
	}

	public void salvar() throws Exception {
		if (material.getSubElemento().getCodigo() == null
				&& material.getSubElemento().getElemento().getCodigo() == 18L
				|| material.getSubElemento().getElemento().getCodigo() == 1L) {
			if (material.getSubElemento().getElemento().getCategoria()
					.getCodigo() == 3L) {
				material.getSubElemento().setCodigo(2L);
			} else {
				material.getSubElemento().setCodigo(1L);
			}

		}
		material.setVlrUnitario(new BigDecimal(material.getVlrUnitarioString()
				.replace(".", "").replace(",", ".")));
		DAO.getInstance().saveOrUpdate(material);
		material = new Material();
		material.setSubElemento(new SubElemento());
		material.getSubElemento().setElemento(new Elemento());
		material.setSubLotacao(new SubLotacao());
		material.getSubLotacao().setLotacao(new Lotacao());
		material.setUnidade(new Unidade());
		material.getSubElemento().getElemento().setCategoria(new Categoria());
		Usuario usuarioLogado = (Usuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuarioLogado");
		material.getSubLotacao()
				.getLotacao()
				.setCodigo(
						usuarioLogado.getSubLotacao().getLotacao().getCodigo());
		if (!usuarioLogado.getIndAdministrador()
				|| !usuarioLogado.getIndCadastrador()) {
			material.getSubLotacao().setCodigo(
					usuarioLogado.getSubLotacao().getCodigo());
		}
		botao = Boolean.TRUE;
	}

	public List<SelectItem> listarSubElementos() {
		subElementos = new ArrayList<SelectItem>();
		List<SubElemento> listSubElemento = new ArrayList<SubElemento>();
		listSubElemento = (ArrayList<SubElemento>) SubElementoDAO.getInstance()
				.listByElemento(material.getSubElemento().getElemento());
		for (SubElemento subElemento : listSubElemento) {
			subElementos.add(new SelectItem(subElemento.getCodigo(),
					subElemento.getDescricao()));
		}
		return subElementos;
	}

	public List<SelectItem> listarSubLotacoes() {
		subLotacoes = new ArrayList<SelectItem>();
		List<SubLotacao> listSubLotacao = new ArrayList<SubLotacao>();
		listSubLotacao = (ArrayList<SubLotacao>) SubLotacaoDAO.getInstance()
				.listBySubLotacao(material.getSubLotacao().getLotacao());
		for (SubLotacao subLotacao : listSubLotacao) {
			subLotacoes.add(new SelectItem(subLotacao.getCodigo(), subLotacao
					.getDescricao()));
		}
		return subLotacoes;
	}

	public void listar() throws IOException {
		material = new Material();
		material.setSubElemento(new SubElemento());
		material.getSubElemento().setElemento(new Elemento());
		material.setSubLotacao(new SubLotacao());
		material.getSubLotacao().setLotacao(new Lotacao());
		material.setUnidade(new Unidade());
		material.getSubElemento().getElemento().setCategoria(new Categoria());
		listarCategorias();
		lotacoes = new ArrayList<SelectItem>();
		listarLotacoes();
		Usuario usuarioLogado = (Usuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuarioLogado");
		material.getSubLotacao()
				.getLotacao()
				.setCodigo(
						usuarioLogado.getSubLotacao().getLotacao().getCodigo());
		listarSubLotacoes();
		if (!usuarioLogado.getIndAdministrador()
				|| !usuarioLogado.getIndCadastrador()) {
			material.getSubLotacao().setCodigo(
					usuarioLogado.getSubLotacao().getCodigo());
		}
		materialList = new ArrayList<Material>();
		total = new BigDecimal(0);
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("lista.jsp");
	}

	public List<Material> pesquisar() {
		if (material.getSubLotacao().getLotacao() != null && material.getSubLotacao().getLotacao().getCodigo() != null){
			listarSubLotacoes();
		}
				
		if (material.getSubLotacao().getLotacao() != null
				&& material.getSubElemento().getElemento().getCategoria()
						.getCodigo() != null
				&& material.getSubLotacao().getLotacao().getCodigo() != null
				&& material.getSubElemento().getElemento().getCategoria()
						.getCodigo() != null) {
			total = new BigDecimal(0);
			materialList.clear();
			Usuario usuarioLogado = (Usuario) FacesContext.getCurrentInstance()
					.getExternalContext().getSessionMap().get("usuarioLogado");
			if (usuarioLogado.getIndAdministrador()
					|| usuarioLogado.getIndCadastrador()) {
				materialList = (ArrayList<Material>) MaterialDAO.getInstance()
						.listByFilter(material);
			}
			else {
				materialList = (ArrayList<Material>) MaterialDAO.getInstance()
						.listByFilterSubLotacao(material);
			}
			if(usuarioLogado.getIndPriorizador()){
				materialList.clear();
				materialList = (ArrayList<Material>) MaterialDAO.getInstance()
						.listByLotacao(material);
			}
			for (Material item : materialList) {
				total = total.add(item.getVlrTotal());
			}
			if (materialList.isEmpty()) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"Nenhum registro cadastrado para o filtro informado!",
						"Nenhum registro cadastrado para o filtro informado!");
				FacesContext.getCurrentInstance().addMessage("", message);
				total = new BigDecimal(0);
			} else {
				calcularDiferencaeAcumulado();
			}
		}
		return materialList;
	}

	public void editar() throws IOException {
		Cronograma cronograma = new Cronograma();
		cronograma = CronogramaDAO.getInstance().buscar();
		if (cronograma != null) {
			if (cronograma.getDataIniCadastrar().compareTo(new Date()) == 1
					|| cronograma.getDataFimCadastrar().compareTo(new Date()) == -1) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"O periodo de cadastro é de "
								+ DataUtil.format(
										cronograma.getDataIniCadastrar(),
										"dd/MM/yyyy")
								+ " a "
								+ DataUtil.format(
										cronograma.getDataFimCadastrar(),
										"dd/MM/yyyy") + "!",

						"O periodo de cadastro era de "
								+ DataUtil.format(
										cronograma.getDataIniCadastrar(),
										"dd/MM/yyyy")
								+ " a "
								+ DataUtil.format(
										cronograma.getDataFimCadastrar(),
										"dd/MM/yyyy") + "!")

				;
				FacesContext.getCurrentInstance().addMessage("", message);

			} else {
				listarCategorias();
				listarElementos();
				listarSubElementos();
				listarUnidades();
				listarSubLotacoes();
				botao = Boolean.FALSE;
				material.setVlrUnitarioString(MoedaUtil.mascaraDinheiro(
						material.getVlrUnitario(), MoedaUtil.DINHEIRO_REAL));
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("cadastro.jsp");
			}
		}
	}

	public void priorizar() throws IOException {
		Cronograma cronograma = new Cronograma();
		cronograma = CronogramaDAO.getInstance().buscar();
		if (cronograma != null) {
			if (cronograma.getDataIniPriorizar().compareTo(new Date()) == 1
					|| cronograma.getDataFimPriorizar().compareTo(new Date()) == -1) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"O periodo de priorizar é de "
								+ DataUtil.format(
										cronograma.getDataIniPriorizar(),
										"dd/MM/yyyy")
								+ " a "
								+ DataUtil.format(
										cronograma.getDataFimPriorizar(),
										"dd/MM/yyyy") + "!",

						"O periodo de priorizar é de "
								+ DataUtil.format(
										cronograma.getDataIniPriorizar(),
										"dd/MM/yyyy")
								+ " a "
								+ DataUtil.format(
										cronograma.getDataFimPriorizar(),
										"dd/MM/yyyy") + "!")

				;
				FacesContext.getCurrentInstance().addMessage("", message);
			} else {
				botaoSalvar = false;
				material = new Material();
				material.setSubElemento(new SubElemento());
				material.getSubElemento().setElemento(new Elemento());
				material.setSubLotacao(new SubLotacao());
				material.getSubLotacao().setLotacao(new Lotacao());
				material.setUnidade(new Unidade());
				material.getSubElemento().getElemento()
						.setCategoria(new Categoria());
				materialTemp = new Material();
				materialTemp.setSubElemento(new SubElemento());
				materialTemp.getSubElemento().setElemento(new Elemento());
				materialTemp.setSubLotacao(new SubLotacao());
				materialTemp.getSubLotacao().setLotacao(new Lotacao());
				materialTemp.setUnidade(new Unidade());
				materialTemp.getSubElemento().getElemento()
						.setCategoria(new Categoria());

				listarCategorias();
				listarLotacoes();
				materialList = new ArrayList<Material>();
				total = new BigDecimal(0);
				Usuario usuarioLogado = (Usuario) FacesContext
						.getCurrentInstance().getExternalContext()
						.getSessionMap().get("usuarioLogado");
				material.getSubLotacao()
						.getLotacao()
						.setCodigo(
								usuarioLogado.getSubLotacao().getLotacao()
										.getCodigo());
				if (!usuarioLogado.getIndAdministrador()
						|| !usuarioLogado.getIndCadastrador()) {
					material.getSubLotacao().setCodigo(
							usuarioLogado.getSubLotacao().getCodigo());
				}
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("listaPrioridades.jsp");
			}
		}
	}

	public void salvarPrioridades() {
		acumulado = new BigDecimal(0);
		FacesMessage message = null;
		int erro = 0;

		try {
			for (Material item : materialList) {
				MaterialDAO.getInstance().updatePrioridades(item);
				material = new Material();
				material.setSubElemento(new SubElemento());
				material.getSubElemento().setElemento(new Elemento());
				material.setSubLotacao(new SubLotacao());
				material.getSubLotacao().setLotacao(new Lotacao());
				material.setUnidade(new Unidade());
				material.getSubElemento().getElemento()
						.setCategoria(new Categoria());
				material.setCodigo(item.getCodigo());
				material = (Material) MaterialDAO.getInstance().refresh(
						material);
				if (item.getPrioridade() != 0) {
					if (!zeraExecucao(item)) {
						erro++;
					}
				} else {
					item.setExecucao("-");
					MaterialDAO.getInstance().updatePrioridades(item);
				}
				calcularDiferencaeAcumulado();
			}
			if (materialList.get(0).getSubElemento().getElemento()
					.getCategoria().getCodigo() == 3L
					&& acumulado.compareTo(materialList.get(0).getSubLotacao()
							.getLotacao().getOrcamentoCusteio()) == 1) {
				if (erro > 0) {
					message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"O Limite do Orçamento foi ultrapassado!",
							"O Limite do Orçamento foi ultrapassado!");
					FacesContext.getCurrentInstance().addMessage("", message);
				}
			} else if (materialList.get(0).getSubElemento().getElemento()
					.getCategoria().getCodigo() == 4L
					&& acumulado.compareTo(materialList.get(0).getSubLotacao()
							.getLotacao().getOrcamentoCapital()) == 1) {
				if (erro > 0) {
					message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"O Limite do Orçamento foi ultrapassado!",
							"O Limite do Orçamento foi ultrapassado!");
					FacesContext.getCurrentInstance().addMessage("", message);
					materialList.clear();
					materialList = (ArrayList<Material>) MaterialDAO
							.getInstance().listByFilter(material);
				}
			}

			if (erro == 0) {
				message = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Prioridades atribuidas com sucesso!",
						"Prioridades atribuidas com sucesso!");
				FacesContext.getCurrentInstance().addMessage("", message);
			}
		} catch (Exception e) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Erro ao comunicar com o servidor!",
					"Erro ao comunicar com o servidor!");
			FacesContext.getCurrentInstance().addMessage("", message);
		}
	}

	public void salvarPrioridadesAprovador() {
		Cronograma cronograma = new Cronograma();
		cronograma = CronogramaDAO.getInstance().buscar();
		if (cronograma != null) {
			if (cronograma.getDataIniAprovar().compareTo(new Date()) == 1
					|| cronograma.getDataFimAprovar().compareTo(new Date()) == -1) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"O periodo de priorizar e aprovar é de "
								+ DataUtil.format(
										cronograma.getDataIniAprovar(),
										"dd/MM/yyyy")
								+ " a "
								+ DataUtil.format(
										cronograma.getDataFimAprovar(),
										"dd/MM/yyyy") + "!",

						"O periodo de priorizar e aprovar é de "
								+ DataUtil.format(
										cronograma.getDataIniAprovar(),
										"dd/MM/yyyy")
								+ " a "
								+ DataUtil.format(
										cronograma.getDataFimAprovar(),
										"dd/MM/yyyy") + "!")

				;
				FacesContext.getCurrentInstance().addMessage("", message);
			} else {
				acumulado = new BigDecimal(0);
				FacesMessage message = null;
				int erro = 0;

				try {
					for (Material item : materialList) {
						MaterialDAO.getInstance().updatePrioridades(item);
						material = new Material();
						material.setSubElemento(new SubElemento());
						material.getSubElemento().setElemento(new Elemento());
						material.setSubLotacao(new SubLotacao());
						material.getSubLotacao().setLotacao(new Lotacao());
						material.setUnidade(new Unidade());
						material.getSubElemento().getElemento()
								.setCategoria(new Categoria());
						material.setCodigo(item.getCodigo());
						material = (Material) MaterialDAO.getInstance()
								.refresh(material);
						if (item.getPrioridade() != 0) {
							if (!zeraExecucao(item)) {
								erro++;
							}
						} else {
							item.setExecucao("-");
							MaterialDAO.getInstance().updatePrioridades(item);
						}
						calcularDiferencaeAcumulado();
					}
					if (materialList.get(0).getSubElemento().getElemento()
							.getCategoria().getCodigo() == 3L
							&& acumulado.compareTo(materialList.get(0)
									.getSubLotacao().getLotacao()
									.getOrcamentoCusteio()) == 1) {
						if (erro > 0) {
							message = new FacesMessage(
									FacesMessage.SEVERITY_ERROR,
									"O Limite do Orçamento foi ultrapassado!",
									"O Limite do Orçamento foi ultrapassado!");
							FacesContext.getCurrentInstance().addMessage("",
									message);
						}
					} else if (materialList.get(0).getSubElemento()
							.getElemento().getCategoria().getCodigo() == 4L
							&& acumulado.compareTo(materialList.get(0)
									.getSubLotacao().getLotacao()
									.getOrcamentoCapital()) == 1) {
						if (erro > 0) {
							message = new FacesMessage(
									FacesMessage.SEVERITY_ERROR,
									"O Limite do Orçamento foi ultrapassado!",
									"O Limite do Orçamento foi ultrapassado!");
							FacesContext.getCurrentInstance().addMessage("",
									message);
							materialList.clear();
							materialList = (ArrayList<Material>) MaterialDAO
									.getInstance().listByFilter(material);
						}
					}

					if (erro == 0) {
						message = new FacesMessage(
								FacesMessage.SEVERITY_INFO,
								"Prioridades atribuidas com sucesso. Clique em 'Aprovar' para aprovar todos os itens!",
								"Prioridades atribuidas com sucesso. Clique em 'Aprovar' para aprovar todos os itens!");
						FacesContext.getCurrentInstance().addMessage("",
								message);
					}
				} catch (Exception e) {
					message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Erro ao comunicar com o servidor!",
							"Erro ao comunicar com o servidor!");
					FacesContext.getCurrentInstance().addMessage("", message);
				}
			}
		}
	}

	public boolean zeraExecucao(Material material) {
		if (acumulado.compareTo(new BigDecimal(0)) == 1) {
			if (material.getSubElemento().getElemento().getCategoria()
					.getCodigo() == 4L
					&& material.getSubLotacao().getLotacao()
							.getOrcamentoCapital().compareTo(acumulado) == -1) {
				material.setExecucao("-");
				return false;
			} else if (material.getSubElemento().getElemento().getCategoria()
					.getCodigo() == 3L
					&& material.getSubLotacao().getLotacao()
							.getOrcamentoCusteio().compareTo(acumulado) == -1) {
				material.setExecucao("-");
				return false;
			}
		}
		if (material.getSubElemento().getElemento().getCategoria().getCodigo() == 4L
				&& material.getSubLotacao().getLotacao().getOrcamentoCapital()
						.compareTo(material.getVlrTotal()) == -1) {
			material.setExecucao("-");
			return false;
		} else if (material.getSubElemento().getElemento().getCategoria()
				.getCodigo() == 3L
				&& material.getSubLotacao().getLotacao().getOrcamentoCusteio()
						.compareTo(material.getVlrTotal()) == -1) {
			material.setExecucao("-");
			return false;
		} else {
			material.setExecucao(Constantes.ANO_VIGENTE);
			MaterialDAO.getInstance().updatePrioridades(material);
			return true;
		}

	}

	public void calcularDiferencaeAcumulado() {
		acumulado = new BigDecimal(0);
		diferenca = new BigDecimal(0);
		Lotacao lotacao = new Lotacao();
		lotacao = (Lotacao) DAO.getInstance().refresh(
				material.getSubLotacao().getLotacao());
		material.getSubLotacao().setLotacao(lotacao);
		for (Material item : materialList) {
			if (acumulado != total) {
				if (item.getPrioridade() != 0) {
					acumulado = acumulado.add(item.getVlrTotal());
					if (item.getSubElemento().getElemento().getCategoria()
							.getCodigo().equals(3L)) {
						diferenca = item.getSubLotacao().getLotacao()
								.getOrcamentoCusteio().subtract(acumulado);
					} else {
						diferenca = item.getSubLotacao().getLotacao()
								.getOrcamentoCapital().subtract(acumulado);
					}
				}
			}
		}
		if (acumulado.compareTo(new BigDecimal(0)) == 0) {
			@SuppressWarnings("unused")
			FacesMessage message = null;
		}
	}

	/*
	 * public void numeroExiste() { int numeroRepetido = 0; FacesMessage message
	 * = null; for (Material item : materialList) { if (item.getPrioridade() ==
	 * ultimoNumero) { numeroRepetido++; } } if (numeroRepetido > 1 &&
	 * ultimoNumero != 0) { message = new
	 * FacesMessage(FacesMessage.SEVERITY_ERROR,
	 * "Número de Prioridade já Existente!",
	 * "Número de Prioridade já Existente!");
	 * FacesContext.getCurrentInstance().addMessage("", message); botaoSalvar =
	 * false; } else { botaoSalvar = true; } }
	 */

	public void numeroExiste2() {
		List<Material> listaAnterior = new ArrayList<Material>();
		List<Material> listaPosterior = new ArrayList<Material>();
		if (materialTemp.getPrioridade() == 0) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Não pode haver prioridade ZERO!",
					"Não pode haver prioridade ZERO!");
			FacesContext.getCurrentInstance().addMessage("", message);
		} else {
			materialList.remove(materialTemp);
			for (Material item : materialList) {
				if (item.getPrioridade() == 0
						|| item.getPrioridade() < materialTemp.getPrioridade()) {
					listaAnterior.add(item);
				}
				if (item.getPrioridade() == materialTemp.getPrioridade()) {
					item.setPrioridade(item.getPrioridade() + 1);
				}
				if (item.getPrioridade() > materialTemp.getPrioridade()) {
					item.setPrioridade(item.getPrioridade());
					listaPosterior.add(item);
				}
			}
			materialList = new ArrayList<Material>();
			materialList.addAll(listaAnterior);
			materialList.add(materialTemp);
			materialList.addAll(listaPosterior);

			int i = 0;
			for (Material item : materialList) {
				if (item.getPrioridade() != 0) {
					item.setPrioridade(++i);
				}
			}
			botaoSalvar = true;
		}
	}

	/*
	 * public void numeroExisteAprovar() { int numeroRepetido = 0; FacesMessage
	 * message = null; for (Material item : materialList) { if
	 * (item.getPrioridade() == ultimoNumero) { numeroRepetido++; } } if
	 * (numeroRepetido > 1 && ultimoNumero != 0) { message = new
	 * FacesMessage(FacesMessage.SEVERITY_ERROR,
	 * "Número de Prioridade já Existente!",
	 * "Número de Prioridade já Existente!");
	 * FacesContext.getCurrentInstance().addMessage("", message); botaoSalvar =
	 * false; } else { botaoSalvar = true; message = new FacesMessage(
	 * FacesMessage.SEVERITY_INFO,
	 * "Para confirmar a alteração de prioridades efetuada, por favor clique em 'Alterar Prioridades',e depois em 'Aprovar'"
	 * ,
	 * "Para confirmar a alteração de prioridades efetuada, por favor clique em 'Alterar Prioridades',e depois em 'Aprovar'!"
	 * ); FacesContext.getCurrentInstance().addMessage("", message); } }
	 */

	public void numeroExiste2Aprovar() {
		FacesMessage message = null;
		List<Material> listaAnterior = new ArrayList<Material>();
		List<Material> listaPosterior = new ArrayList<Material>();
		if (materialTemp.getPrioridade() == 0) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Não pode haver prioridade ZERO!",
					"Não pode haver prioridade ZERO!");
			FacesContext.getCurrentInstance().addMessage("", message);
		} else {
			materialList.remove(materialTemp);
			for (Material item : materialList) {
				if (item.getPrioridade() == 0
						|| item.getPrioridade() < materialTemp.getPrioridade()) {
					listaAnterior.add(item);
				}
				if (item.getPrioridade() == materialTemp.getPrioridade()) {
					item.setPrioridade(item.getPrioridade() + 1);
				}
				if (item.getPrioridade() > materialTemp.getPrioridade()) {
					item.setPrioridade(item.getPrioridade());
					listaPosterior.add(item);
				}
			}
			materialList = new ArrayList<Material>();
			materialList.addAll(listaAnterior);
			materialList.add(materialTemp);
			materialList.addAll(listaPosterior);

			int i = 0;
			for (Material item : materialList) {
				if (item.getPrioridade() != 0) {
					item.setPrioridade(++i);
				}
			}
			botaoSalvar = true;
			message = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					"Para confirmar a alteração de prioridades efetuada, por favor clique em 'Alterar Prioridades',e depois em 'Aprovar'",
					"Para confirmar a alteração de prioridades efetuada, por favor clique em 'Alterar Prioridades',e depois em 'Aprovar'!");
			FacesContext.getCurrentInstance().addMessage("", message);
		}
	}

	public void aprovarPrioridades() throws Exception {
		try {
			Cronograma cronograma = new Cronograma();
			cronograma = CronogramaDAO.getInstance().buscar();
			if (cronograma != null) {
				if (cronograma.getDataIniAprovar().compareTo(new Date()) == 1
						|| cronograma.getDataFimAprovar().compareTo(new Date()) == -1) {
					FacesMessage message = new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"O periodo de priorizar é de "
									+ DataUtil.format(
											cronograma.getDataIniAprovar(),
											"dd/MM/yyyy")
									+ " a "
									+ DataUtil.format(
											cronograma.getDataFimAprovar(),
											"dd/MM/yyyy") + "!",

							"O periodo de priorizar é de "
									+ DataUtil.format(
											cronograma.getDataIniAprovar(),
											"dd/MM/yyyy")
									+ " a "
									+ DataUtil.format(
											cronograma.getDataFimAprovar(),
											"dd/MM/yyyy") + "!")

					;
					FacesContext.getCurrentInstance().addMessage("", message);

				} else {
					botaoSalvar = false;
					material = new Material();
					material.setSubElemento(new SubElemento());
					material.getSubElemento().setElemento(new Elemento());
					material.getSubElemento().getElemento()
							.setCategoria(new Categoria());
					material.setSubLotacao(new SubLotacao());
					material.getSubLotacao().setLotacao(new Lotacao());
					material.setUnidade(new Unidade());

					materialTemp = new Material();
					materialTemp.setSubElemento(new SubElemento());
					materialTemp.getSubElemento().setElemento(new Elemento());
					materialTemp.getSubElemento().getElemento()
							.setCategoria(new Categoria());
					materialTemp.setSubLotacao(new SubLotacao());
					materialTemp.getSubLotacao().setLotacao(new Lotacao());
					materialTemp.setUnidade(new Unidade());

					listarUnidades();
					Usuario usuarioLogado = (Usuario) FacesContext
							.getCurrentInstance().getExternalContext()
							.getSessionMap().get("usuarioLogado");
					material.setSubLotacao(usuarioLogado.getSubLotacao());
					if (!usuarioLogado.getIndAdministrador()
							|| !usuarioLogado.getIndCadastrador()) {
						material.getSubLotacao().setCodigo(
								usuarioLogado.getSubLotacao().getCodigo());
					}
					listarLotacoes();
					listarCategorias();
					FacesContext.getCurrentInstance().getExternalContext()
							.redirect("aprovarPrioridades.jsp");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void aprovar() throws Exception {
		Aprovacao aprovacao = new Aprovacao();
		aprovacao.setCategoria(new Categoria());
		aprovacao.setSubLotacao(new SubLotacao());
		aprovacao.getSubLotacao().setLotacao(new Lotacao());
		aprovacao.setCategoria(material.getSubElemento().getElemento()
				.getCategoria());
		aprovacao.setSubLotacao(material.getSubLotacao());
		aprovacao.getSubLotacao().setLotacao(
				material.getSubLotacao().getLotacao());
		aprovacao.setAcumulado(acumulado);
		aprovacao.setDiferenca(diferenca);
		if (material.getSubElemento().getElemento().getCategoria().getCodigo() != 4L) {
			aprovacao.setOrcamento(material.getSubLotacao().getLotacao()
					.getOrcamentoCusteio());
		}
		if (material.getSubElemento().getElemento().getCategoria().getCodigo() != 3L) {
			aprovacao.setOrcamento(material.getSubLotacao().getLotacao()
					.getOrcamentoCapital());
		}
		validaAprovacao(aprovacao);
		aprovacao.setTotal(total);
		DAO.getInstance().saveOrUpdate(aprovacao);
		material = new Material();
		material.setSubElemento(new SubElemento());
		material.getSubElemento().setElemento(new Elemento());
		material.setSubLotacao(new SubLotacao());
		material.getSubLotacao().setLotacao(new Lotacao());
		material.setUnidade(new Unidade());
		material.getSubElemento().getElemento().setCategoria(new Categoria());
		Usuario usuarioLogado = (Usuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuarioLogado");
		material.getSubLotacao()
				.getLotacao()
				.setCodigo(
						usuarioLogado.getSubLotacao().getLotacao().getCodigo());
		if (!usuarioLogado.getIndAdministrador()
				|| !usuarioLogado.getIndCadastrador()) {
			material.getSubLotacao().setCodigo(
					usuarioLogado.getSubLotacao().getCodigo());
		}
	}

	@SuppressWarnings("unchecked")
	public void listarAprovacao() throws Exception {
		aprovacaoList = new ArrayList<Aprovacao>();
		Usuario usuarioLogado = (Usuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuarioLogado");
		if (usuarioLogado.getIndAdministrador()) {
			aprovacaoList = DAO.getInstance().list(Aprovacao.class);
		} else {
			aprovacaoList = AprovacaoDAO.getInstance().listAprovacaoByLotacao(
					usuarioLogado.getSubLotacao());
		}
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("listaAprovacao.jsp");
	}

	public void validaAprovacao(Aprovacao aprovacao) {
		List<Aprovacao> aprovacaoList = new ArrayList<Aprovacao>();
		aprovacaoList = AprovacaoDAO.getInstance().listByFilter(aprovacao);
		if (aprovacaoList.size() > 0) {
			for (Aprovacao item : aprovacaoList) {
				DAO.getInstance().deleteAprovacao(item);
			}
		}
	}

	public void excluir() throws IOException {
		DAO.getInstance().delete(material);
		pesquisar();
	}

	@SuppressWarnings("unchecked")
	public void excluirAprovacao() throws IOException {
		Usuario usuarioLogado = (Usuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuarioLogado");
		DAO.getInstance().delete(aprovacao);
		if (usuarioLogado.getIndAdministrador()) {
			aprovacaoList = DAO.getInstance().list(Aprovacao.class);
		} else {
			aprovacaoList = AprovacaoDAO.getInstance().listAprovacaoByLotacao(
					usuarioLogado.getSubLotacao());
		}
	}

	public void validaMaterialCadastrado() {
		List<Material> materialList = new ArrayList<Material>();
		materialList = MaterialDAO.getInstance()
				.listBySubLotacaoValor(material);
		if (!materialList.isEmpty()) {
			for (Material material : materialList) {
				if ((material.getVlrTotal() == null)
						|| new Double(material.getVlrTotal().toString()) < 1) {
					FacesMessage message = new FacesMessage(
							FacesMessage.SEVERITY_INFO,
							"Existem Sub Lotações com valores abaixo de R$ 1.00!",
							"Existem Sub Lotações com valores abaixo de R$ 1.00!");
					FacesContext.getCurrentInstance().addMessage("", message);
				}
			}

		}
	}

}
