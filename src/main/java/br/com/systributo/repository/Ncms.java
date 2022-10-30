package br.com.systributo.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.systributo.model.Ncm;
import br.com.systributo.model.UsuarioSite;
import br.com.systributo.service.NegocioException;
import br.com.systributo.util.jpa.Transactional;

public class Ncms implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@Transactional
	public Ncm guardar(Ncm ncm) {
		ncm = manager.merge(ncm);

		return ncm;
	}

	public Ncm porId(Long id) {
		return manager.find(Ncm.class, id);
	}

	@Transactional
	public void remover(Ncm ncm) {// TODO Auto-generated method stub
		try {
			ncm = porId(ncm.getId());
			manager.remove(ncm);
			manager.flush();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new NegocioException("NCM não pode ser excluído.");

		}

	}

	@SuppressWarnings("unchecked")
	public List<Ncm> filtradros(String txt) {
		Session session = manager.unwrap(Session.class);
		// Criteria criteria = session.createCriteria(Cofins.class);

		Criteria crit = session.createCriteria(Ncm.class);
		Criterion ncm = Restrictions.like("ncm", "%" + txt + "%");
		Criterion descricao = Restrictions.like("decricao", "%" + txt + "%");
		LogicalExpression orExp = Restrictions.or(ncm, descricao);
		crit.add(orExp).addOrder(Order.asc("ncm"));
		List<Ncm> results = crit.list();

		return results;
	}

	public Ncm pesquisar(UsuarioSite user, String txtNcm) {

		Session session = manager.unwrap(Session.class);
		// Criteria criteria = session.createCriteria(Cofins.class);

		Criteria crit = session.createCriteria(Ncm.class);
		Criterion ncm = Restrictions.eq("ncm", txtNcm);
		Criterion uf = Restrictions.eq("uf", user.getCliente().getCidade().getUf());
		Criterion crt = Restrictions.eq("crt", user.getCliente().getCrt());

		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(ncm);
		disjunction.add(uf);
		disjunction.add(crt);

		crit.add(disjunction).addOrder(Order.asc("id"));

		List<Ncm> results = crit.list();

		return results.get(0);

	}

	public Ncm pesquisar(Ncm ncm) {

		Session session = manager.unwrap(Session.class);
		// Criteria criteria = session.createCriteria(Cofins.class);

		Criteria crit = session.createCriteria(Ncm.class);
		Criterion ncmC = Restrictions.eq("ncm", ncm.getNcm());
		Criterion uf = Restrictions.eq("uf", ncm.getUf());
		Criterion crt = Restrictions.eq("crt", ncm.getCrt());

		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(ncmC);
		disjunction.add(uf);
		disjunction.add(crt);

		crit.add(disjunction).addOrder(Order.asc("id"));

		List<Ncm> results = crit.list();

		return results.get(0);

	}

}
