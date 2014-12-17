package br.com.BeautyManager.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import br.com.BeautyManager.bean.service.NegocioException;
import br.com.BeautyManager.validation.SKU;

@Entity
@Table(name="tb_produto")
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank @Size(max = 150)
	@Column(nullable=false,length=150)
	private String nome;
	
	@NotBlank @SKU
	@Column(nullable=false,length=150,unique=true)
	private String sku;
	// Precision é a quantidade total de casas no banco
	// Scale define a quantidade de casas decimais no banco
	@NotNull
	@Column(nullable=false,precision=10,scale=2)
	private BigDecimal valorUnitario;
	
	// Não posso incluir esse atributo nulo
	@NotNull(message = "é obrigatório") 
	@Min(0) @Max(value =9999, message = "tem um valor muito alto")
	@Column(nullable=false,length=10)
	private Integer quantidadeEstoque;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "categoria_id", nullable = false)
	private Categoria categoria;

	/**
	 * Método que faz a baixa de produtos em estoque
	 * @param quantidade a ser baixada do estoque
	 */
	public void baixaEstoque(Integer quantidade) {
		// Faço o calculo da nova quantidade
		int novaQuantidade = this.getQuantidadeEstoque() - quantidade;
		
		if(novaQuantidade < 0 ){
			throw new NegocioException("Não há disponibilidade de estoque de " 
					+ quantidade + " itens no produto " + this.getSku() + ".");
		}
		
		// Faço o set da nova quantidade
		this.setQuantidadeEstoque(novaQuantidade);
	}	
	
	/**
	 * Método que retorna a quantidade de estoque que foi retirado
	 * @param quantidade a ser devolvida quando o pedido está sendo cancelado
	 */
	public void adicionaEstoque(Integer quantidade) {
		this.setQuantidadeEstoque(getQuantidadeEstoque() + quantidade);
		
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

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
