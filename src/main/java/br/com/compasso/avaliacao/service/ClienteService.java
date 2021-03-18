package br.com.compasso.avaliacao.service;

import java.util.List;
import java.util.Optional;

import br.com.compasso.avaliacao.model.dto.ClienteDto;
import br.com.compasso.avaliacao.model.dto.request.ClienteRequest;
import br.com.compasso.avaliacao.model.dto.request.NomeRequest;

public interface ClienteService {
	
	List<ClienteDto> listarTodos();
	
	Optional<ClienteDto> obterPorId(String id);
	
	List<ClienteDto> listarPorNome(String nome);
	
	ClienteDto atualizaNome(String id, NomeRequest request);
	
	ClienteDto cadastrar(ClienteRequest request);
	
	Boolean deletar(String id);
}
