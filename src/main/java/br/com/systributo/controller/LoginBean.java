package br.com.systributo.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.systributo.model.UsuarioSite;
import br.com.systributo.repository.UsuariosSites;
import br.com.systributo.service.CadastroUsuarioSiteServices;
import br.com.systributo.util.jsf.FacesUtil;

@Named
@RequestScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletRequest request;

	@Inject
	private HttpServletResponse response;

	@Inject
	private UsuariosSites users;

	@Inject
	private CadastroUsuarioSiteServices cadastroUsuarioSite;

	private String email;
	public static UsuarioSite user;
	private UsuarioSite usuario;
	private String senhaNova;
	private String senhaAtual;

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getSenhaNova() {
		return senhaNova;
	}

	public void setSenhaNova(String senhaNova) {
		this.senhaNova = senhaNova;
	}

	public void preRender() {
		if ("true".equals(request.getParameter("invalid"))) {
			FacesUtil.addErrorMessage("Usuário ou senha inválido!");
		}
	}

	public void login() throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/j_spring_security_check");
		dispatcher.forward(request, response);

		facesContext.responseComplete();
		user = users.porEmail(email);
		usuario = user;

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static UsuarioSite getUser() {
		return user;
	}

	public static void setUser(UsuarioSite user) {
		LoginBean.user = user;
	}

	public void salvar() {

		if (senhaAtual.equals(user.getSenha())) {

			if (!user.equals(usuario)) {
				FacesUtil.addInfoMessage("Usuario não pode ser Editado!");
			} else {
				usuario.setSenha(senhaNova);
				this.usuario = cadastroUsuarioSite.salvar(this.usuario);
				FacesUtil.addInfoMessage("Usuario salvo com Sucesso!");
			}

		} else {
			FacesUtil.addErrorMessage("Senhas não conferem!");
		}

	}

	public UsuarioSite getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioSite usuario) {
		this.usuario = usuario;
	}
}
