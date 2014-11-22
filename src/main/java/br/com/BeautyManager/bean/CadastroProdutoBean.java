package br.com.BeautyManager.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("cadastroProdutoBean")
@RequestScoped
public class CadastroProdutoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	public void salvar() {
		throw new RuntimeException("Teste!!");
	}


}
