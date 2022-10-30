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

import br.com.systributo.model.Cliente;
import br.com.systributo.service.NegocioException;
import br.com.systributo.util.jpa.Transactional;

public class Clientes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public List<Cliente> buscaClientes() {
		return manager.createQuery("from Cliente", Cliente.class)
				.getResultList();
	}

	public Cliente porDocumentoReceita(String registroReceita) {
		try {
			return manager
					.createQuery(
							"from Cliente where upper(registroReceita) = :registroReceita",
							Cliente.class)
					.setParameter("registroReceita",
							registroReceita.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Transactional	
	public Cliente guardar(Cliente cliente) {
		cliente = manager.merge(cliente);

		return cliente;
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> filtradros(String cliente) {
		Session session = manager.unwrap(Session.class);
		// Criteria criteria = session.createCriteria(Cofins.class);

		Criteria crit = session.createCriteria(Cliente.class);
		Criterion nome = Restrictions.like("nome", "%" + cliente + "%");
		Criterion registro = Restrictions.like("registroReceita", "%" + cliente
				+ "%");

		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(nome);
		disjunction.add(registro);

		crit.add(disjunction).addOrder(Order.asc("id"));

		List<Cliente> results = crit.list();

		return results;
	}

	@Transactional
	public void remover(Cliente cliente) {
		try {
			cliente = porDocumentoReceita(cliente.getRegistroReceita());
			manager.remove(cliente);
			manager.flush();

		} catch (PersistenceException e) {
			throw new NegocioException("Cliente não pode ser excluído.");
		}
	}

	public Cliente porId(Long id) {
		return manager.find(Cliente.class, id);
	}

}
