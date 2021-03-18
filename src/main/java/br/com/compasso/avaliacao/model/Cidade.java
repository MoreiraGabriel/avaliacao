package br.com.compasso.avaliacao.model;

import java.util.ArrayList;
import java.util.List;

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
	private List<Cliente> cliente = new ArrayList<>();

	public Cidade(CidadeRequest request) {
		this.nome = request.getNome();
		this.estado = request.getEstado().toUpperCase();
	}

}