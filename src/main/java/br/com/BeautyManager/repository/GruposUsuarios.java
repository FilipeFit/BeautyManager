package br.com.BeautyManager.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.BeautyManager.model.Grupo;
import br.com.BeautyManager.model.Usuario;

public class GruposUsuarios implements Serializable {

	private static final long serialVersionUID = 1L;

	// Usando a minha farica de entity Manager com Inject usando meu produtor
	// que tem a anotação producess
	@Inject
	private EntityManager manager;

	/** 
	* Método que retorna um Grupo de usuário especifico passando seu id
	* @param id Chave para buscar um grupo especifico na base de dados 
	*/  
	public Grupo porId(Long id) {
		// Busco a grupo de usuário por ID
		return manager.find(Grupo.class, id);
	}
	
	/** 
	* Método que retorna uma lista de todos os grupos de usuários cadastrados 
	*/  
	public List<Grupo> buscaGrupos(){
		return manager.createQuery("from Grupo", Grupo.class).getResultList();
	}
	
	public List<Grupo> buscaGruposDe(Usuario user){
		return manager.createQuery("from Grupo where ", Grupo.class).getResultList();
	}		

}

