package br.com.BeautyManager.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.BeautyManager.bean.service.NegocioException;
import br.com.BeautyManager.model.Produto;
import br.com.BeautyManager.repository.filter.ProdutoFilter;
import br.com.BeautyManager.util.jpa.Transactional;

public class Produtos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Produto guardar(Produto produto) {

		return produto = manager.merge(produto);
	}

	public Produto porSku(String sku) {
		try {
			return manager
					.createQuery("from Produto where upper(sku) = :sku",
							Produto.class)
					.setParameter("sku", sku.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Transactional
	public void remover(Produto produto) {
		try {
			produto = porId(produto.getId());
			// Vai ser marcado para ser removido
			manager.remove(produto);
			// Neste momento o registro vai ser excluído
			manager.flush();
		} catch (PersistenceException ex) {
			throw new NegocioException("Produto não pode ser excluído");
		}

	}

	@SuppressWarnings("unchecked")
	public List<Produto> filtrados(ProdutoFilter filtro) {
		// Desempacota o Session do manager dentro da minha variavel
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Produto.class);
		if (StringUtils.isNotBlank(filtro.getSKU())) {
			criteria.add(Restrictions.eq("sku", filtro.getSKU()));
		}
		if (StringUtils.isNotBlank(filtro.getNome())) {
			// MatchMode age como o like do sql
			criteria.add(Restrictions.ilike("nome", filtro.getNome(),
					MatchMode.ANYWHERE));
		}

		// Retorno os resultados do criteria ordenados por nome
		return criteria.addOrder(Order.asc("nome")).list();

	}

	public Produto porId(Long id) {

		// Busco um produto a partir de um id
		return manager.find(Produto.class, id);
	}

	/**
	 * @param nome do produto a ser pesquisado
	 * @return retorna uma lista de produtos com o nome informado
	 */
	public List<Produto> porNome(String nome) {
		return this.manager.createQuery("from Produto where upper(nome) like :nome",Produto.class)
				.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}
}
