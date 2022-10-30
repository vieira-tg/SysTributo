package br.com.systributo.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.systributo.model.Crt;
import br.com.systributo.service.NegocioException;
import br.com.systributo.util.jpa.Transactional;

public class Crts implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Crt porNome(String crt) {
		try {
			return manager.createQuery("from Crt where upper(crt) = :crt", Crt.class)
					.setParameter("crt", crt.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Transactional
	public Crt guardar(Crt crt) {
		crt = manager.merge(crt);
		return crt;
	}

	public List<Crt> lista() {
		try {
			return manager.createQuery("from Crt ", Crt.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Crt porId(Long id) {
		return manager.find(Crt.class, id);
	}

	@Transactional
	public void remover(Crt crt) {
		try {
			crt = porId(crt.getId());
			manager.remove(crt);
			manager.flush();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new NegocioException("CRT não pode ser excluído.");

		}

	}

	@SuppressWarnings("unchecked")
	public List<Crt> filtradros(String crt) {
		Session session = manager.unwrap(Session.class);
		// Criteria criteria = session.createCriteria(Cofins.class);
		Criteria crit = session.createCriteria(Crt.class);
		crit.add(Restrictions.like("crt", "%" + crt + "%"));
		List<Crt> results = crit.list();

		for (Crt c : results) {
			System.out.println(c.getCrt());
		}
		return results;
	}

}
