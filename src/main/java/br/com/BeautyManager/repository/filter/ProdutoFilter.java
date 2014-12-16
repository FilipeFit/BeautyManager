package br.com.BeautyManager.repository.filter;

import java.io.Serializable;

import br.com.BeautyManager.validation.SKU;

public class ProdutoFilter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@SKU
	private String SKU;
	private String Nome;

	public String getSKU() {
		return SKU;
	}

	public void setSKU(String sKU) {
		this.SKU = sKU == null ? null  : sKU.toUpperCase();
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

}
