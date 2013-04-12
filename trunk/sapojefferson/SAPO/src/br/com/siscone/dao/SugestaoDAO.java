package br.com.siscone.dao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.Query;

import br.com.siscone.entity.Sugestao;
import br.com.siscone.util.HibernateUtility;

public class SugestaoDAO extends DAO {

	private static SugestaoDAO instance;
	FacesMessage message;

	private SugestaoDAO() {
	}

	public static SugestaoDAO getInstance() {
		if (instance == null) {
			instance = new SugestaoDAO();
		}
		return instance;
	}

	public void updateSugestao(Object objeto) {
		try {
			HibernateUtility.getSession().clear();
			HibernateUtility.beginTransaction();
			HibernateUtility.getSession().update(objeto);
			HibernateUtility.commitTransaction();
		} catch (Exception e) {
			HibernateUtility.rollbackTransaction();
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Erro ao comunicar com o servidor!",
					"Erro ao comunicar com o servidor!");
			FacesContext.getCurrentInstance().addMessage("", message);
		}
	}
	@SuppressWarnings("unchecked")
	public List<Sugestao> listByFilter(Integer indicador) {
		HibernateUtility.getSession().clear();
		HibernateUtility.beginTransaction();
		String sql ="from Sugestao s where 1 = 1  ";
		//lida
		if(indicador == 1){
			sql += " and s.lida = 1";
		}
		//não lida
		if(indicador == 2){
			sql += " and s.lida = 0";
		}
		//arquivada
		if(indicador == 3){
			sql += " and s.arquivada = 1";
		}
		Query query = HibernateUtility
				.getSession()
				.createQuery(sql);
		HibernateUtility.commitTransaction();
		return (List<Sugestao>) query.list();
	}

}