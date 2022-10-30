package br.com.systributo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.systributo.model.Cofins;
import br.com.systributo.repository.Cofinses;
import br.com.systributo.service.CadastroCofinsService;


import br.com.systributo.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroCofinsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroCofinsService cadastroCofinsService;
	
	@Inject
	private Cofinses cofinses;

	private Cofins cofins;
	private String filtro;
	private List<Cofins> filtrados;
	private Cofins cofinsSelecionado;
	
	public CadastroCofinsBean() {
		limpar();
		filtro = "";
		filtrados = new ArrayList<Cofins>();
		cofinsSelecionado = new Cofins();
	}

	public void salvar() {
		this.cofins = cadastroCofinsService.salvar(this.cofins);

		limpar();
		FacesUtil.addInfoMessage("Cofins salvo com Sucesso!");

	}

	private void limpar() {
		cofins = new Cofins();

	}

	public Cofins getCofins() {
		return cofins;
	}

	public void setCofins(Cofins cofins) {
		this.cofins = cofins;
	}

	public void pesquisar() {
		filtrados =  cofinses.filtradros(filtro);
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<Cofins> getFiltrados() {
		return filtrados;
	}

	public void setFiltrados(List<Cofins> filtrados) {
		this.filtrados = filtrados;
	}
	
	
	public boolean isEditando() {
		return this.cofins.getId() != 0;
	}

	public Cofins getCofinsSelecionado() {		
		return cofinsSelecionado;
	}

	public void setCofinsSelecionado(Cofins cofinsSelecionado) {
		this.cofinsSelecionado = cofinsSelecionado;
	}
	
	public void excluir(ActionEvent evento){
		cofinsSelecionado = (Cofins) evento.getComponent().getAttributes().get("cof");			
		cofinses.remover(cofinsSelecionado);		
		filtrados.remove(cofinsSelecionado);
		
		FacesUtil.addInfoMessage("Cofins Excluido com sucesso!");
	}
}