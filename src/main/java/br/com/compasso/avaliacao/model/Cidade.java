package br.com.compasso.avaliacao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.compasso.avaliacao.model.dto.request.CidadeRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("cidades")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cidade {

	@Id
	private String id;
	private String nome;
	private String estado;

	public Cidade(CidadeRequest request) {
		this.nome = request.getNome();
		this.estado = request.getEstado().toUpperCase();
	}

}