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
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.systributo.model.Produto;
import br.com.systributo.service.NegocioException;
import br.com.systributo.util.jpa.Transactional;

public class Produtos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@Transactional
	public Produto guardar(Produto produto) {

		produto = manager.merge(produto);

		return produto;
	}

	public Produto porSku(String barras) {
		try {
			return manager.createQuery("from Produto where upper(barras) = :barras", Produto.class)
					.setParameter("barras", barras.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Produto> filtradros(String produto) {
		Session session = manager.unwrap(Session.class);
		// Criteria criteria = session.createCriteria(Cofins.class);

		Criteria crit = session.createCriteria(Produto.class);
		Criterion id = Restrictions.like("descricao", "%" + produto + "%");
		Criterion barras = Restrictions.like("barras", "%" + produto + "%");
		Criterion descricao = Restrictions.like("descricao", "%" + produto + "%");
		Criterion ncm = Restrictions.like("ncm", "%" + produto + "%");
		Criterion obs = Restrictions.like("obs", "%" + produto + "%");
		// LogicalExpression orExp = Restrictions.or(descricao, ncm, obs);

		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(id);
		disjunction.add(barras);
		disjunction.add(descricao);
		disjunction.add(ncm);
		disjunction.add(obs);
		crit.add(disjunction).addOrder(Order.asc("id"));

		List<Produto> results = crit.list();

		return results;
	}

	@Transactional
	public void remover(Produto produto) {
		try {
			produto = porSku(produto.getBarras());
			manager.remove(produto);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Produto não pode ser excluído.");
		}
	}

	public Produto porId(Long id) {
		return manager.find(Produto.class, id);
	}

}
