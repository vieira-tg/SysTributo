package br.com.systributo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.systributo.model.Cliente;
import br.com.systributo.model.UsuarioSite;
import br.com.systributo.repository.Clientes;
import br.com.systributo.repository.UsuariosSites;
import br.com.systributo.service.CadastroUsuarioSiteServices;
import br.com.systributo.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioSiteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroUsuarioSiteServices cadastroUsuarioSite;

	@Inject
	private UsuariosSites usuarios;

	@Inject
	private Clientes clientesRepository;

	private UsuarioSite usuario;

	private UsuarioSite usuarioSelecionado;
	private List<UsuarioSite> filtrados;
	private String filtro;

	private List<Cliente> clientes;

	private Cliente clienteSelecionado;

	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			carregarClientes();
		}
	}

	private void carregarClientes() {
		if (clientes == null) {
			clientes = new ArrayList<Cliente>();
		}
		clientes = clientesRepository.buscaClientes();
		for (Cliente c : clientes) {
			System.out.println(c.getNome());
		}

	}

	public CadastroUsuarioSiteBean() {
		limpar();

	}

	public void salvar() {
		this.usuario = cadastroUsuarioSite.salvar(this.usuario);

		limpar();
		FacesUtil.addInfoMessage("Usuario salvo com Sucesso!");

	}

	private void limpar() {
		usuario = new UsuarioSite();
		clienteSelecionado = new Cliente();
		clientes = new ArrayList<Cliente>();
	}

	public boolean isEditando() {
		return this.usuario.getId() != 0;
	}

	public void pesquisar() {
		filtrados = usuarios.filtradros(filtro);
	}

	public void excluir(ActionEvent evento) {
		usuarioSelecionado = (UsuarioSite) evento.getComponent().getAttributes().get("usuario");
		usuarios.remover(usuarioSelecionado);
		filtrados.remove(usuarioSelecionado);

		FacesUtil.addInfoMessage("Usuário Excluido com sucesso!");

	}

	public UsuarioSite getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioSite usuario) {
		this.usuario = usuario;
	}

	public UsuariosSites getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(UsuariosSites usuarios) {
		this.usuarios = usuarios;
	}

	public UsuarioSite getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(UsuarioSite usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public List<UsuarioSite> getFiltrados() {
		return filtrados;
	}

	public void setFiltrados(List<UsuarioSite> filtrados) {
		this.filtrados = filtrados;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
}