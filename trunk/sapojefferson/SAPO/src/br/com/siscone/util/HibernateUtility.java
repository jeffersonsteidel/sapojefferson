package br.com.siscone.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtility {
	private static final SessionFactory factory;
	@SuppressWarnings("rawtypes")
	private static final ThreadLocal sessionThread = new ThreadLocal();
	@SuppressWarnings("rawtypes")
	private static final ThreadLocal transactionThread = new ThreadLocal();
	static {
		// Bloco estático que inicializa o Hibernate, escreve o stack trace se
		// houver algum problema e relança a exceção
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	public static Session getSession() {
		try {

			if (sessionThread.get() == null) {
				Session session = factory.openSession();
				session.clear();
				session.flush();
				sessionThread.set(session);
			}
		} catch (Exception e) {
			System.err.print("Caiu aqui!!!!");
		}
		return (Session) sessionThread.get();
	}

	@SuppressWarnings("unchecked")
	public static void closeSession() {
		Session session = (Session) sessionThread.get();
		if (session != null && session.isOpen()) {
			sessionThread.set(null);
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public static void beginTransaction() {
		Transaction transaction = getSession().beginTransaction();
		transactionThread.set(transaction);
	}

	@SuppressWarnings("unchecked")
	public static void commitTransaction() {
		Transaction transaction = (Transaction) transactionThread.get();
		if (transaction != null && !transaction.wasCommitted()
				&& !transaction.wasRolledBack()) {
			transaction.commit();
			transactionThread.set(null);
		}
	}

	@SuppressWarnings("unchecked")
	public static void rollbackTransaction() {
		Transaction transaction = (Transaction) transactionThread.get();
		if (transaction != null && !transaction.wasCommitted()
				&& !transaction.wasRolledBack()) {
			transaction.rollback();
			transactionThread.set(null);
		}
	}
}
