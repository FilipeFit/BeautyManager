package br.com.BeautyManager.bean.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.BeautyManager.model.Usuario;
import br.com.BeautyManager.repository.Usuarios;
import br.com.BeautyManager.util.jpa.Transactional;

public class CadastroUsuarioService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuarios usuarios;
	
	/**
	 * Método que persiste um usuário na base de dados usando todas as regras de negocio
	 * @param usuario recebe um usuário para ser salvo na base de dados
	 * @return retorna o usuário saldo na base de dados
	 */
	@Transactional
	public Usuario salvar(Usuario usuario){
		
		// Verifico se o usuário já existe
		Usuario usuarioExistente = usuarios.porEmail(usuario.getEmail());
		
		// Este método grava um novo usuario e caso esteja editando ele não manda mensagem de erro
		if (usuarioExistente != null && !usuarioExistente.equals(usuario)){
			throw new NegocioException("Já existe um usuário com o E-mail informado");
		}
		
		if(usuario.getGrupos().isEmpty()){
			throw new NegocioException("É preciso incluir ao menos um Grupo ao usuário");
		}
		
		return usuarios.guardar(usuario);		
	}
}
