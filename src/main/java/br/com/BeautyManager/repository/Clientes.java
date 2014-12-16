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
import br.com.BeautyManager.model.Cliente;
import br.com.BeautyManager.repository.filter.ClienteFilter;

public class Clientes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<Cliente> filtrados(ClienteFilter filtro) {
		// Desempacota o Session do manager dentro da minha variavel
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cliente.class);

		if (StringUtils.isNotBlank(filtro.getDocumentoReceitaFederal())) {
			criteria.add(Restrictions.ilike("documentoReceitaFederal",
					filtro.getDocumentoReceitaFederal(), MatchMode.ANYWHERE));

		}
		if (StringUtils.isNotBlank(filtro.getNome())) {
			// MatchMode age como o like do sql
			criteria.add(Restrictions.ilike("nome", filtro.getNome(),
					MatchMode.ANYWHERE));
		}

		// Retorno os resultados do criteria ordenados por nome
		return criteria.addOrder(Order.asc("nome")).list();

	}

	public Cliente porId(Long id) {
		// Busco um produto a partir de um id
		return manager.find(Cliente.class, id);
	}

	/**
	 * Método que consulta um cliente de acordo com usa inscrição
	 * 
	 * @param documentoReceitaFederal
	 * @return retorna o cliente encontrado
	 */
	public Cliente porDocumentoReceitaFederal(String documentoReceitaFederal) {
		// Busco o cliente a partir de sua inscrição
		try {
			return manager
					.createQuery(
							"from Cliente where documentoReceitaFederal = :documentoReceitaFederal",
							Cliente.class)
					.setParameter("documentoReceitaFederal",
							documentoReceitaFederal).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * Método que persiste um cliente na Base de dados
	 * 
	 * @param cliente Cliente a ser persistido na base de dados
	 */
	public Cliente guardar(Cliente cliente) {
		return cliente = manager.merge(cliente);
	}

	/**
	 * Método que remove um cliente da base de dados
	 * @param cliente a ser removido
	 */
	public void remover(Cliente cliente) {
		try {
			cliente = porId(cliente.getId());
			// Vai ser marcado para ser removido
			manager.remove(cliente);
			// Neste momento o registro vai ser excluído
			manager.flush();
		} catch (PersistenceException ex) {
			throw new NegocioException("Cliente não pode ser excluído");
		}

	}

}
