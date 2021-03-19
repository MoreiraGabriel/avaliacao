package br.com.compasso.avaliacao.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import br.com.compasso.avaliacao.service.impl.ClienteServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("cliente")
@Api(value = "Resource para cliente")
public class ClienteResource {
	
	@Autowired
	private ClienteServiceImpl service;
	
	@ApiOperation(value = "Listar todos os clientes")
	@GetMapping("listar")
	public ResponseEntity<List<ClienteDto>> listarTodos(){
		return ResponseEntity.ok(service.listarTodos());
	}
	
	@ApiOperation(value = "Listar clinete por id")
	@GetMapping("listar/{id}")
	public ResponseEntity<ClienteDto> obterPorId(@PathVariable Long id){
		return ResponseEntity.of(service.obterPorId(id));
	}
	
	@ApiOperation(value = "Listar cliente por id")
	@GetMapping("listar-por-nome/{nome}")
	public ResponseEntity<List<ClienteDto>> obterPorNome(@PathVariable String nome){
		return ResponseEntity.ok(service.listarPorNome(nome));
	}
	
	@ApiOperation(value = "Criar cliente")
	@PostMapping("cadastrar")
	public ResponseEntity<ClienteDto> cadastrar(@RequestBody ClienteRequest request, 
			UriComponentsBuilder builder){
		ClienteDto dto = service.cadastrar(request);
		
		return dto != null ? ResponseEntity.status(HttpStatus.CREATED).body(dto)
				: ResponseEntity.unprocessableEntity().build();
	}
	
	@ApiOperation(value = "Atualizar nome do cliente")
	@PutMapping("atualizar-nome/{id}")
	public ResponseEntity<ClienteDto> atualizarNome(@PathVariable Long id, 
			@RequestBody NomeRequest request){
		ClienteDto dto = service.atualizaNome(id, request);
		
		return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
	}
	
	@ApiOperation(value = "Deletar cliente por id")
	@DeleteMapping("deletar/{id}")
	public ResponseEntity<Boolean> deletar(@PathVariable Long id){
		boolean res = service.deletar(id);
		
		return res ? ResponseEntity.ok(res) : ResponseEntity.notFound().build();
	}

}
