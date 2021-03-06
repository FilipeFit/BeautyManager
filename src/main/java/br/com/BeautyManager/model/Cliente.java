package br.com.BeautyManager.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
// Define uma entidade JPA
@Table(name = "tb_cliente")
// Especifica o nome da tabela
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	// Define a PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// Define que a PK vai ser autogenerated
	private Long id;

	@NotBlank @Size(max = 150)
	@Column(nullable = false, length = 100)
	// Define o campo como Not Null e tamanho de 100
	private String nome;
	
	@NotBlank @Size(max = 255)
	@Column(nullable = false, length = 255)
	private String email;
	
	@NotBlank @Size(max = 14)
	@Column(name = "inscricao", nullable = false, length = 14)
	private String documentoReceitaFederal;
	
	@Enumerated(EnumType.STRING)
	// Define que o ENUM vai passar o texto e não o inteiro
	@Column(nullable = false, length = 10)
	private TipoPessoa tipo;

	// Mapeamento Bidirecional entre a entidade cliente e endereço
	// O atributo Cascade Type All diz que o endereço vai ser persistido
	// juntamente com o Cliente
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Endereco> enderecos = new ArrayList<>();	
	
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

	public String getDocumentoReceitaFederal() {
		return documentoReceitaFederal;
	}

	public void setDocumentoReceitaFederal(String documentoReceitaFederal) {
		this.documentoReceitaFederal = documentoReceitaFederal;
	}

	public TipoPessoa getTipo() {
		return tipo;
	}

	public void setTipo(TipoPessoa tipo) {
		this.tipo = tipo;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
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
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
