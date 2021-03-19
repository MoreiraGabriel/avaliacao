package br.com.compasso.avaliacao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.compasso.avaliacao.model.Cidade;
import br.com.compasso.avaliacao.model.dto.CidadeDto;
import br.com.compasso.avaliacao.model.dto.request.CidadeRequest;
import br.com.compasso.avaliacao.repository.CidadeRepository;
import br.com.compasso.avaliacao.service.CidadeService;

@Service
public class CidadeServiceImpl implements CidadeService{

	@Autowired
	private CidadeRepository repository;
	
	@Override
	public Optional<CidadeDto> obterPorId(String id) {
		
		Optional<Cidade> cidade = repository.findById(id);
		
		return cidade.isPresent() ? Optional.of(new CidadeDto(cidade.get())) : Optional.empty();
	}
	
	@Override
	public List<CidadeDto> obterTodas(){
		List<CidadeDto> lista = CidadeDto.converterDto(repository.findAll());
		return lista;
	}
	
	@Override
	public List<CidadeDto>listarPorNome(String request){
		Optional<List<Cidade>> cidades = repository.findByNome(request);
		return cidades.isPresent() ? CidadeDto.converterDto((cidades.get())) : null;
	}
	
	@Transactional
	@Override
	public CidadeDto cadastrar(CidadeRequest request) {
		Cidade cidade = repository.save(new Cidade(request));
		return new CidadeDto(cidade);
	}
	
	@Transactional
	@Override
	public CidadeDto atualizar(String id, CidadeRequest request) {
		
		Optional<Cidade> optional = repository.findById(id);
		Cidade cidade = null;
		
		if(optional.isPresent()) {
			cidade = optional.get();
			cidade.setNome(request.getNome());
			cidade.setEstado(request.getEstado().toUpperCase());
		}
		
		return new CidadeDto(repository.save(cidade));
	}

	@Transactional
	@Override
	public Boolean deletar(String id) {
		
		boolean res = repository.existsById(id);
		
		if(res) {
			repository.deleteById(id);
		}

		return res;
	}

	@Override
	public List<CidadeDto> listarPorEstado(String request) {
		return CidadeDto.converterDto(repository.findByEstado(request));
	}
	
}
