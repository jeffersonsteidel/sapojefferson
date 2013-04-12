package br.com.siscone.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.siscone.entity.Cronograma;
import br.com.siscone.util.HibernateUtility;

public class CronogramaDAO extends DAO {

	private static CronogramaDAO instance;

	private CronogramaDAO() {
	}

	public static CronogramaDAO getInstance() {
		if (instance == null) {
			instance = new CronogramaDAO();
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	public Cronograma buscar() {
		HibernateUtility.beginTransaction();
		List<Cronograma> cronogramaList = new ArrayList<Cronograma>();
		cronogramaList = DAO.getInstance().list(Cronograma.class);
		HibernateUtility.commitTransaction();
		if(!cronogramaList.isEmpty()){
			return cronogramaList.get(0);
		}else{
		return null;
		}
	}
}