package br.com.BeautyManager.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="clienteBean")
@ViewScoped
public class ClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tipoPessoa;

	public ClienteBean() {
		this.tipoPessoa = "F";
	}

	public String getTipoPessoa() {
		System.out.println("GET: " + tipoPessoa);
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		System.out.println("SET: " + tipoPessoa);
		this.tipoPessoa = tipoPessoa;
	}
}
