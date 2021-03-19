package br.com.compasso.avaliacao.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
import br.com.compasso.avaliacao.model.dto.CidadeDto;
import br.com.compasso.avaliacao.model.dto.request.CidadeRequest;
import br.com.compasso.avaliacao.repository.CidadeRepository;
import br.com.compasso.avaliacao.service.impl.CidadeServiceImpl;

@SpringBootTest
public class CidadeServiceTest {

	@Mock
	private CidadeRepository repository;
	
	@InjectMocks
	private CidadeServiceImpl service;
	
	private Cidade cidade;
	private List<Cidade> lista = new ArrayList<>();
	private List<CidadeDto> dtos = new ArrayList<>();
	
	@BeforeEach
	public void setup() {		
		cidade = new Cidade(1L, "Leopoldina", "MG");
		lista.add(cidade);
		dtos = CidadeDto.converterDto(lista);
	}
	
	@Test
	public void ObterPorIdTest() {
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(cidade));
		
		Optional<CidadeDto> result = service.obterPorId(1L);
		
		CidadeDto dto = new CidadeDto(cidade);
		
		assertEquals(dto, result.get());
	}
	
	@Test
	public void ObterTodasTest() {
		
		when(repository.findAll()).thenReturn(lista);
		
		List<CidadeDto> cidades = service.obterTodas();
		
		assertEquals(dtos, cidades);
	}
	
	@Test
	public void ObterNomeTest() {
		
		when(repository.findByNome(Mockito.anyString())).thenReturn(Optional.of(lista));
		
		Optional<List<CidadeDto>> result = service.listarPorNome("Leopoldina"); 
		
		assertEquals(dtos, result.get());
	}
	
	@Test
	public void AtualizarTest() {
		
		when(repository.findById(1L)).thenReturn(Optional.of(cidade));
		when(repository.save(cidade)).thenReturn(cidade);
		
		CidadeDto result = service.atualizar(1L, new CidadeRequest(cidade));
		
		assertEquals(dtos.get(0), result);
	}
	
	@Test
	public void DeletarTest() {
		
		when(repository.existsById(Mockito.anyLong())).thenReturn(true);		
		
		Boolean result = service.deletar(1L);
		
		assertEquals(true, result);
	}
	
	@Test
	public void ObterPorEstadoTest() {
		
		when(repository.findByEstado(Mockito.anyString())).thenReturn(lista);
		
		List<CidadeDto> cidades = service.listarPorEstado("MG");
		
		assertEquals(dtos, cidades);
	}
}
