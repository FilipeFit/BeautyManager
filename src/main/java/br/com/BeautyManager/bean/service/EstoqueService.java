package br.com.BeautyManager.bean.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.BeautyManager.model.ItemPedido;
import br.com.BeautyManager.model.Pedido;
import br.com.BeautyManager.repository.Pedidos;
import br.com.BeautyManager.util.jpa.Transactional;

public class EstoqueService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Pedidos pedidos;
	
	@Transactional
	public void baixarItensEstoque(Pedido pedido){
	
		// Pego o pedido do bancop
		pedido = this.pedidos.porId(pedido.getId());
		
		for (ItemPedido item : pedido.getItens()) {
			item.getProduto().baixaEstoque(item.getQuantidade());
		}
	}

	public void retornarItensEstoque(Pedido pedido) {
		pedido = this.pedidos.porId(pedido.getId());
		
		for (ItemPedido item : pedido.getItens()) {
			item.getProduto().adicionaEstoque(item.getQuantidade());
		}
		
	}

}
