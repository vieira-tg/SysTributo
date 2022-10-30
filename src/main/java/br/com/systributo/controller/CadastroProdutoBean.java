package br.com.systributo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.systributo.model.Produto;
import br.com.systributo.repository.Produtos;
import br.com.systributo.service.CadastroProdutoService;
import br.com.systributo.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroProdutoService cadastroProdutoService;

	@Inject
	private Produtos produtos;

	private List<Produto> filtrados;
	private String filtro;
	private Produto produto;
	private Produto produtoSelecionado;

	public boolean isEditando() {
		return this.produto.getId() != 0;
	}

	public void pesquisar() {
		filtrados = produtos.filtradros(filtro);
	}

	public void excluir(ActionEvent evento) {
		produtoSelecionado = (Produto) evento.getComponent().getAttributes().get("produto");
		produtos.remover(produtoSelecionado);
		filtrados.remove(produtoSelecionado);

		FacesUtil.addInfoMessage("Cofins Excluido com sucesso!");

	}

	public CadastroProdutoBean() {
		limpar();
	}

	public void salvar() {
		this.produto = cadastroProdutoService.salvar(this.produto);

		limpar();
		FacesUtil.addInfoMessage("Produto salvo com Sucesso!");

	}

	public Produto cadastroPisBean() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	private void limpar() {
		produto = new Produto();
		filtrados = new ArrayList<Produto>();
		filtro = "";
		produtoSelecionado = new Produto();

	}

	public Produto getProduto() {
		return produto;
	}

	public Produtos getProdutos() {
		return produtos;
	}

	public void setProdutos(Produtos produtos) {
		this.produtos = produtos;
	}

	public List<Produto> getFiltrados() {
		return filtrados;
	}

	public void setFiltrados(List<Produto> filtrados) {
		this.filtrados = filtrados;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

}