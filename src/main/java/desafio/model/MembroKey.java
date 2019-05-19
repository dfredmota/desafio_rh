package desafio.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MembroKey implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5737918097522601919L;

	@Column(name="idprojeto")
	private Long projetoId;
	
	@Column(name="idpessoa")
	private Long pessoaId;

	public Long getProjetoId() {
		return projetoId;
	}

	public void setProjetoId(Long projetoId) {
		this.projetoId = projetoId;
	}

	public Long getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(Long pessoaId) {
		this.pessoaId = pessoaId;
	}
	
	

}
