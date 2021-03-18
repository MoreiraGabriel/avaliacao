package br.com.compasso.avaliacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.compasso.avaliacao.model.Cidade;
import br.com.compasso.avaliacao.model.Cliente;
import br.com.compasso.avaliacao.model.dto.ClienteDto;
import br.com.compasso.avaliacao.model.dto.request.ClienteRequest;
import br.com.compasso.avaliacao.model.dto.request.NomeRequest;
import br.com.compasso.avaliacao.repository.CidadeRepository;
import br.com.compasso.avaliacao.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public List<ClienteDto> listarTodos(){
		return ClienteDto.converter(repository.findAll());
	}
	
	public Optional<ClienteDto> obterPorId(Long id) {
		
		Optional<Cliente> cliente = repository.findById(id);
		
		return cliente.isPresent() ? Optional.of(new ClienteDto(cliente.get())) : Optional.empty();
	}
	
	public List<ClienteDto> listarPorNome(NomeRequest request) {		
		return ClienteDto.converter(repository.findByNome(request.getNome()));
	}
	
	@Transactional
	public ClienteDto atualizaNome(Long idCliente, String nome) {
		
		Optional<Cliente> optional = repository.findById(idCliente);
		Cliente cliente;
		
		if(optional.isPresent()) {
			cliente = optional.get();
			cliente.setNome(nome);
			return new ClienteDto(repository.save(cliente));
		}
		
		return null;
	}
	
	@Transactional
	public ClienteDto cadastrar(ClienteRequest request) {
		
		Optional<Cidade> cidade = cidadeRepository.findCidade(request.getCidade());
		
		if(cidade.isPresent()) {
			Cliente cliente = new Cliente();
			cliente.setNome(request.getNome());
			cliente.setSexo(request.getSexo());
			cliente.setDataNascimento(request.getDataNascimento());
			cliente.setIdade(request.getIdade());
			cliente.setCidade(cidade.get());	
			
			return new ClienteDto(repository.save(cliente));
		}		
		
		return null;
		
	}
	
	@Transactional
	public Boolean deletar(Long id) {
		
		boolean res = repository.existsById(id);
		
		if(res) {
			repository.deleteById(id);
		}
		
		return res;
	}
}
