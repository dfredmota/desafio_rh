package desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import desafio.dao.MembroDAO;
import desafio.dao.PessoaDAO;
import desafio.dao.ProjetoDAO;
import desafio.exception.MembroException;
import desafio.exception.StatusException;
import desafio.model.Membro;
import desafio.model.MembroKey;
import desafio.model.Pessoa;
import desafio.model.Projeto;

/**
 * 
 * @author fred
 *
 */
@Service("projetoService")
public class ProjetoService {
	
	private static final String MSG_ERRO_STATUS = "O Projeto Não pode ser excluído com status 'Iniciado' ou 'Em Andamento' ou 'Encerrado' ";

	private static final String MSG_ERRO_MEMBRO = "Só Funcionários podem ser Associado ao Projeto.";
	
	@Autowired
	private ProjetoDAO projetoDAO;
	
	@Autowired
	private PessoaDAO pessoaDAO;
	
	@Autowired
	private MembroDAO membroDao;
	
	@Autowired

	public List<Projeto> findAll(){
		
		return projetoDAO.findAll();
	}
	
	public Projeto getById(Long id) {
		return projetoDAO.getById(id);
	}

	public Projeto salvar(Projeto proj) {
		
		if(proj.getPessoaId() != null)
		proj.setGerente(pessoaDAO.getById(proj.getPessoaId()));
		
		return projetoDAO.save(proj);
	}
	
	public Projeto atualizar(Projeto proj) {
		return projetoDAO.update(proj);
	}
	
	public void deletar(Long id) throws StatusException {
		
		Projeto projDB = projetoDAO.getById(id);
		
		if(projDB.getStatus().equals("iniciado") || projDB.getStatus().equals("em andamento")
				|| projDB.getStatus().equals("encerrado")) {
			
			throw new StatusException(MSG_ERRO_STATUS);
			
		}else {
			 projetoDAO.delete(id);

		}		
		
	}
	
	public void associarMembro(Long projetoId , Long pessoaId) throws MembroException {
		
		Pessoa funcionario = pessoaDAO.getById(pessoaId);
		
		Projeto projeto    = projetoDAO.getById(projetoId);
		
		if(!funcionario.getFuncionario()) {
			
			throw new MembroException(MSG_ERRO_MEMBRO);
		}
		
		MembroKey embbedKey = new MembroKey();
		
		embbedKey.setPessoaId(pessoaId);
		embbedKey.setProjetoId(projetoId);
		
		Membro membro = new Membro();
		
		membro.setPessoa(funcionario);
		membro.setProjeto(projeto);
		
		membroDao.save(membro);
		
	}

}
