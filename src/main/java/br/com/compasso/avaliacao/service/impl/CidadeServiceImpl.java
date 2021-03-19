package br.com.compasso.avaliacao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.avaliacao.model.Cidade;
import br.com.compasso.avaliacao.model.dto.CidadeDto;
import br.com.compasso.avaliacao.model.dto.request.CidadeRequest;
import br.com.compasso.avaliacao.model.dto.request.NomeRequest;
import br.com.compasso.avaliacao.repository.CidadeRepository;
import br.com.compasso.avaliacao.service.CidadeService;

@Service
public class CidadeServiceImpl implements CidadeService{

	@Autowired
	private CidadeRepository repository;
	
	@Override
	public Optional<CidadeDto> obterPorId(Long id) {
		
		Optional<Cidade> cidade = repository.findById(id);
		
		return cidade.isPresent() ? Optional.of(new CidadeDto(cidade.get())) : Optional.empty();
	}
	
	@Override
	public List<CidadeDto> obterTodas(){
		return CidadeDto.converterDto(repository.findAll());
	}
	
	@Override
	public List<CidadeDto>listarPorNome(NomeRequest request){
		Optional<List<Cidade>> cidades = repository.findByNome(request.getNome());
		return cidades.isPresent() ? CidadeDto.converterDto((cidades.get())) : null;
	}
	
	@Override
	public CidadeDto cadastrar(CidadeRequest request) {
		return new CidadeDto(repository.save(new Cidade(request)));
	}
	
	@Override
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

	@Override
	public Boolean deletar(Long id) {
		
		boolean res = repository.existsById(id);
		
		if(res) {
			repository.deleteById(id);
		}

		return res;
	}

	@Override
	public List<CidadeDto> listarPorEstado(NomeRequest request) {
		return CidadeDto.converterDto(repository.findByEstado(request.getNome()));
	}
	
}