package br.com.BeautyManager.bean;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import br.com.BeautyManager.model.Usuario;


@Named("cadastroUsuarioBean")
@ViewScoped
public class CadastroUsuarioBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	public CadastroUsuarioBean() {
		usuario = new Usuario();
	}	

	public Usuario getUsuario() {
		return usuario;
	}
	
	public void salvar(){
		
	}

}
