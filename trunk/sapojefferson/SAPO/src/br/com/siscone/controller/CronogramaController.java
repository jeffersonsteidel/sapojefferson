package br.com.siscone.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import br.com.siscone.dao.CronogramaDAO;
import br.com.siscone.dao.DAO;
import br.com.siscone.entity.Cronograma;

public class CronogramaController {

	private Cronograma cronograma;
	private List<Cronograma> cronogramaList;

	public Cronograma getCronograma() {
		return cronograma;
	}

	public void setCronograma(Cronograma cronograma) {
		this.cronograma = cronograma;
	}

	public List<Cronograma> getCronogramaList() {
		return cronogramaList;
	}

	public void setCronogramaList(List<Cronograma> cronogramaList) {
		this.cronogramaList = cronogramaList;
	}

	public void cadastrar() throws Exception {
		try {
			cronograma = new Cronograma();
			cronograma =  CronogramaDAO.getInstance().buscar();
			if(cronograma == null){
				cronograma = new Cronograma();
			}
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("cadastroCronograma.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Cronograma editar() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		cronograma = (Cronograma) context.getExternalContext().getRequestMap()
				.get("list");
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("cadastroCronograma.jsp");
		return cronograma;
	}

	public void salvar() throws Exception {
		DAO.getInstance().saveOrUpdate(cronograma);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Cronograma> listar() throws IOException{
		cronogramaList = new ArrayList<Cronograma>();
		cronogramaList = DAO.getInstance().list(Cronograma.class);
		FacesContext.getCurrentInstance().getExternalContext()
		.redirect("listaCronograma.jsp");
		return cronogramaList;
	}

}
