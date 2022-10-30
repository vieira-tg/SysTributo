package br.com.systributo.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.systributo.model.Ncm;
import br.com.systributo.model.Produto;
import br.com.systributo.repository.Ncms;
import br.com.systributo.util.jsf.FacesUtil;

@Named
@SessionScoped
public class DetalheNcmBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Ncm ncm;

	private Produto produtoSelecionado;

	@Inject
	private Ncms ncms;

	public Ncm getNcm() {
		return ncm;
	}

	public void setNcm(Ncm ncm) {
		this.ncm = ncm;
	}

	public void busca() {
		if (LoginBean.getUser().getCliente() == null) {
			FacesUtil.addErrorMessage("IMPOSSIVEL DETERMINAR NCM PARA ESTE USUÁRIO");
		} else {

			ncm = ncms.pesquisar(LoginBean.getUser(), produtoSelecionado.getNcm());
			System.out.println(LoginBean.getUser().getCliente().getCrt().getId());
		}
	}

	public DetalheNcmBean() {
		produtoSelecionado = new Produto();
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {

		this.produtoSelecionado = produtoSelecionado;
		busca();
	}

}
