package br.com.BeautyManager.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.BeautyManager.model.Cliente;
import br.com.BeautyManager.model.Endereco;

public class Enderecos implements Serializable {

	private static final long serialVersionUID = 1L;

	// Usando a minha farica de entity Manager com Inject usando meu produtor
	// que tem a anotação producess
	@Inject
	private EntityManager manager;

	/** 
	* Método que retorna um Grupo de usuário especifico passando seu id
	* @param id Chave para buscar um grupo especifico na base de dados 
	*/  
	public Endereco porId(Long id) {
		// Busco a grupo de usuário por ID
		return manager.find(Endereco.class, id);
	}
	
	/**
	 * Busca os endereços de um determinado Cliente
	 * @param user
	 * @return
	 */
	public List<Endereco> buscaEnderecoDe(Cliente cliente){
		return manager.createQuery("from Endereco where ", Endereco.class).getResultList();
	}		

}

