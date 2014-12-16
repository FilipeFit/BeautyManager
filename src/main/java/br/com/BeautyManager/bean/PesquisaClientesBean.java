package br.com.BeautyManager.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.BeautyManager.model.Cliente;
import br.com.BeautyManager.repository.Clientes;
import br.com.BeautyManager.repository.filter.ClienteFilter;
import br.com.BeautyManager.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaClientesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Clientes clientes;

	private ClienteFilter filtro;
	private List<Cliente> clientesFiltrados;
	private Cliente clienteSelecionado;

	public PesquisaClientesBean() {
		filtro = new ClienteFilter();
	}

	
	public void excluir() {
		// Removendo o produto
		clientes.remover(clienteSelecionado);
		// Removendo o produto excluído da pesquisa
		clientesFiltrados.remove(clienteSelecionado);

		FacesUtil.addInfoMessage("Cliente" + clienteSelecionado.getNome()
				+ " excluído com sucesso.");
	}	
	
	public void pesquisar() {
		clientesFiltrados = clientes.filtrados(filtro);
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public Clientes getClientes() {
		return clientes;
	}

	public void setClientes(Clientes clientes) {
		this.clientes = clientes;
	}

	public ClienteFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(ClienteFilter filtro) {
		this.filtro = filtro;
	}

	public List<Cliente> getClientesFiltrados() {
		return clientesFiltrados;
	}

	public void setClientesFiltrados(List<Cliente> clientesFiltrados) {
		this.clientesFiltrados = clientesFiltrados;
	}

}
