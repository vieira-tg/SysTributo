package br.com.systributo.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;





import br.com.systributo.model.UsuarioSite;
import br.com.systributo.repository.UsuariosSites;
import br.com.systributo.util.cdi.CDIServiceLocator;

public class AppUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UsuariosSites usuariosSite = CDIServiceLocator.getBean(UsuariosSites.class);
		UsuarioSite usuarioSite = usuariosSite.porEmail(email);
		
	System.out.println(usuarioSite.getEmail());
	
		UsuarioSistema user = null;
		
		if (usuarioSite != null) {
			user = new UsuarioSistema(usuarioSite, getGrupos(usuarioSite));
		}
		
		return user;
	}

	private Collection<? extends GrantedAuthority> getGrupos(UsuarioSite usuario) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		if(usuario.getCliente()== null){
			authorities.add(new SimpleGrantedAuthority("ADMINISTRADORES"));  //ADMINISTRADORES
		}else{
			authorities.add(new SimpleGrantedAuthority("USUARIOSITE"));
			
		}
		
		return authorities;
	}

}
