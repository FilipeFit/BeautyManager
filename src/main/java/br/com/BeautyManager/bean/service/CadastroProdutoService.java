package br.com.BeautyManager.bean.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.BeautyManager.model.Produto;
import br.com.BeautyManager.repository.Produtos;
import br.com.BeautyManager.util.jpa.Transactional;

public class CadastroProdutoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Produtos produtos;
	
	@Transactional
	public Produto salvar(Produto produto){
		Produto produtoExistente = produtos.porSku(produto.getSku());
		
		// Este método grava um novo produto e caso esteja editando ele não manda mensagem de erro
		if (produtoExistente != null && !produtoExistente.equals(produto)){
			throw new NegocioException("Já existe um produto com o SKU informado.");
		}
		
		return produtos.guardar(produto);
	}

}
