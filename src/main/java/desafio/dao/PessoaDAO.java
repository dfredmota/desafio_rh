package desafio.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import desafio.model.Pessoa;

/**
 * 
 * @author fred
 *
 */
@Repository
@Transactional
public class PessoaDAO {
	
	@PersistenceContext
    protected EntityManager em; 
	
	public Pessoa save(final Pessoa entity) {
		Session session = (Session) em.unwrap(Session.class);
		session.persist(entity);
		session.flush();
		return entity;
	}	
	
	@SuppressWarnings("unchecked")
	public List<Pessoa> findAll() {
		return em.createQuery("FROM Pessoa").getResultList();
	}

	public Pessoa update(Pessoa entity) {
		Session session = (Session) em.unwrap(Session.class);
		session.merge(entity);
		session.flush();
		return entity;
	}

	@Transactional
	public Pessoa getById(final Long id) {
		return em.find(Pessoa.class, id);
	}
	
	@Transactional
	public void delete(final Long id) {
		Pessoa  entity =  em.find(Pessoa.class, id);
		em.remove(em.contains(entity) ? entity : em.merge(entity));		
	}

}
