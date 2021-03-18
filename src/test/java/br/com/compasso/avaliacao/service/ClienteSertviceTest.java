package br.com.compasso.avaliacao.service;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.compasso.avaliacao.model.Cidade;
import br.com.compasso.avaliacao.model.Cliente;
import br.com.compasso.avaliacao.repository.CidadeRepository;
import br.com.compasso.avaliacao.repository.ClienteRepository;
import br.com.compasso.avaliacao.service.impl.CidadeServiceImpl;
import br.com.compasso.avaliacao.service.impl.ClienteServiceImpl;

@SpringBootTest
@ActiveProfiles("teste")
public class ClienteSertviceTest {
	
	@Mock
	private CidadeRepository cidadeRepository;
	
	@Mock
	private ClienteRepository repository;
	
	@Autowired
	private ClienteServiceImpl service;
	
	private Cliente cliente;
	
	private Cidade cidade;
	
	@BeforeEach
	public void setup() {
		
		cidade = new Cidade("1", "Leopoldina", "MG");
		
		cliente = new Cliente("José", "M", LocalDateTime.now(), 20L, cidade);
	}

}
