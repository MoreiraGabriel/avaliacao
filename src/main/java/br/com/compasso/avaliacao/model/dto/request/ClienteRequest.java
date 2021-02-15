package br.com.compasso.avaliacao.model.dto.request;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {

	private String nome;
	private String sexo;
	private LocalDateTime dataNascimento;
	private Long idade;
	private String cidade;	
	
}
