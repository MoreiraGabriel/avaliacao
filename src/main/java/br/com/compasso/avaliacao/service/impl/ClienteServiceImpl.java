package br.com.compasso.avaliacao.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.avaliacao.model.Cidade;
import br.com.compasso.avaliacao.model.Cliente;
import br.com.compasso.avaliacao.model.dto.ClienteDto;
import br.com.compasso.avaliacao.model.dto.request.ClienteRequest;
import br.com.compasso.avaliacao.model.dto.request.NomeRequest;
import br.com.compasso.avaliacao.repository.CidadeRepository;
import br.com.compasso.avaliacao.repository.ClienteRepository;
import br.com.compasso.avaliacao.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Override
	public List<ClienteDto> listarTodos(){
		return ClienteDto.converter(repository.findAll());
	}
	
	@Override
	public Optional<ClienteDto> obterPorId(Long id) {
		
		Optional<Cliente> cliente = repository.findById(id);
		
		return cliente.isPresent() ? Optional.of(new ClienteDto(cliente.get())) : Optional.empty();
	}
	
	@Override
	public List<ClienteDto> listarPorNome(String nome) {		
		return ClienteDto.converter(repository.findByNome(nome));
	}
	
	@Transactional
	@Override
	public ClienteDto atualizaNome(Long idCliente, NomeRequest request) {
		
		Optional<Cliente> optional = repository.findById(idCliente);
		
		Cliente cliente = null;		
		if(optional.isPresent()) {
			cliente = optional.get();
			cliente.setNome(request.getNome());			
		}
		
		return new ClienteDto(repository.save(cliente));
	}
	
	@Transactional
	@Override
	public ClienteDto cadastrar(ClienteRequest request) {
		
		Optional<Cidade> cidade = cidadeRepository.findCidade(request.getCidade());
		
		Cliente cliente = null;
		if(cidade.isPresent()) {
			cliente = new Cliente();
			cliente.setNome(request.getNome());
			cliente.setSexo(request.getSexo());
			cliente.setDataNascimento(request.getDataNascimento());
			cliente.setIdade(request.getIdade());
			cliente.setCidade(cidade.get());				
		}		
		
		return new ClienteDto(repository.save(cliente));		
	}
	
	@Transactional
	@Override
	public Boolean deletar(Long id) {
		
		boolean res = repository.existsById(id);		
		if(res) {
			repository.deleteById(id);
		}		
		return res;
	}
}
