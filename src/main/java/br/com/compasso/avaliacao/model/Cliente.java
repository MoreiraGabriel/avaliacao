package br.com.compasso.avaliacao.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Cliente {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String sexo;
	private LocalDateTime dataNascimento;
	private Long idade;
	@ManyToOne
	@JoinColumn(name = "id_cidade")
	private Cidade cidade;
}
