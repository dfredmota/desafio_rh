package desafio.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import desafio.model.Projeto;

/**
 * 
 * @author fred
 *
 */
@Repository
@Transactional
public class ProjetoDAO {
	
	@PersistenceContext
    protected EntityManager em; 
	
	public Projeto save(final Projeto entity) {
		Session session = (Session) em.unwrap(Session.class);
		session.persist(entity);
		session.flush();
		return entity;
	}	
	
	@SuppressWarnings("unchecked")
	public List<Projeto> findAll() {
		return em.createQuery("FROM Projeto").getResultList();
	}

	public Projeto update(Projeto entity) {
		Session session = (Session) em.unwrap(Session.class);
		session.merge(entity);
		session.flush();
		return entity;
	}

	@Transactional
	public Projeto getById(final Long id) {
		return em.find(Projeto.class, id);
	}
	
	@Transactional
	public void delete(final Long id) {
		Projeto  entity =  em.find(Projeto.class, id);
		em.remove(em.contains(entity) ? entity : em.merge(entity));		
	}

}
