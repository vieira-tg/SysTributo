package br.com.systributo.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.Transient;

import br.com.systributo.model.Cofins;
import br.com.systributo.repository.Cofinses;

public class CadastroCofinsService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Cofinses cofinses;

	@Transient
	public Cofins salvar(Cofins cofins) {
		Cofins cofinsExistente = cofinses.porNome(cofins.getCst());

		if (cofinsExistente != null && !cofinsExistente.equals(cofins)) {
			throw new NegocioException("Já existe o COFINS informado!");
		}

		return cofinses.guardar(cofins);
	}

}
