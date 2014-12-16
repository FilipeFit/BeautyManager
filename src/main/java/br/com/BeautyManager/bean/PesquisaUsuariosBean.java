package br.com.BeautyManager.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.BeautyManager.model.Usuario;
import br.com.BeautyManager.repository.Usuarios;
import br.com.BeautyManager.repository.filter.UsuarioFilter;
import br.com.BeautyManager.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaUsuariosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;

	private Usuario usuarioSelecionado;
	private UsuarioFilter filtro;
	private List<Usuario> usuariosFiltrados;

	public void excluir() {

		usuarios.remover(usuarioSelecionado);
		usuariosFiltrados.remove(usuarioSelecionado);

		FacesUtil.addInfoMessage("Usuário " + usuarioSelecionado.getNome()
				+ " excluído com sucesso.");
	}

	public PesquisaUsuariosBean() {
		filtro = new UsuarioFilter();
	}

	public void pesquisar() {
		usuariosFiltrados = usuarios.filtrados(filtro);
	}

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public UsuarioFilter getFiltro() {
		return filtro;
	}

	public List<Usuario> getUsuariosFiltrados() {
		return usuariosFiltrados;
	}

}
