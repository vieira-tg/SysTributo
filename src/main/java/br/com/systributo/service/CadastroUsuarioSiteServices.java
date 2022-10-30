package br.com.systributo.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.Transient;

import br.com.systributo.model.UsuarioSite;
import br.com.systributo.repository.UsuariosSites;

public class CadastroUsuarioSiteServices implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuariosSites usuarios;

	@Transient
	public UsuarioSite salvar(UsuarioSite usuario) {
		UsuarioSite usuarioExistente = usuarios.porNome(usuario.getEmail());

		if (usuarioExistente != null && !usuarioExistente.equals(usuario)) {
			throw new NegocioException("Já existe o usuário informado!");
		}

		return usuarios.guardar(usuario);
	}

}
