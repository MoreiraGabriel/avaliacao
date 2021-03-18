package br.com.compasso.avaliacao.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
	
	@Id 
	private Long id;
	private String nome;
	private String sexo;
	private LocalDateTime dataNascimento;
	private Long idade;
	private Cidade cidade;
	
	public Cliente(String nome, String sexo, LocalDateTime dataNascimento, Long idade, Cidade cidade) {
		this.nome = nome;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.idade = idade;
		this.cidade = cidade;
	}	
	
}
