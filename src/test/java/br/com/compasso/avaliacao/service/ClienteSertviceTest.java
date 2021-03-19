package br.com.compasso.avaliacao.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.compasso.avaliacao.model.Cidade;
import br.com.compasso.avaliacao.model.Cliente;
import br.com.compasso.avaliacao.model.dto.ClienteDto;
import br.com.compasso.avaliacao.model.dto.request.ClienteRequest;
import br.com.compasso.avaliacao.model.dto.request.NomeRequest;
import br.com.compasso.avaliacao.repository.CidadeRepository;
import br.com.compasso.avaliacao.repository.ClienteRepository;
import br.com.compasso.avaliacao.service.impl.ClienteServiceImpl;

@SpringBootTest
public class ClienteSertviceTest {
	
	@Mock
	private CidadeRepository cidadeRepository;
	
	@Mock
	private ClienteRepository repository;
	
	@InjectMocks
	private ClienteServiceImpl service;
	
	private Cliente cliente;
	private List<Cliente> listaClientes = new ArrayList<>();
	private List<ClienteDto> listaDtos = new ArrayList<>();
	
	private ClienteDto dto;
	
	private Cidade cidade;
	private List<Cidade> listaCidades = new ArrayList<>();
	
	private ClienteRequest request = new ClienteRequest();
	
	@BeforeEach
	public void setup() {
		
		cidade = new Cidade("1", "Leopoldina", "MG");		
		listaCidades.add(cidade);
		
		cliente = new Cliente("1", "José", "M", LocalDateTime.now(), 20L, cidade);
		cliente.setCidade(cidade);
		listaClientes.add(cliente);
		
		dto = new ClienteDto(cliente);
		
		listaDtos = ClienteDto.converter(listaClientes);
		
		request.setNome("José");
		request.setSexo("M");
		request.setDataNascimento(LocalDateTime.now());
		request.setIdade(24L);
		request.setCidade("Leopoldina");
	}
	
	@Test
	public void listarTodosTest(){
		
		when(repository.findAll()).thenReturn(listaClientes);
		
		List<ClienteDto> result = service.listarTodos();
		
		assertEquals(listaDtos, result);		
	}
	
	@Test
	public void ObterPorIdTest(){
		
		when(repository.findById(Mockito.anyString())).thenReturn(Optional.of(cliente));
		
		Optional<ClienteDto> result = service.obterPorId("1");
		
		assertEquals(dto, result.get());		
	}
	
	@Test
	public void listarPorNomeTest(){
		
		when(repository.findByNome(Mockito.anyString())).thenReturn(listaClientes);
		
		List<ClienteDto> result = service.listarPorNome("José");
		
		assertEquals(listaDtos, result);		
	}

	@Test
	public void atualizaNomeTest(){
		
		NomeRequest nome = new NomeRequest("José");
		
		when(repository.findById(Mockito.anyString())).thenReturn(Optional.of(cliente));
		when(repository.save(Mockito.any())).thenReturn(cliente);
		
		ClienteDto result = service.atualizaNome("1", nome);
		
		assertEquals(dto, result);		
	}
	
	@Test
	public void DeletarTest() {
		
		when(repository.existsById(Mockito.anyString())).thenReturn(true);		
		
		Boolean result = service.deletar("1");
		
		assertEquals(true, result);
	}
}
