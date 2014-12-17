package br.com.BeautyManager.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.com.BeautyManager.bean.service.CadastroPedidoService;
import br.com.BeautyManager.event.PedidoAlteradoEvent;
import br.com.BeautyManager.model.Cliente;
import br.com.BeautyManager.model.EnderecoEntrega;
import br.com.BeautyManager.model.FormaPagamento;
import br.com.BeautyManager.model.ItemPedido;
import br.com.BeautyManager.model.Pedido;
import br.com.BeautyManager.model.Produto;
import br.com.BeautyManager.model.Usuario;
import br.com.BeautyManager.repository.Clientes;
import br.com.BeautyManager.repository.Produtos;
import br.com.BeautyManager.repository.Usuarios;
import br.com.BeautyManager.util.jsf.FacesUtil;
import br.com.BeautyManager.validation.SKU;

@Named("cadastroPedidoBean")
@ViewScoped
public class CadastroPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;

	@Inject
	private Clientes clientes;

	@Inject
	private Produtos produtos;

	@Inject
	private CadastroPedidoService cadastroPedidoService;

	@SKU
	private String sku;
	
	// A variável pedido produz uma variável que pode ser usada em outros lugares 
	// neste caso na classe EmissaoPedidoBean
	@Produces
	@PedidoEdicao
	private Pedido pedido;
	private List<Usuario> vendedores;

	private Produto produtoLinhaEditavel;

	public CadastroPedidoBean() {
		limpar();
	}
	
	/**
	 * Método que escuta a alteração de um pedido caso alguma outra classe faça uma alteração o @Observes é obrigatório
	 * @param event Evento que foi disparado em outra classe e está sendo ouvido pelo meu BeanCDI
	 */
	public void pedidoAlterado(@Observes PedidoAlteradoEvent event){
		// Pego o evento do pedido que foi disparado em outra classe
		this.pedido = event.getPedido();
	}

	public void inicializar() {
		if (FacesUtil.isNotPostBack()) {
			this.vendedores = usuarios.vendedores();

			this.pedido.adiconarItemVazio();

			this.recalcularPedido();
		}
	}

	/**
	 * @return Retorna verdadeiro caso o Id do pedido não esteja nulo ou seja o
	 *         pedido já existe e está sendo editado
	 */
	public boolean isEditando() {
		return this.pedido.getId() != null;
	}

	public void recalcularPedido() {
		if (this.pedido != null) {
			this.pedido.recalcularValorTotal();
		}
	}

	private void limpar() {
		pedido = new Pedido();
		pedido.setEnderecoEntrega(new EnderecoEntrega());
	}

	/**
	 * Método que persiste um pedido na base de dados
	 */
	public void salvar() {
		this.pedido.removerItemVazio();
		try {
			this.pedido = this.cadastroPedidoService.salvar(this.pedido);
			FacesUtil.addInfoMessage("Pedido salvo com sucesso!");
		} finally {
			this.pedido.adiconarItemVazio();
		}
	}

	public List<Produto> completarProduto(String nome) {
		return this.produtos.porNome(nome);
	}

	public void carregarProdutoLinhaEditavel() {
		ItemPedido item = this.pedido.getItens().get(0);

		if (this.produtoLinhaEditavel != null) {
			if (this.existeItemComProduto(this.produtoLinhaEditavel)) {
				FacesUtil
						.addErrorMessage("Já existe um item no pedido com o produto informado.");
			} else {
				item.setProduto(this.produtoLinhaEditavel);
				item.setValorUnitario(this.produtoLinhaEditavel
						.getValorUnitario());

				this.pedido.adiconarItemVazio();
				this.produtoLinhaEditavel = null;
				this.sku = null;

				this.pedido.recalcularValorTotal();
			}

		}

	}

	private boolean existeItemComProduto(Produto produto) {
		boolean existeItem = false;

		for (ItemPedido item : this.getPedido().getItens()) {
			if (produto.equals(item.getProduto())) {
				existeItem = true;
				break;
			}
		}

		return existeItem;
	}

	public void carregarProdutoPorSku() {
		if (StringUtils.isNotEmpty(this.sku)) {
			this.produtoLinhaEditavel = this.produtos.porSku(this.sku);
			this.carregarProdutoLinhaEditavel();
		}
	}

	public void atualizarQuantidade(ItemPedido item, int linha) {
		if (item.getQuantidade() < 1) {
			// Nunca removo a primeira linha sempre preciso ter uma linha
			if (linha == 0) {
				item.setQuantidade(1);
			} else {
				this.getPedido().getItens().remove(linha);
			}
		}

		// Recalcula o valor total do Pedido
		this.pedido.recalcularValorTotal();
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Produto getProdutoLinhaEditavel() {
		return produtoLinhaEditavel;
	}

	public void setProdutoLinhaEditavel(Produto produtoLinhaEditavel) {
		this.produtoLinhaEditavel = produtoLinhaEditavel;
	}

	public List<Cliente> completarCliente(String nome) {
		return clientes.porNome(nome);
	}

	public FormaPagamento[] getFormasPagamentos() {
		return FormaPagamento.values();
	}

	public List<Usuario> getVendedores() {
		return vendedores;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
}
