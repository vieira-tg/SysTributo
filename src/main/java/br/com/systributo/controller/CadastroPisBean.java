package br.com.systributo.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.systributo.model.Pis;
import br.com.systributo.repository.Pises;
import br.com.systributo.service.CadastroPisService;
import br.com.systributo.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroPisBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroPisService cadastroPisService;

	@Inject
	private Pises pises;

	private Pis pis;
	private List<Pis> filtrados;
	private String filtro;
	private Pis pisSelecionado;

	public CadastroPisBean() {
		limpar();
	}

	public void salvar() {
		this.pis = cadastroPisService.salvar(this.pis);

		limpar();
		FacesUtil.addInfoMessage("PIS salvo com Sucesso!");

	}

	private void limpar() {
		pis = new Pis();

	}

	public void pesquisar() {
		filtrados = pises.filtradros(filtro);

	}

	public void excluir(ActionEvent evento) {
		pisSelecionado = (Pis) evento.getComponent().getAttributes().get("pises");
		pises.remover(pisSelecionado);
		filtrados.remove(pisSelecionado);

		FacesUtil.addInfoMessage("Cofins Excluido com sucesso!");
	}

	public boolean isEditando() {
		return this.pis.getId() != 0;
	}

	public Pis getPis() {
		return pis;
	}

	public void setPis(Pis pis) {
		this.pis = pis;
	}

	public List<Pis> getFiltrados() {
		return filtrados;
	}

	public void setFiltrados(List<Pis> filtrados) {
		this.filtrados = filtrados;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public Pis getPisSelecionado() {
		return pisSelecionado;
	}

	public void setPisSelecionado(Pis pisSelecionado) {
		this.pisSelecionado = pisSelecionado;
	}
}