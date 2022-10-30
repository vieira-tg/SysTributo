package br.com.systributo.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.systributo.model.UsuarioSite;
import br.com.systributo.service.NegocioException;
import br.com.systributo.util.jpa.Transactional;

public class UsuariosSites implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public UsuarioSite porNome(String nome) {
		try {
			return manager.createQuery("from UsuarioSite where upper(email) = :email", UsuarioSite.class)
					.setParameter("email", nome.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Transactional
	public UsuarioSite guardar(UsuarioSite usuario) {
		usuario = manager.merge(usuario);

		return usuario;
	}

	public UsuarioSite porEmail(String email) {
		try {
			return manager.createQuery("from UsuarioSite where upper(email) = :email", UsuarioSite.class)
					.setParameter("email", email.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public UsuarioSite porId(Long id) {
		return manager.find(UsuarioSite.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<UsuarioSite> filtradros(String usuario) {
		Session session = manager.unwrap(Session.class);
		// Criteria criteria = session.createCriteria(Cofins.class);

		Criteria crit = session.createCriteria(UsuarioSite.class);
		Criterion nome = Restrictions.like("nome", "%" + usuario + "%");
		Criterion email = Restrictions.like("email", "%" + usuario + "%");
		LogicalExpression orExp = Restrictions.or(nome, email);
		crit.add(orExp).addOrder(Order.asc("nome"));
		List<UsuarioSite> results = crit.list();

		return results;
	}

	@Transactional
	public void remover(UsuarioSite usuario) {
		try {
			usuario = porId(usuario.getId());
			manager.remove(usuario);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Usuário não pode ser excluído.");
		}
	}

}
