package br.com.BeautyManager.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.BeautyManager.bean.service.CadastroUsuarioService;
import br.com.BeautyManager.model.Grupo;
import br.com.BeautyManager.model.Usuario;
import br.com.BeautyManager.repository.GruposUsuarios;
import br.com.BeautyManager.util.jsf.FacesUtil;

@Named("cadastroUsuarioBean")
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private GruposUsuarios gruposUsuarios;
	// Injetando o Service
	@Inject
	private CadastroUsuarioService cadastroUsuarioService;

	private Usuario usuario;
	private Long grupoId;
	private List<Grupo> grupos;
	private Grupo grupoSelecionado;

	public CadastroUsuarioBean() {
		usuario = new Usuario();
	}

	/**
	 * Método que salva um grupo de usuários na base de dados
	 */
	public void salvar() {

		this.usuario = cadastroUsuarioService.salvar(usuario);
		usuario = new Usuario();
		FacesUtil.addInfoMessage("Usuário salvo com sucesso!");
	}

	/**
	 * Método usado pela camada de visão para popular as informações iniciais da
	 * camada de visão
	 */
	public void inicializar() {

		if (FacesUtil.isNotPostBack()) {
			this.grupos = gruposUsuarios.buscaGrupos();
		}
	}

	/**
	 * Método que inclui na lista de grupos um grupo de usuário a partir de um
	 * id
	 */
	public void adicionaGrupo() {
		if ((grupoId != null) && (grupoId != 0)) {
			Grupo grupo = gruposUsuarios.porId(grupoId);
			if (!usuario.verificaGrupo(grupo)) {
				this.usuario.adicionaGrupo(grupo);
			}
		}

	}

	/**
	 * Método que exclui um grupo de usuário de um usuário
	 */
	public void excluiGrupo() {
		System.out.println("Entrou");
		if ((grupoSelecionado.getId() != null) && (grupoSelecionado.getId() != 0)) {
			this.usuario.removeGrupo(grupoSelecionado);
		}
		FacesUtil.addInfoMessage("Grupo " + grupoSelecionado.getNome()
				+ " excluído com sucesso do usuário.");		
	}
	
	/**
	 * Verifica se o produto existe ou não para informar a camada de apresentação se é uma edição ou cadastro
	 * @return Retorna true caso o id do produto seja diferente de nulo ou seja o produto já existe
	 */
	public boolean isEditando(){
		return this.usuario.getId() != null;
	}	
	

	public Grupo getGrupoSelecionado() {
		return grupoSelecionado;
	}

	public void setGrupoSelecionado(Grupo grupoSelecionado) {
		this.grupoSelecionado = grupoSelecionado;
	}

	public Long getGrupoId() {
		return grupoId;
	}

	public void setGrupoId(Long grupoId) {
		this.grupoId = grupoId;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
