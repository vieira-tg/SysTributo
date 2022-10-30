package br.com.systributo.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.Transient;

import br.com.systributo.model.Ncm;
import br.com.systributo.repository.Ncms;

public class CadastroNcmService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Ncms ncms;

	@Transient
	public Ncm salvar(Ncm ncm) {
		Ncm ncmExistente = ncms.pesquisar(ncm);

		if (ncmExistente != null && !ncmExistente.equals(ncm)) {
			throw new NegocioException("Já existe o NCM informado!");
		}

		return ncms.guardar(ncm);
	}

}
