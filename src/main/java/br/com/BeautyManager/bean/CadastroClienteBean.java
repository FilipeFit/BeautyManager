package br.com.BeautyManager.bean;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import br.com.BeautyManager.model.Cliente;
import br.com.BeautyManager.model.TipoPessoa;


@Named("cadastroClienteBean")
@ViewScoped
public class CadastroClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cliente cliente;
	
	public Cliente getCliente() {
		return cliente;
	}

	public CadastroClienteBean() {
		
		cliente = new Cliente();
		this.cliente.setTipo(TipoPessoa.FISICA);
	}

	public void salvar(){
		
	}
	//public TipoPessoa getTipoPessoa() {
		
	//	return cliente.getTipo();
   //	}

	//public void setTipoPessoa(String tipoPessoa) {
		
		//this.tipoPessoa = tipoPessoa;
//	}
}
