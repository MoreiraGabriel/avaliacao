package br.com.compasso.avaliacao.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.compasso.avaliacao.model.dto.ClienteDto;
import br.com.compasso.avaliacao.model.dto.request.ClienteRequest;
import br.com.compasso.avaliacao.model.dto.request.NomeRequest;
import br.com.compasso.avaliacao.service.ClienteService;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("cliente")
@Api(value = "Resource para cliente")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@GetMapping("listar")
	public ResponseEntity<List<ClienteDto>> listarTodos(){
		return ResponseEntity.ok(service.listarTodos());
	}
	
	@GetMapping("listar/{id}")
	public ResponseEntity<ClienteDto> obterPorId(@PathVariable Long id){
		return ResponseEntity.of(service.obterPorId(id));
	}
	
	@GetMapping("listar-por-nome")
	public ResponseEntity<List<ClienteDto>> obterPorNome(@RequestBody NomeRequest request){
		return ResponseEntity.ok(service.listarPorNome(request));
	}
	
	@PostMapping("cadastrar")
	public ResponseEntity<ClienteDto> cadastrar(@RequestBody ClienteRequest request, 
			UriComponentsBuilder builder){
		ClienteDto dto = service.cadastrar(request);
		
		return dto != null ? ResponseEntity.status(201).body(dto)
				: ResponseEntity.unprocessableEntity().build();
	}
	
	@PutMapping("atualizar-nome/{id}")
	public ResponseEntity<ClienteDto> atualizarNome(@PathVariable Long id, 
			@RequestBody NomeRequest request){
		ClienteDto dto = service.atualizaNome(id, request);
		
		return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("deletar/{id}")
	public ResponseEntity<Boolean> deletar(@PathVariable Long id){
		boolean res = service.deletar(id);
		
		return res ? ResponseEntity.ok(res) : ResponseEntity.notFound().build();
	}

}
