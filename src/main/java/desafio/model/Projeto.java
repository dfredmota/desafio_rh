package desafio.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "projeto")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Projeto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	private String nome;
	
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name="data_inicio")
	private Date dataInicio;
	
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name="data_fim")
	private Date dataFim;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name="data_previsao_fim")
    private Date dataPrevisaoFim;
	
	private String descricao;
	
	private String status;
	
	private String risco;
	
	private BigDecimal orcamento;
	
	@OneToOne
	@JoinColumn(name = "idgerente")
	private Pessoa gerente;
	
	@Transient
	private Long pessoaId;
	
	
	
	

}
