package br.com.siscone.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;

import br.com.siscone.dao.DAO;
import br.com.siscone.dao.SugestaoDAO;
import br.com.siscone.entity.Sugestao;
import br.com.siscone.entity.Usuario;

public class SugestaoController {

	private Sugestao sugestao;
	private List<Sugestao> sugestaoList;
	private int indicador = 0;

	public Sugestao getSugestao() {
		return sugestao;
	}

	public void setSugestao(Sugestao sugestao) {
		this.sugestao = sugestao;
	}

	public List<Sugestao> getSugestaoList() {
		return sugestaoList;
	}

	public void setSugestaoList(List<Sugestao> sugestaoList) {
		this.sugestaoList = sugestaoList;
	}
	
	public int getIndicador() {
		return indicador;
	}

	public void setIndicador(int indicador) {
		this.indicador = indicador;
	}

	public void cadastrar() throws Exception {
		try {
			sugestao = new Sugestao();
			sugestao.setUsuario(new Usuario());
			Usuario usuarioLogado = (Usuario) FacesContext
					.getCurrentInstance().getExternalContext()
					.getSessionMap().get("usuarioLogado");
			sugestao.setUsuario(usuarioLogado);
			sugestao.setData(new Date());
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("cadastroSugestao.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Sugestao editar() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		sugestao = (Sugestao) context.getExternalContext().getRequestMap()
				.get("list");
		sugestao.setLida(Boolean.TRUE);
		SugestaoDAO.getInstance().updateSugestao(sugestao);
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("cadastroSugestao.jsp");
		return sugestao;
	}

	public void salvar() throws Exception {
		if(sugestao.getCodigo() == null || sugestao.getCodigo() == 0){
			sugestao.setArquivada(Boolean.FALSE);
			sugestao.setLida(Boolean.FALSE);
		}
		DAO.getInstance().saveOrUpdate(sugestao);
		sugestao = new Sugestao();
		sugestao.setUsuario(new Usuario());
		Usuario usuarioLogado = (Usuario) FacesContext
				.getCurrentInstance().getExternalContext()
				.getSessionMap().get("usuarioLogado");
		sugestao.setUsuario(usuarioLogado);
	}
	

	@SuppressWarnings("unchecked")
	public void excluir() throws IOException {
		DAO.getInstance().delete(sugestao);
		sugestaoList = DAO.getInstance().list(Sugestao.class);
	}
	
	public void listar() throws IOException{
		sugestaoList = new ArrayList<Sugestao>();
		FacesContext.getCurrentInstance().getExternalContext()
		.redirect("listaSugestao.jsp");
	}
	
	public List<Sugestao> pesquisar() throws IOException{
		sugestaoList = new ArrayList<Sugestao>();
		sugestaoList = SugestaoDAO.getInstance().listByFilter(indicador);
		return sugestaoList;
	}

}
