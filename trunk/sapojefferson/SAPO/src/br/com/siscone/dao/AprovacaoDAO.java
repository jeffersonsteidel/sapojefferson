package br.com.siscone.dao;

import java.util.List;

import javax.faces.application.FacesMessage;

import org.hibernate.Query;

import br.com.siscone.entity.Aprovacao;
import br.com.siscone.entity.SubLotacao;
import br.com.siscone.util.HibernateUtility;

public class AprovacaoDAO extends DAO {

	private static AprovacaoDAO instance;
	FacesMessage message;

	private AprovacaoDAO() {
	}

	public static AprovacaoDAO getInstance() {
		if (instance == null) {
			instance = new AprovacaoDAO();
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	public List<Aprovacao> listByFilter(Aprovacao aprovacao) {
		HibernateUtility.getSession().clear();
		Query query = HibernateUtility
				.getSession()
				.createQuery(
						"from Aprovacao a where a.subLotacao.lotacao.codigo = :codigoLotacao and a.categoria.codigo  = :codigoCategoria");
		query.setParameter("codigoLotacao", aprovacao.getSubLotacao().getLotacao().getCodigo());
		query.setParameter("codigoCategoria", aprovacao.getCategoria()
				.getCodigo());
		HibernateUtility.commitTransaction();
		return (List<Aprovacao>) query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Aprovacao> listAprovacaoByLotacao(SubLotacao subLotacao) {
		HibernateUtility.getSession().clear();
		Query query = HibernateUtility
				.getSession()
				.createQuery(
						"from Aprovacao a where a.subLotacao.lotacao.codigo = :codigoLotacao");
		query.setParameter("codigoLotacao", subLotacao.getLotacao().getCodigo());
		HibernateUtility.commitTransaction();
		return (List<Aprovacao>) query.list();
	}
}