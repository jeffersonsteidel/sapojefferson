package br.com.siscone.dao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.Query;
import org.hibernate.exception.ConstraintViolationException;

import br.com.siscone.entity.Replanejamento;
import br.com.siscone.util.HibernateUtility;

public class ReplanejamentoDAO {

	private static ReplanejamentoDAO instance;
	FacesMessage message;

	private ReplanejamentoDAO() {
	}

	public static ReplanejamentoDAO getInstance() {
		if (instance == null) {
			instance = new ReplanejamentoDAO();
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	public List<Replanejamento> listByFilter(Replanejamento replanejamento) {
		HibernateUtility.getSession().clear();
		Query query = HibernateUtility
				.getSession()
				.createQuery(
						"from Replanejamento r where r.material.subLotacao.lotacao.codigo = :codigoLotacao and r.material.subElemento.elemento.categoria.codigo  = :codigoCategoria");
		query.setParameter("codigoLotacao", replanejamento.getMaterial()
				.getSubLotacao().getLotacao().getCodigo());
		query.setParameter("codigoCategoria", replanejamento.getMaterial()
				.getSubElemento().getElemento().getCategoria().getCodigo());
		HibernateUtility.commitTransaction();
		return (List<Replanejamento>) query.list();
	}

	public void delete(Object objeto) {
		try {
			HibernateUtility.getSession().clear();
			HibernateUtility.beginTransaction();
			HibernateUtility.getSession().delete(objeto);
			HibernateUtility.commitTransaction();
		} catch (ConstraintViolationException e) {
			HibernateUtility.getSession().getTransaction().rollback();
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Item com dependências!",
					"Item com dependências!");
			FacesContext.getCurrentInstance().addMessage("", message);
		} catch (Exception e) {
			HibernateUtility.getSession().getTransaction().rollback();
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Erro ao comunicar com o servidor!",
					"Erro ao comunicar com o servidor!");
			FacesContext.getCurrentInstance().addMessage("", message);
		}
	}

}
