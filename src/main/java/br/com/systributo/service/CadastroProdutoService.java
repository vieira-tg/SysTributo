package br.com.systributo.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.Transient;

import br.com.systributo.model.Produto;
import br.com.systributo.repository.Produtos;

public class CadastroProdutoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Produtos produtos;

	@Transient
	public Produto salvar(Produto produto) {
		Produto produtoExistente = produtos.porSku(produto.getBarras());

		if (produtoExistente != null && !produto.equals(produtoExistente)) {
			throw new NegocioException("Já existe o produto informado!");
		}
		if (produto.getNcm().length() > 8 || produto.getNcm().length() < 8) {
			throw new NegocioException("NCM INVALIDO!");
		}

		return produtos.guardar(produto);
	}

}
