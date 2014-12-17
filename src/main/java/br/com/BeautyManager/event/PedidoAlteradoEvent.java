package br.com.BeautyManager.event;

import br.com.BeautyManager.model.Pedido;

public class PedidoAlteradoEvent {

	private Pedido pedido;

	public PedidoAlteradoEvent(Pedido pedido) {
		this.pedido = pedido;
	}

	public Pedido getPedido() {
		return pedido;
	}

}
