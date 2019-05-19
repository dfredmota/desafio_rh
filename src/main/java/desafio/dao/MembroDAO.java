package desafio.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import desafio.model.Membro;

/**
 * 
 * @author fred
 *
 */
@Repository
@Transactional
public class MembroDAO {
	
	@PersistenceContext
    protected EntityManager em; 
	
	public Membro save(final Membro entity) {
		Session session = (Session) em.unwrap(Session.class);
		session.persist(entity);
		session.flush();
		return entity;
	}	
	
	@SuppressWarnings("unchecked")
	public List<Membro> findAll() {
		return em.createQuery("FROM Membro").getResultList();
	}

	public Membro update(Membro entity) {
		Session session = (Session) em.unwrap(Session.class);
		session.merge(entity);
		session.flush();
		return entity;
	}

	@Transactional
	public Membro getById(final Long id) {
		return em.find(Membro.class, id);
	}
	
	@Transactional
	public void delete(final Long id) {
		Membro  entity =  em.find(Membro.class, id);
		em.remove(em.contains(entity) ? entity : em.merge(entity));		
	}

}
