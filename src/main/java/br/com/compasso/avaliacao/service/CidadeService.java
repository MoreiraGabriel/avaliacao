package br.com.compasso.avaliacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.avaliacao.model.Cidade;
import br.com.compasso.avaliacao.model.dto.CidadeDto;
import br.com.compasso.avaliacao.model.dto.request.CidadeRequest;
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
	
	public CidadeDto listarPorNome(String nome){
		return new CidadeDto(repository.findByNome(nome));
	}
	
	public CidadeDto cadastrar(CidadeRequest request) {
		return new CidadeDto(repository.save(new Cidade(request)));
	}
	
	public CidadeDto atualizar(CidadeRequest request) {
		
		Optional<Cidade> optional = repository.findById(request.getId());
		Cidade cidade;
		
		if(optional.isPresent()) {
			cidade = optional.get();
			cidade.setNome(request.getNome());
			cidade.setEstado(request.getEstado());
		}else {
			return null;
		}
		
		return new CidadeDto(repository.save(cidade));
	}

	public Boolean deletar(Long id) {
		
		boolean res = repository.existsById(id);
		
		if(res) {
			repository.deleteById(id);
		}

		return res;
	}

	public List<CidadeDto> listarPorEstado(String estado) {
		return CidadeDto.converterDto(repository.findByEstado(estado));
	}
	
}
