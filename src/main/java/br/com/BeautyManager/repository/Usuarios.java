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
import br.com.BeautyManager.model.Usuario;
import br.com.BeautyManager.repository.filter.UsuarioFilter;
import br.com.BeautyManager.util.jpa.Transactional;

public class Usuarios implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	// Método que salva de fato o usuário na base de dados
	public Usuario guardar(Usuario usuario) {
		return usuario = manager.merge(usuario);
	}

	// Método que busca o usuário por Email
	public Usuario porEmail(String email) {

		// Busco o Usuario a partir de seu email
		try {
			return manager
					.createQuery("from Usuario where upper(email) = :email",
							Usuario.class)
					.setParameter("email", email.toUpperCase())
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> filtrados(UsuarioFilter filtro) {
		// Desempacota o Session do manager dentro da minha variavel
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Usuario.class);
		if (StringUtils.isNotBlank(filtro.getNome())) {
			// MatchMode age como o like do sql
			criteria.add(Restrictions.ilike("nome", filtro.getNome(),
					MatchMode.ANYWHERE));
		}

		// Retorno os resultados do criteria ordenados por nome
		return criteria.addOrder(Order.asc("nome")).list();

	}

	public Usuario porId(Long id) {
		// Busco um produto a partir de um id
		return manager.find(Usuario.class, id);
	}

	@Transactional
	public void remover(Usuario usuario) {
		try {
			usuario = porId(usuario.getId());
			// Vai ser marcado para ser removido
			manager.remove(usuario);
			// Neste momento o registro vai ser excluído
			manager.flush();
		} catch (PersistenceException ex) {
			throw new NegocioException("Usuário não pode ser excluído");
		}

	}

}
