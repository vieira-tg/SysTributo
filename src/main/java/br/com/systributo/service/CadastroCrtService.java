package br.com.systributo.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.Transient;

import br.com.systributo.model.Crt;
import br.com.systributo.repository.Crts;

public class CadastroCrtService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Crts crts;

	@Transient
	public Crt salvar(Crt crt) {
		Crt crtExistente = crts.porNome(crt.getCrt());

		if (crtExistente != null && !crtExistente.equals(crt)) {
			throw new NegocioException("Já existe o CRT informado!");
		}

		return crts.guardar(crt);
	}

}
