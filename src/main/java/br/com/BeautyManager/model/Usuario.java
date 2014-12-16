package br.com.BeautyManager.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="tb_usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank @Size(max = 150)
	@Column(nullable=false, length=150)
	private String nome;
	
	@NotBlank @Size(max = 150)
	@Column(nullable=false, length=150,unique=true)
	private String email;
	
	@NotBlank @Size(max = 150)
	@Column(nullable=false, length=30)
	private String senha;
	
	@NotNull
	@ManyToMany
	// Define o relacionamento muitos para muitos e sua tabela de relacionamento
	@JoinTable(name="tb_usuario_grupo", joinColumns=@JoinColumn(name="usuario_id"),
	inverseJoinColumns=@JoinColumn(name="grupo_id")) 
	private List<Grupo> grupos = new ArrayList<>();

	/**
	 * Método que inclui um grupo de usuário
	 * @param grupo grupo a ser incluído no usuário
	 */
	public void adicionaGrupo(Grupo grupo) {
		this.grupos.add(grupo);
		
	}	
	
	/**
	 * Método que reve um grupo da lista de um usuário
	 * @param grupo para ser removido da lista do usuario
	 */
	public void removeGrupo(Grupo grupo){
		this.grupos.remove(grupo);
	}
	
	/** 
	 * Método que verifica dado um grupo se ele já existe na lista para não ficar duplicado
	 * @param grupo
	 * @return retorna true ou false caso o grupo já esteja cadastradp
	 */
	public boolean verificaGrupo(Grupo grupo){
		for (Grupo grupoCadastrado : grupos) {
			if (grupo.getId() == grupoCadastrado.getId()){
				return true;
			}
		}
		return false;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
