package desafio.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import desafio.dao.PessoaDAO;
import desafio.exception.StatusException;
import desafio.model.Pessoa;

/**
 * 
 * @author fred
 *
 */
@Service("pessoaService")
public class PessoaService {
	
	@Autowired
	private PessoaDAO pessoaDAO;
	
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

	public List<Pessoa> findAll(){
		
		return pessoaDAO.findAll();
	}
	
	public Pessoa getById(Long id) {
		return pessoaDAO.getById(id);
	}

	public Pessoa salvar(Pessoa proj) {
		return pessoaDAO.save(proj);
	}
	
	public Pessoa atualizar(Pessoa proj) {
		return pessoaDAO.update(proj);
	}
	
	public void deletar(Long id) throws StatusException {
		
	 pessoaDAO.delete(id);	
		
	}

}
