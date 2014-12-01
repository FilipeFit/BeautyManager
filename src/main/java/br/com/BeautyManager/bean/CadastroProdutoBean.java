package br.com.BeautyManager.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.BeautyManager.model.Categoria;
import br.com.BeautyManager.model.Produto;
import br.com.BeautyManager.repository.Categorias;

@Named("cadastroProdutoBean")
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// Injetando o 
	@Inject
	private Categorias categorias;
	
	private Produto produto;

	private List<Categoria> categoriaRaizes;

	public CadastroProdutoBean() {
		produto = new Produto();
	}

	public void inicializar() {
		
		System.out.println("Inicializando as categorias");
		categoriaRaizes = categorias.raizes();

	}

	public void salvar() {

	}

	public Produto getProduto() {
		return produto;
	}

	public List<Categoria> getCategoriaRaizes() {
		return categoriaRaizes;
	}

}
