package br.com.siscone.dao;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.siscone.entity.Categoria;
import br.com.siscone.entity.Elemento;
import br.com.siscone.util.HibernateUtility;

public class ElementoDAO extends DAO {

    private static ElementoDAO instance;

    private ElementoDAO() {
    }

    public static ElementoDAO getInstance() {
        if (instance == null) {
            instance = new ElementoDAO();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
	public List<Elemento> listByCategoria(Categoria categoria) {
		HibernateUtility.getSession().clear();
		HibernateUtility.beginTransaction();
		HibernateUtility.commitTransaction();
		return HibernateUtility.getSession().createCriteria(Elemento.class).add(
				Restrictions.eq("categoria", categoria)).addOrder(
				Order.asc("descricao")).list();
	}
	

}