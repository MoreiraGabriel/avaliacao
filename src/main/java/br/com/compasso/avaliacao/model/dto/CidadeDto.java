package br.com.compasso.avaliacao.model.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.compasso.avaliacao.model.Cidade;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CidadeDto {

	private Long id;
	private String nome;
	private String estado;
	
	public CidadeDto(Cidade cidade){
		this.id = cidade.getId();
		this.nome = cidade.getNome();
		this.estado = cidade.getEstado();		
	}
	
	public static List<CidadeDto> converterDto(List<Cidade> cidades){
		return cidades.stream().map(CidadeDto::new).collect(Collectors.toList());
	}
}
