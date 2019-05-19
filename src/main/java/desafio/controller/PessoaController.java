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

import desafio.exception.StatusException;
import desafio.model.Pessoa;
import desafio.service.PessoaService;
import desafio.util.ApiError;

/**
 * 
 * @author fred
 *
 */
@RestController
public class PessoaController {

	@Autowired
	private PessoaService service;
	
	@RequestMapping(value = "/pessoas", method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<List<Pessoa>> listAll() {

		List<Pessoa> todos = service.findAll();

		if (todos.isEmpty()) {
			return new ResponseEntity<List<Pessoa>>(HttpStatus.NO_CONTENT);// You many decide to return
																			// HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Pessoa>>(todos, HttpStatus.OK);
	}

	@RequestMapping(value = "/pessoa", method = RequestMethod.POST)
	@CrossOrigin
	public ResponseEntity<Object> salvar(@RequestBody Pessoa pessoa) {

		pessoa = service.salvar(pessoa);

		return new ResponseEntity<Object>(pessoa, HttpStatus.CREATED);

	}

	@RequestMapping(value = "/pessoa/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> salvar(@PathVariable("id") String id, @RequestBody Pessoa pessoa) {

		Pessoa pessoaDB = service.getById(Long.valueOf(id));

		pessoaDB.setNome(pessoa.getNome());
		pessoaDB.setDataNascimento(pessoa.getDataNascimento());
		pessoaDB.setFuncionario(pessoa.getFuncionario());
		
		service.atualizar(pessoaDB);

		return new ResponseEntity<Object>(pessoaDB, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/pessoa/{id}", method = RequestMethod.DELETE)
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

	
}
