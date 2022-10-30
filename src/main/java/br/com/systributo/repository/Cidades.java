package br.com.systributo.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.systributo.model.Cidade;

public class Cidades implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	public List<Cidade> buscaPorEstado(String uf) {
		try {
			return manager.createNativeQuery("select * from cidade where uf=\'" + uf.trim()+"\'", 
					Cidade.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Cidade porId(String id) {
		return manager.find(Cidade.class, id);
	}

}
