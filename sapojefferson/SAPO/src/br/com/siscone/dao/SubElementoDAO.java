package br.com.siscone.dao;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.siscone.entity.Elemento;
import br.com.siscone.entity.SubElemento;
import br.com.siscone.util.HibernateUtility;

public class SubElementoDAO extends DAO {

    private static SubElementoDAO instance;

    private SubElementoDAO() {
    }

    public static SubElementoDAO getInstance() {
        if (instance == null) {
            instance = new SubElementoDAO();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
	public List<SubElemento> listByElemento(Elemento elemento) {
		HibernateUtility.getSession().clear();
		HibernateUtility.beginTransaction();
		HibernateUtility.commitTransaction();
		return HibernateUtility.getSession().createCriteria(SubElemento.class).add(
				Restrictions.eq("elemento", elemento)).addOrder(
				Order.asc("descricao")).list();
	}
	

}