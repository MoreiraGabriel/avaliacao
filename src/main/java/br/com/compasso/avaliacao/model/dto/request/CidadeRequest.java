package br.com.compasso.avaliacao.model.dto.request;

import java.util.List;
import java.util.stream.Collectors;

import br.com.compasso.avaliacao.model.Cidade;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CidadeRequest {

	private Long id;
	private String nome;
	private String estado;
	
	public CidadeRequest(Cidade cidade){
		this.id = cidade.getId();
		this.nome = cidade.getNome();
		this.estado = cidade.getEstado();		
	}
	
	public static List<CidadeRequest> converterDto(List<Cidade> cidades){
		return cidades.stream().map(CidadeRequest::new).collect(Collectors.toList());
	}
}
