package br.com.compasso.avaliacao.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("cliente")
@Api(value = "Resource para cliente")
public class ClienteResource {
	
	@Autowired
	private ClienteServiceImpl service;
	
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso",  response = List.class ),
			@ApiResponse(code = 404, message = "Não encontrado", response = NotFoundException.class),		
			@ApiResponse(code = 500, message = "Erro interno do servidor", response = Exception.class),
	})
	@GetMapping
	@ApiOperation(value = "Listar todos os clientes.")
	public ResponseEntity<List<ClienteDto>> listarTodos(){
		return ResponseEntity.ok(service.listarTodos());
	}
	
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso",  response = List.class ),
			@ApiResponse(code = 404, message = "Não encontrado", response = NotFoundException.class),		
			@ApiResponse(code = 500, message = "Erro interno do servidor", response = Exception.class),
	})
	@GetMapping("{id}")
	@ApiOperation(value = "Buscar cliente por id")
	public ResponseEntity<ClienteDto> obterPorId(@PathVariable String id){
		return ResponseEntity.of(service.obterPorId(id));
	}
	
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso",  response = List.class ),
			@ApiResponse(code = 404, message = "Não encontrado", response = NotFoundException.class),		
			@ApiResponse(code = 500, message = "Erro interno do servidor", response = Exception.class),
	})
	@GetMapping("listar-por-nome/{nome}")
	@ApiOperation(value = "Buscar cliente por nome")
	public ResponseEntity<List<ClienteDto>> obterPorNome(@PathVariable String nome){
		return ResponseEntity.ok(service.listarPorNome(nome));
	}
	
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso",  response = List.class ),
			@ApiResponse(code = 404, message = "Não encontrado", response = NotFoundException.class),		
			@ApiResponse(code = 500, message = "Erro interno do servidor", response = Exception.class),
	})
	@PostMapping
	@ApiOperation(value = "Criar novo cliente")
	public ResponseEntity<ClienteDto> cadastrar(@RequestBody ClienteRequest request, 
			UriComponentsBuilder builder){
		ClienteDto dto = service.cadastrar(request);
		
		return dto != null ? ResponseEntity.status(201).body(dto)
				: ResponseEntity.unprocessableEntity().build();
	}
	
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso",  response = List.class ),
			@ApiResponse(code = 404, message = "Não encontrado", response = NotFoundException.class),		
			@ApiResponse(code = 500, message = "Erro interno do servidor", response = Exception.class),
	})
	@PutMapping("{id}")
	@ApiOperation(value = "Atualizar nome do cliente")
	public ResponseEntity<ClienteDto> atualizarNome(@PathVariable String id, 
			@RequestBody NomeRequest request){
		ClienteDto dto = service.atualizaNome(id, request);
		
		return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
	}
	
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso",  response = List.class ),
			@ApiResponse(code = 404, message = "Não encontrado", response = NotFoundException.class),		
			@ApiResponse(code = 500, message = "Erro interno do servidor", response = Exception.class),
	})
	@DeleteMapping("{id}")
	@ApiOperation(value = "Deletar cliente")
	public ResponseEntity<Boolean> deletar(@PathVariable String id){
		boolean res = service.deletar(id);
		
		return res ? ResponseEntity.ok(res) : ResponseEntity.notFound().build();
	}

}
