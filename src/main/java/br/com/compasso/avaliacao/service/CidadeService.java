package br.com.compasso.avaliacao.service;

import java.util.List;
import java.util.Optional;

import br.com.compasso.avaliacao.model.dto.CidadeDto;
import br.com.compasso.avaliacao.model.dto.request.CidadeRequest;

public interface CidadeService {

	Optional<CidadeDto> obterPorId(String id);
	
	List<CidadeDto> obterTodas();
	
	List<CidadeDto>listarPorNome(String request);
	
	CidadeDto cadastrar(CidadeRequest request);
	
	CidadeDto atualizar(String id, CidadeRequest request);
	
	Boolean deletar(String id);
	
	List<CidadeDto> listarPorEstado(String request);
}
