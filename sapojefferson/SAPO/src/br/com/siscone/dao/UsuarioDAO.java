package br.com.siscone.dao;

import java.security.NoSuchAlgorithmException;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.siscone.entity.Usuario;
import br.com.siscone.util.Encripty;
import br.com.siscone.util.HibernateUtility;

public class UsuarioDAO extends DAO {

	private static UsuarioDAO instance;

	private UsuarioDAO() {
	}

	public static UsuarioDAO getInstance() {
		if (instance == null) {
			instance = new UsuarioDAO();
		}
		return instance;
	}

	public Usuario autentica(Usuario usuario) throws NoSuchAlgorithmException {
		HibernateUtility.beginTransaction();
		Criteria c = HibernateUtility.getSession()
				.createCriteria(Usuario.class);
		c.add(Restrictions.eq("login", usuario.getLogin().toLowerCase()));
		c.add(Restrictions.eq("senha",
				Encripty.criptografaSenha(usuario.getSenha())));
		//HibernateUtility.commitTransaction();
		return (Usuario) c.uniqueResult();
	}

}