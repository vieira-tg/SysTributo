package br.com.systributo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.systributo.model.Cidade;
import br.com.systributo.model.Cliente;
import br.com.systributo.model.Crt;
import br.com.systributo.model.Uf;
import br.com.systributo.repository.Cidades;
import br.com.systributo.repository.Clientes;
import br.com.systributo.repository.Crts;
import br.com.systributo.service.CadastroClienteService;
import br.com.systributo.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroClienteService cadastroClienteService;
	
	@Inject
	private Cidades cidadesRepository;
	
	@Inject
	private Clientes clientes;

	@Inject
	private Crts crtRepository;

	private List<Crt> crts;

	private Cliente cliente;
	private List<Cidade> cidades;
	private Cidade cidade;

	private List<Uf> ufs;

	private Cliente clienteSelecionado;
	private List<Cliente> filtrados;
	private String filtro;

	public List<Uf> getUfs() {
		return ufs;
	}

	public void setUfs(List<Uf> ufs) {
		this.ufs = ufs;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	private String uf;

	public void inicializar() {

		Uf[] array = Uf.values();
		ufs = Arrays.asList(array);

		if (FacesUtil.isNotPostback()) {

			if (this.uf != null) {
				carregarCidades();

			}
			carregarCrt();

		}
	}

	private void carregarCrt() {
		crts = crtRepository.lista();

	}

	public void carregarCidades() {

		cidades = cidadesRepository.buscaPorEstado(uf);
	}

	public CadastroClienteBean() {
		limpar();

	}

	public void salvar() {
		this.cliente = cadastroClienteService.salvar(this.cliente);

		limpar();
		FacesUtil.addInfoMessage("Cliente salvo com Sucesso!");

	}

	private void limpar() {
		cliente = new Cliente();
		cidades = new ArrayList<Cidade>();
		ufs = new ArrayList<Uf>();
		crts = new ArrayList<Crt>();

	}

	public boolean isEditando() {
		return this.cliente.getId() != 0;
	}

	public void pesquisar() {
		filtrados = clientes.filtradros(filtro);
	}

	public void excluir(ActionEvent evento) {
		clienteSelecionado = (Cliente) evento.getComponent().getAttributes().get("cliente");
		clientes.remover(clienteSelecionado);
		filtrados.remove(clienteSelecionado);

		FacesUtil.addInfoMessage("Cliente Excluido com sucesso!");

	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
		this.uf = cliente.getCidade().getUf().toString();
		this.cidade = cliente.getCidade();
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public List<Cliente> getFiltrados() {
		return filtrados;
	}

	public void setFiltrados(List<Cliente> filtrados) {
		this.filtrados = filtrados;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<Crt> getCrts() {
		return crts;
	}

	public void setCrts(List<Crt> crts) {
		this.crts = crts;
	}
}
