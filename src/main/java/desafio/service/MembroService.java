package desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import desafio.dao.MembroDAO;
import desafio.exception.StatusException;
import desafio.model.Membro;

/**
 * 
 * @author fred
 *
 */
@Service("membroService")
public class MembroService {
	
	@Autowired
	private MembroDAO pessoaDAO;

	public List<Membro> findAll(){
		
		return pessoaDAO.findAll();
	}
	
	public Membro getById(Long id) {
		return pessoaDAO.getById(id);
	}

	public Membro salvar(Membro proj) {
		return pessoaDAO.save(proj);
	}
	
	public Membro atualizar(Membro proj) {
		return pessoaDAO.update(proj);
	}
	
	public void deletar(Long id) throws StatusException {
		
	 pessoaDAO.delete(id);	
		
	}

}
