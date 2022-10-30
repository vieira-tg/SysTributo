package br.com.systributo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.systributo.model.Crt;
import br.com.systributo.repository.Crts;
import br.com.systributo.service.CadastroCrtService;
import br.com.systributo.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroCrtBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroCrtService cadastroCrtService;

	@Inject
	private Crts crts;

	private Crt crt;
	private List<Crt> filtrados;
	private String filtro;
	private Crt crtSelecionado;

	public CadastroCrtBean() {
		limpar();
	}

	public void salvar() {
		this.crt = cadastroCrtService.salvar(this.crt);

		limpar();
		FacesUtil.addInfoMessage("CRT salvo com Sucesso!");

	}

	private void limpar() {
		crt = new Crt();
		filtrados = new ArrayList<Crt>();
		filtro = "";
	}

	public void excluir(ActionEvent evento) {
		crt = (Crt) evento.getComponent().getAttributes().get("crts");
		crts.remover(crt);
		filtrados.remove(crt);

		FacesUtil.addInfoMessage("Cofins Excluido com sucesso!");
	}

	public boolean isEditando() {
		return this.crt.getId() != 0;
	}

	public void pesquisar() {
		filtrados = crts.filtradros(filtro);
	}

	public Crt getCrt() {
		return crt;
	}

	public void setCrt(Crt crt) {
		this.crt = crt;
	}

	public List<Crt> getFiltrados() {
		return filtrados;
	}

	public void setFiltrados(List<Crt> filtrados) {
		this.filtrados = filtrados;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public Crt getCrtSelecionado() {
		return crtSelecionado;
	}

	public void setCrtSelecionado(Crt crtSelecionado) {
		this.crtSelecionado = crtSelecionado;
	}

}