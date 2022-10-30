package br.com.systributo.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.systributo.model.UsuarioSite;

public class UsuarioSistema extends User {

	private static final long serialVersionUID = 1L;
	
	private UsuarioSite usuario;
	
	
	public UsuarioSistema(UsuarioSite usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail(), usuario.getSenha(), authorities);
		this.usuario = usuario;
	}	
}