package br.com.BeautyManager.bean;

import java.io.Serializable;

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

	private Endereco novoEndereco;
	private Endereco enderecoSelecionado;

	public Cliente getCliente() {
		return cliente;
	}

	public CadastroClienteBean() {
		limpar();
	}

	public void inicializar() {
	}

	public void limpar() {
		cliente = new Cliente();
		this.cliente.setTipo(TipoPessoa.FISICA);
		novoEndereco = new Endereco();
	}

	public void salvar() {

		this.cliente = cadastroCLienteService.salvar(cliente);
		cliente = new Cliente();
		FacesUtil.addInfoMessage("Cliente salvo com sucesso!");

	}

	public void adicionarEndereco() {

		if (this.novoEndereco != null) {

			if (this.novoEndereco.getCliente() == null) {
				// Ainda não tenho cliente associado ao endereço então faço um
				// inclusão
				this.novoEndereco.setCliente(cliente);

				this.cliente.getEnderecos().add(this.novoEndereco);

				this.novoEndereco = new Endereco();
			} else {
				// Percorro todos os endereços para ver se tenho um igual
				for (Endereco endereco : this.cliente.getEnderecos()) {
					if (this.novoEndereco.equals(endereco)) {
						int index = this.cliente.getEnderecos().indexOf(
								endereco);
						if (index != -1) {
							this.cliente.getEnderecos()
									.set(index, novoEndereco);
							FacesUtil
									.addInfoMessage("Endereço alterado com sucesso.");
						}
					}
					this.novoEndereco = new Endereco();
				}
			}
		}
	}

	public void removeEndereco() {

		if (enderecoSelecionado.getId() != null) {
			this.getCliente().getEnderecos().remove(enderecoSelecionado);
			FacesUtil.addInfoMessage("Endereço removido com sucesso.");
		}
	}

	public boolean isEditando() {
		return this.cliente.getId() != null;
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

	public Endereco getNovoEndereco() {
		return novoEndereco;
	}

	public void setNovoEndereco(Endereco novoEndereco) {
		this.novoEndereco = novoEndereco;
	}

	public Endereco getEnderecoSelecionado() {
		return enderecoSelecionado;
	}

	public void setEnderecoSelecionado(Endereco enderecoSelecionado) {
		this.enderecoSelecionado = enderecoSelecionado;
	}

}
