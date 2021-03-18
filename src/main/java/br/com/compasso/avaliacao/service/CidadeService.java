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
	
	public Optional<CidadeDto> obterPorId(String id) {
		
		Optional<Cidade> cidade = repository.findById(id);
		
		return cidade.isPresent() ? Optional.of(new CidadeDto(cidade.get())) : Optional.empty();
	}
	
	public List<CidadeDto> obterTodas(){
		List<CidadeDto> lista = CidadeDto.converterDto(repository.findAll());
		return lista;
	}
	
	public List<CidadeDto>listarPorNome(String request){
		Optional<List<Cidade>> cidades = repository.findByNome(request);
		return cidades.isPresent() ? CidadeDto.converterDto((cidades.get())) : null;
	}
	
	public CidadeDto cadastrar(CidadeRequest request) {
		return new CidadeDto(repository.save(new Cidade(request)));
	}
	
	public CidadeDto atualizar(String id, CidadeRequest request) {
		
		Optional<Cidade> optional = repository.findById(id);
		Cidade cidade;
		
		if(optional.isPresent()) {
			cidade = optional.get();
			cidade.setNome(request.getNome());
			cidade.setEstado(request.getEstado().toUpperCase());
		}else {
			return null;
		}
		
		return new CidadeDto(repository.save(cidade));
	}

	public Boolean deletar(String id) {
		
		boolean res = repository.existsById(id);
		
		if(res) {
			repository.deleteById(id);
		}

		return res;
	}

	public List<CidadeDto> listarPorEstado(String request) {
		return CidadeDto.converterDto(repository.findByEstado(request));
	}
	
}
