package br.com.systributo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.systributo.model.Cofins;
import br.com.systributo.model.Crt;
import br.com.systributo.model.Ncm;
import br.com.systributo.model.Pis;
import br.com.systributo.model.Uf;
import br.com.systributo.repository.Cofinses;
import br.com.systributo.repository.Crts;
import br.com.systributo.repository.Ncms;
import br.com.systributo.repository.Pises;
import br.com.systributo.service.CadastroNcmService;
import br.com.systributo.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroNcmBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroNcmService cadastroNcnService;
	
	@Inject
	private Pises pisRepository;
	
	@Inject
	private Cofinses cofinsRepository;
	
	@Inject
	private Crts crtRepository;
	
	@Inject
	private Ncms ncmRepository;

	private Ncm ncm;
	private List<Pis> pises;
	private List<Cofins> cofinses;
	private List<Crt> crts;

	private Pis pisEntrada;
	private Pis pisSaida;

	private Cofins cofinsEntrada;
	private Cofins cofinsSaida;

	private Crt crt;

	private List<Uf> ufs;
	private String uf;

	private List<Ncm> filtrados;
	private String filtro;
	private Ncm ncmSelecionado;

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

	public void inicializar() {
		Uf[] array = Uf.values();
		this.ufs = Arrays.asList(array);

		if (FacesUtil.isNotPostback()) {

			carregarPis();
			carregarCofins();
			carregarCst();

		}

	}

	private void carregarCst() {
		crts = crtRepository.lista();

	}

	private void carregarCofins() {
		cofinses = cofinsRepository.lista();

	}

	private void carregarPis() {
		pises = pisRepository.lista();

	}

	public CadastroNcmBean() {
		limpar();
	}

	public void salvar() {
		this.ncm = cadastroNcnService.salvar(this.ncm);

		limpar();
		FacesUtil.addInfoMessage("NCM salvo com Sucesso!");

	}

	private void limpar() {
		ncm = new Ncm();
		ufs = new ArrayList<Uf>();
		pises = new ArrayList<Pis>();
		cofinses = new ArrayList<Cofins>();
		crts = new ArrayList<Crt>();
		filtrados = new ArrayList<Ncm>();
	}

	public void pesquisar() {
		filtrados = ncmRepository.filtradros(filtro);

	}

	public void excluir(ActionEvent evento) {
		ncmSelecionado = (Ncm) evento.getComponent().getAttributes().get("ncm");
		ncmRepository.remover(ncmSelecionado);
		filtrados.remove(ncmSelecionado);

		FacesUtil.addInfoMessage("NCM Excluido com sucesso!");
	}

	public boolean isEditando() {
		return this.ncm.getId() != 0;
	}

	public CadastroNcmService getCadastroNcnService() {
		return cadastroNcnService;
	}

	public void setCadastroNcnService(CadastroNcmService cadastroNcnService) {
		this.cadastroNcnService = cadastroNcnService;
	}

	public Pises getPisRepository() {
		return pisRepository;
	}

	public void setPisRepository(Pises pisRepository) {
		this.pisRepository = pisRepository;
	}

	public Cofinses getCofinsRepository() {
		return cofinsRepository;
	}

	public void setCofinsRepository(Cofinses cofinsRepository) {
		this.cofinsRepository = cofinsRepository;
	}

	public Crts getCrtRepository() {
		return crtRepository;
	}

	public void setCrtRepository(Crts crtRepository) {
		this.crtRepository = crtRepository;
	}

	public Ncm getNcm() {
		return ncm;
	}

	public void setNcm(Ncm ncm) {
		this.ncm = ncm;
	}

	public List<Pis> getPises() {
		return pises;
	}

	public void setPises(List<Pis> pises) {
		this.pises = pises;
	}

	public List<Cofins> getCofinses() {
		return cofinses;
	}

	public void setCofinses(List<Cofins> cofinses) {
		this.cofinses = cofinses;
	}

	public List<Crt> getCrts() {
		return crts;
	}

	public void setCrts(List<Crt> crts) {
		this.crts = crts;
	}

	public Pis getPisEntrada() {
		return pisEntrada;
	}

	public void setPisEntrada(Pis pisEntrada) {
		this.pisEntrada = pisEntrada;
	}

	public Pis getPisSaida() {
		return pisSaida;
	}

	public void setPisSaida(Pis pisSaida) {
		this.pisSaida = pisSaida;
	}

	public Cofins getCofinsEntrada() {
		return cofinsEntrada;
	}

	public void setCofinsEntrada(Cofins cofinsEntrada) {
		this.cofinsEntrada = cofinsEntrada;
	}

	public Cofins getCofinsSaida() {
		return cofinsSaida;
	}

	public void setCofinsSaida(Cofins cofinsSaida) {
		this.cofinsSaida = cofinsSaida;
	}

	public Crt getCrt() {
		return crt;
	}

	public void setCrt(Crt crt) {
		this.crt = crt;
	}

	public List<Ncm> getFiltrados() {
		return filtrados;
	}

	public void setFiltrados(List<Ncm> filtrados) {
		this.filtrados = filtrados;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public Ncm getNcmSelecionado() {
		return ncmSelecionado;
	}

	public void setNcmSelecionado(Ncm ncmSelecionado) {
		this.ncmSelecionado = ncmSelecionado;
	}

}
