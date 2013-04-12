package br.com.siscone.dao;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.siscone.entity.Lotacao;
import br.com.siscone.entity.SubLotacao;
import br.com.siscone.util.HibernateUtility;

public class SubLotacaoDAO extends DAO {

    private static SubLotacaoDAO instance;

    private SubLotacaoDAO() {
    }

    public static SubLotacaoDAO getInstance() {
        if (instance == null) {
            instance = new SubLotacaoDAO();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
	public List<SubLotacao> listBySubLotacao(Lotacao lotacao) {
		HibernateUtility.getSession().clear();
		HibernateUtility.beginTransaction();
		HibernateUtility.commitTransaction();
		return HibernateUtility.getSession().createCriteria(SubLotacao.class).add(
				Restrictions.eq("lotacao", lotacao)).addOrder(
				Order.asc("descricao")).list();
	}
	

}