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

import br.com.systributo.model.Pis;
import br.com.systributo.service.NegocioException;
import br.com.systributo.util.jpa.Transactional;

public class Pises implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Pis porNome(String pis) {
		try {
			return manager.createQuery("from Pis where upper(cst) = :cst", Pis.class)
					.setParameter("cst", pis.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Transactional
	public Pis guardar(Pis pis) {
		pis = manager.merge(pis);
		return pis;
	}

	public List<Pis> lista() {
		try {
			return manager.createQuery("from Pis ", Pis.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Pis porId(Long id) {
		return manager.find(Pis.class, id);
	}

	@Transactional
	public void remover(Pis pis) {// TODO Auto-generated method stub
		try {
			pis = porId(pis.getId());
			manager.remove(pis);
			manager.flush();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new NegocioException("Pis não pode ser excluído.");

		}

	}

	@SuppressWarnings("unchecked")
	public List<Pis> filtradros(String pis) {
		Session session = manager.unwrap(Session.class);
		// Criteria criteria = session.createCriteria(Cofins.class);

		Criteria crit = session.createCriteria(Pis.class);
		Criterion cst = Restrictions.like("cst", "%" + pis + "%");
		Criterion descricao = Restrictions.like("descricao", "%" + pis + "%");
		LogicalExpression orExp = Restrictions.or(cst, descricao);
		crit.add(orExp).addOrder(Order.asc("cst"));
		List<Pis> results = crit.list();

		return results;
	}
}
