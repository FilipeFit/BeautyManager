package br.com.BeautyManager.bean.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.BeautyManager.model.Cliente;
import br.com.BeautyManager.repository.Clientes;
import br.com.BeautyManager.util.jpa.Transactional;

public class CadastroClienteService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Clientes clientes;
	
	/**
	 * Método que persiste um cliente na base de dados usando todas as regras de negocio
	 * @param usuario recebe um usuário para ser salvo na base de dados
	 * @return retorna o usuário saldo na base de dados
	 */
	@Transactional
	public Cliente salvar(Cliente cliente){
		
		// Verifico se o usuário já existe
		Cliente clienteExistente = clientes.porDocumentoReceitaFederal(cliente.getDocumentoReceitaFederal());
		
		// Este método grava um novo cliente e caso esteja editando ele não manda mensagem de erro
		if (clienteExistente != null && !clienteExistente.equals(cliente)){
			throw new NegocioException("Já existe um Cliente com o mesmo CPF/CNPJ");
		}
		
		if(cliente.getEnderecos().isEmpty()){
			throw new NegocioException("É preciso incluir ao menos um Endereço ao Cliente");
		}
		
		return clientes.guardar(cliente);		
	}
}
