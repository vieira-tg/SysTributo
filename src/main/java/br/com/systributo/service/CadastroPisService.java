package br.com.systributo.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.Transient;

import br.com.systributo.model.Pis;
import br.com.systributo.repository.Pises;

public class CadastroPisService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Pises pises;

	@Transient
	public Pis salvar(Pis pis) {
		Pis pisExistente = pises.porNome(pis.getCst());

		if (pisExistente != null && !pisExistente.equals(pis)) {
			throw new NegocioException("Já existe o PIS informado!");
		}

		return pises.guardar(pis);
	}

}
