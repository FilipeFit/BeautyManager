package br.com.BeautyManager.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.BeautyManager.model.Categoria;

public class Categorias implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// Usando a minha farica de entity Manager com Inject usando meu produtor que tem a anotação producess
	@Inject
	private EntityManager manager;
	
	public Categoria porId(Long id){
		// Busco a categoria por ID
		return manager.find(Categoria.class, id);
	}
	
	public List<Categoria> raizes(){
		return manager.createQuery("from Categoria where categoriaPai is null", Categoria.class).getResultList();
	}
	
	public List<Categoria> subCategoriasDe(Categoria categoriaPai){
		return manager.createQuery("from Categoria where categoriaPai = :raiz", 
				Categoria.class)
				.setParameter("raiz", categoriaPai)
				.getResultList();
	}

}
