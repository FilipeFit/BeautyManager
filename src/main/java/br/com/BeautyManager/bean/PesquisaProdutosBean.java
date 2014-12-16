package br.com.BeautyManager.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.BeautyManager.model.Produto;
import br.com.BeautyManager.repository.Produtos;
import br.com.BeautyManager.repository.filter.ProdutoFilter;
import br.com.BeautyManager.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaProdutosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Produtos produtos;

	private Produto produtoSelecionado;
	private ProdutoFilter filtro;
	private List<Produto> produtosFiltrados;

	public PesquisaProdutosBean() {
		filtro = new ProdutoFilter();
	}

	public void excluir() {
		// Removendo o produto
		produtos.remover(produtoSelecionado);
		// Removendo o produto excluído da pesquisa
		produtosFiltrados.remove(produtoSelecionado);

		FacesUtil.addInfoMessage("Produto " + produtoSelecionado.getSku()
				+ " excluído com sucesso.");
	}

	public void pesquisar() {
		produtosFiltrados = produtos.filtrados(filtro);
	}

	public ProdutoFilter getFiltro() {
		return filtro;
	}

	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

}
