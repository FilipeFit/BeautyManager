package br.com.BeautyManager.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import br.com.BeautyManager.bean.service.CadastroProdutoService;
import br.com.BeautyManager.model.Categoria;
import br.com.BeautyManager.model.Produto;
import br.com.BeautyManager.repository.Categorias;
import br.com.BeautyManager.util.jsf.FacesUtil;

@Named("cadastroProdutoBean")
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// Injetando o repositório de categorias
	@Inject
	private Categorias categorias;

	@Inject
	private CadastroProdutoService CadastroProdutoService;

	private Produto produto;
	@NotNull
	private Categoria categoriaPai;

	private List<Categoria> categoriaRaizes;
	private List<Categoria> subCategorias;

	public CadastroProdutoBean() {
		limpar();
	}

	public void inicializar() {

		if (FacesUtil.isNotPostBack()) {
			categoriaRaizes = categorias.raizes();

			if (this.categoriaPai != null) {
				carregarSubCategorias();
			}
		}
	}

	public void carregarSubCategorias() {
		subCategorias = categorias.subCategoriasDe(categoriaPai);
	}

	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}

	public void salvar() {
		this.produto = CadastroProdutoService.salvar(produto);
		limpar();
		FacesUtil.addInfoMessage("Produto salvo com sucesso!");
	}

	public List<Categoria> getSubCategorias() {
		return subCategorias;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;

		if (this.produto != null) {
			this.categoriaPai = this.produto.getCategoria().getCategoriaPai();
		}
	}

	public List<Categoria> getCategoriaRaizes() {
		return categoriaRaizes;
	}

	private void limpar() {
		this.produto = new Produto();
		categoriaPai = null;
		subCategorias = new ArrayList<>();
	}
	
	public boolean isEditando(){
		return this.produto.getId() != null;
	}
}
