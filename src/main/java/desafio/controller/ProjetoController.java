package desafio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import desafio.exception.MembroException;
import desafio.exception.StatusException;
import desafio.model.Projeto;
import desafio.service.ProjetoService;
import desafio.util.ApiError;

/**
 * 
 * @author fred
 *
 */
@RestController
public class ProjetoController {

	@Autowired
	private ProjetoService service;
	
	@RequestMapping(value = "/projetos", method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<List<Projeto>> listAll() {

		List<Projeto> todos = service.findAll();

		if (todos.isEmpty()) {
			return new ResponseEntity<List<Projeto>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Projeto>>(todos, HttpStatus.OK);
	}

	@RequestMapping(value = "/projeto", method = RequestMethod.POST)
	@CrossOrigin
	public ResponseEntity<Object> salvar(@RequestBody Projeto projeto) {
				
		projeto = service.salvar(projeto);

		return new ResponseEntity<Object>(projeto, HttpStatus.CREATED);

	}

	@RequestMapping(value = "/projeto/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> atualizar(@PathVariable("id") String id, @RequestBody Projeto projeto) {

		Projeto projDB = service.getById(Long.valueOf(id));

		projDB.setNome(projeto.getNome());
		projDB.setDataInicio(projeto.getDataInicio());
		projDB.setDataFim(projeto.getDataInicio());
		projDB.setOrcamento(projeto.getOrcamento());
		projDB.setRisco(projeto.getRisco());
		projDB.setStatus(projeto.getStatus());
		
		service.atualizar(projDB);

		return new ResponseEntity<Object>(projDB, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/servico/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable("id") String id) {

		List<String> erros = new ArrayList<String>();
		
		try {
		
		service.deletar(Long.valueOf(id));
		
		} catch (StatusException e) {

			erros.add(e.getMessage());

			ApiError apiError = new ApiError(HttpStatus.CONFLICT, erros);

			return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());

		}

		return new ResponseEntity<Object>(new HttpHeaders(), HttpStatus.OK);

	}
	
	@RequestMapping(value = "/associar", method = RequestMethod.POST)
	@CrossOrigin
	public ResponseEntity<Object> associarMembro(@RequestBody String projetoId,String pessoaId) {
		
		List<String> erros = new ArrayList<String>();

		try {
		
		service.associarMembro(new Long(projetoId), new Long(pessoaId));
		
		}catch(MembroException e) {
			
			erros.add(e.getMessage());

			ApiError apiError = new ApiError(HttpStatus.CONFLICT, erros);

			return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
			
		}

		return new ResponseEntity<Object>(new HttpHeaders(), HttpStatus.CREATED);

	}

	
}
