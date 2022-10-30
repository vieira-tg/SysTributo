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

import br.com.systributo.model.Cofins;
import br.com.systributo.service.NegocioException;
import br.com.systributo.util.jpa.Transactional;

public class Cofinses implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Cofins porNome(String cofins) {
		try {
			return manager.createQuery("from Cofins where upper(cst) = :cst", Cofins.class)
					.setParameter("cst", cofins.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Transactional
	public Cofins guardar(Cofins cofins) {
		cofins = manager.merge(cofins);
		return cofins;
	}

	public List<Cofins> lista() {
		try {
			return manager.createQuery("from Cofins ", Cofins.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Cofins porId(Long id) {
		return manager.find(Cofins.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Cofins> filtradros(String cofins) {
		Session session = manager.unwrap(Session.class);
		// Criteria criteria = session.createCriteria(Cofins.class);

		Criteria crit = session.createCriteria(Cofins.class);
		Criterion cst = Restrictions.like("cst", "%" + cofins + "%");
		Criterion descricao = Restrictions.like("descricao", "%" + cofins + "%");
		LogicalExpression orExp = Restrictions.or(cst, descricao);
		crit.add(orExp).addOrder(Order.asc("cst"));
		List<Cofins> results = crit.list();

		return results;
	}

	@Transactional
	public void remover(Cofins cofins) {
		try {
			cofins = porId(cofins.getId());
			manager.remove(cofins);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Cofins não pode ser excluído.");
		}
	}

}
