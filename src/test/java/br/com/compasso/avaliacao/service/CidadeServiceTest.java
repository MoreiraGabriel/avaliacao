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
import org.springframework.test.context.ActiveProfiles;

import br.com.compasso.avaliacao.model.Cidade;
import br.com.compasso.avaliacao.model.dto.CidadeDto;
import br.com.compasso.avaliacao.model.dto.request.CidadeRequest;
import br.com.compasso.avaliacao.repository.CidadeRepository;
import br.com.compasso.avaliacao.service.impl.CidadeServiceImpl;

@SpringBootTest
@ActiveProfiles("teste")
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
		cidade = new Cidade("1", "Leopoldina", "MG");
		lista.add(cidade);
		dtos = CidadeDto.converterDto(lista);
	}
	
	@Test
	public void ObterPorIdTest() {
		when(repository.findById(Mockito.anyString())).thenReturn(Optional.of(cidade));
		
		Optional<CidadeDto> result = service.obterPorId("1");
		
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
		
		List<CidadeDto> result = service.listarPorNome("Leopoldina");
		
		assertEquals(dtos, result);
	}
	
	@Test
	public void CadastrarTest() {
		
		when(repository.insert(cidade)).thenReturn(cidade);
		
		CidadeDto result = service.cadastrar(new CidadeRequest(cidade));
		
		assertEquals(dtos, result);
	}
	
	@Test
	public void AtualizarTest() {
		
		when(repository.save(cidade)).thenReturn(cidade);
		
		CidadeDto result = service.atualizar("1", new CidadeRequest(cidade));
		
		assertEquals(dtos, result);
	}

}
