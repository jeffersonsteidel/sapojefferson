package br.com.siscone.dao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.Query;

import br.com.siscone.entity.Material;
import br.com.siscone.util.HibernateUtility;

public class MaterialDAO extends DAO {

	private static MaterialDAO instance;
	FacesMessage message;

	private MaterialDAO() {
	}

	public static MaterialDAO getInstance() {
		if (instance == null) {
			instance = new MaterialDAO();
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	public List<Material> listByFilter(Material material) {
		HibernateUtility.getSession().clear();
		Query query = HibernateUtility
				.getSession()
				.createQuery(
						"from Material m where m.subLotacao.lotacao.codigo = :codigoLotacao and m.subElemento.elemento.categoria.codigo  = :codigoCategoria   order by m.prioridade");
		query.setParameter("codigoLotacao", material.getSubLotacao()
				.getLotacao().getCodigo());
		query.setParameter("codigoCategoria", material.getSubElemento()
				.getElemento().getCategoria().getCodigo());
		HibernateUtility.commitTransaction();
		return (List<Material>) query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Material> listByFilterSubLotacao(Material material) {
		HibernateUtility.getSession().clear();
		Query query = HibernateUtility
				.getSession()
				.createQuery(
						"from Material m where m.subLotacao.codigo = :codigoLotacao and m.subElemento.elemento.categoria.codigo  = :codigoCategoria   order by m.prioridade");
		query.setParameter("codigoLotacao", material.getSubLotacao()
				.getCodigo());
		query.setParameter("codigoCategoria", material.getSubElemento()
				.getElemento().getCategoria().getCodigo());
		HibernateUtility.commitTransaction();
		return (List<Material>) query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Material> listByElemento(Material material) {
		HibernateUtility.getSession().clear();
		Query query = HibernateUtility
				.getSession()
				.createQuery(
						"from Material m where m.elemento.codigo = :codigoElemento and m.subElemento.elemento.categoria.codigo  = :codigoCategoria   order by m.descricao");
		query.setParameter("codigoElemento", material.getSubElemento()
				.getElemento().getCodigo());
		query.setParameter("codigoCategoria", material.getSubElemento()
				.getElemento().getCategoria().getCodigo());
		HibernateUtility.commitTransaction();
		return (List<Material>) query.list();
	}

	public void updatePrioridades(Object objeto) {
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
	public List<Material> listBySubLotacaoValor(Material material) {
		HibernateUtility.getSession().clear();
		Query query = HibernateUtility
				.getSession()
				.createQuery(
						"from SubLotacao sub LEFT JOIN FETCH Material mat where sub.lotacao.codigo = :codigoLotacao");
		query.setParameter("codigoLotacao", material.getSubLotacao()
				.getLotacao().getCodigo());
		HibernateUtility.commitTransaction();
		return (List<Material>) query.list();
	}

}