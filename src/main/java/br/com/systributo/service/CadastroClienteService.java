package br.com.systributo.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.Transient;

import br.com.systributo.model.Cliente;
import br.com.systributo.repository.Clientes;
import br.com.systributo.util.validador.CnpjValidator;

public class CadastroClienteService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private Clientes clientes;

	@Transient
	public Cliente salvar(Cliente cliente) {

		if (CnpjValidator.validaCnpj(cliente.getRegistroReceita()) == false) {
			throw new NegocioException("CNPJ Invalido!");
		}

		Cliente clienteExistente = clientes.porDocumentoReceita(cliente.getRegistroReceita());

		if (clienteExistente != null && !clienteExistente.equals(cliente)) {
			throw new NegocioException("Já existe o cliente informado!");
		}

		return clientes.guardar(cliente);
	}

}
