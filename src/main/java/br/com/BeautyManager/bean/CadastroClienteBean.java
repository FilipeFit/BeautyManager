package br.com.BeautyManager.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.BeautyManager.bean.service.CadastroClienteService;
import br.com.BeautyManager.model.Cliente;
import br.com.BeautyManager.model.Endereco;
import br.com.BeautyManager.model.TipoPessoa;
import br.com.BeautyManager.util.jsf.FacesUtil;

@Named("cadastroClienteBean")
@ViewScoped
public class CadastroClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroClienteService cadastroCLienteService;

	private Cliente cliente;
	private Endereco enderecoCliente;
	private List<Endereco> enderecosCadastrados;

	public Cliente getCliente() {
		return cliente;
	}

	public CadastroClienteBean() {

		cliente = new Cliente();
		this.cliente.setTipo(TipoPessoa.FISICA);
	}

	/**
	 * Inicializa um endereco quando o dialog de cadastro de endereços é aberto
	 */
	public void iniciaEnderecos() {
		this.enderecoCliente = new Endereco();
	}

	/**
	 * Inclui o endereço cadastrado na lista para ser gravado
	 */
	public void adicionaEndereco() {

		// Verifico se o endereco já possui um cliente associado
		if (enderecoCliente.getCliente().getId() == null) {
			// Endereco não possui cliente portanto é um endereço novo caso
			// contrario é uma edição de endereço
			enderecoCliente.setCliente(cliente);
			cliente.adicionaEndereco(this.enderecoCliente);
		} else {	
			for (Endereco endereco : cliente.getEnderecos()) {
				if (endereco.getId() == enderecoCliente.getId()){
					endereco.atualizaEndereco(enderecoCliente);
				}
			}
		}

	}

	public void salvar() {

		this.cliente = cadastroCLienteService.salvar(cliente);
		cliente = new Cliente();
		FacesUtil.addInfoMessage("Cliente salvo com sucesso!");

	}

	public CadastroClienteService getCadastroCLienteService() {
		return cadastroCLienteService;
	}

	public void setCadastroCLienteService(
			CadastroClienteService cadastroCLienteService) {
		this.cadastroCLienteService = cadastroCLienteService;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEnderecoCliente() {
		return enderecoCliente;
	}

	public void setEnderecoCliente(Endereco enderecoCliente) {
		this.enderecoCliente = enderecoCliente;
	}

	public List<Endereco> getEnderecosCadastrados() {
		return enderecosCadastrados;
	}

	public void setEnderecosCadastrados(List<Endereco> enderecosCadastrados) {
		this.enderecosCadastrados = enderecosCadastrados;
	}

}
