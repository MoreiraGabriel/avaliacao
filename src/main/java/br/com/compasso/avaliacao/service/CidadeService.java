package br.com.compasso.avaliacao.service;

import java.util.List;
import java.util.Optional;

import br.com.compasso.avaliacao.model.dto.CidadeDto;
import br.com.compasso.avaliacao.model.dto.request.CidadeRequest;
import br.com.compasso.avaliacao.model.dto.request.NomeRequest;

public interface CidadeService {
	
	Optional<CidadeDto> obterPorId(Long id);
	
	List<CidadeDto> obterTodas();

	List<CidadeDto>listarPorNome(NomeRequest request);
	
	CidadeDto cadastrar(CidadeRequest request);
	
	CidadeDto atualizar(CidadeRequest request);
	
	Boolean deletar(Long id);
	
	List<CidadeDto> listarPorEstado(NomeRequest request);
}
