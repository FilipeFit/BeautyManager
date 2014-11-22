package br.com.BeautyManager.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;

import br.com.BeautyManager.bean.service.NegocioException;

@Named("cadastroPedidoBean")
@RequestScoped
public class CadastroPedidoBean {
	private List<Integer> itens;

	public CadastroPedidoBean() {
		itens = new ArrayList<>();
		itens.add(1);
	}

	public List<Integer> getItens() {
		return itens;
	}
	
	public void salvar() {
		throw new NegocioException("Pedido Não pode ser salvo ainda não foi implementado!!");
	}
}
