package br.com.compasso.avaliacao.service;

import java.util.List;
import java.util.Optional;

import br.com.compasso.avaliacao.model.dto.CidadeDto;
import br.com.compasso.avaliacao.model.dto.request.CidadeRequest;

public interface CidadeService {
	
	Optional<CidadeDto> obterPorId(Long id);
	
	List<CidadeDto> obterTodas();

	Optional<List<CidadeDto>> listarPorNome(String cidade);
	
	CidadeDto cadastrar(CidadeRequest request);
	
	CidadeDto atualizar(Long id, CidadeRequest request);
	
	Boolean deletar(Long id);
	
	List<CidadeDto> listarPorEstado(String estado);
}
