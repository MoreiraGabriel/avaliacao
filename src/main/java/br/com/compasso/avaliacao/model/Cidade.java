package br.com.compasso.avaliacao.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.compasso.avaliacao.model.dto.request.CidadeRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cidade {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String estado;
	@OneToMany(mappedBy = "cidade")
	private List<Cliente> cliente = new ArrayList<>();
	
	public Cidade(CidadeRequest request) {
		this.id = request.getId();
		this.nome = request.getNome();
		this.estado = request.getEstado();
	}
}
