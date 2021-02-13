package br.com.compasso.avaliacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.avaliacao.model.Cidade;
import br.com.compasso.avaliacao.model.dto.CidadeDto;
import br.com.compasso.avaliacao.repository.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repository;
	
	public Optional<CidadeDto> obterPorId(Long id) {
		
		Optional<Cidade> cidade = repository.findById(id);
		
		return cidade.isPresent() ? Optional.of(new CidadeDto(cidade.get())) : Optional.empty();
	}
	
	public List<CidadeDto> obterTodas(){
		return CidadeDto.converterDto(repository.findAll());
	}
	
}
