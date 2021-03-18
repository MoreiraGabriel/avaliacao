package br.com.compasso.avaliacao.resource;

import java.util.List;
import java.util.Optional;

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

import br.com.compasso.avaliacao.model.dto.CidadeDto;
import br.com.compasso.avaliacao.model.dto.request.CidadeRequest;
import br.com.compasso.avaliacao.service.impl.CidadeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("cidade")
@Api(value = "Resource para cidade.")
public class CidadeResource {
	
	@Autowired
	private CidadeServiceImpl service;
	
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso",  response = CidadeDto.class ),
			@ApiResponse(code = 404, message = "Não encontrado", response = NotFoundException.class),
			@ApiResponse(code = 500, message = "Erro interno do servidor", response = Exception.class),
	})
	@ApiOperation(value = "Buscar cidade por id")
	@GetMapping("{id}")
	public ResponseEntity<CidadeDto> obterPorId(@PathVariable String id) {
		Optional<CidadeDto> dto = service.obterPorId(id);
		
		return  ResponseEntity.of(dto);
	}
	
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso",  response = List.class ),
			@ApiResponse(code = 404, message = "Não encontrado", response = NotFoundException.class),		
			@ApiResponse(code = 500, message = "Erro interno do servidor", response = Exception.class),
	})
	@ApiOperation(value = "Listar todas as cidades")
	@GetMapping
	public ResponseEntity<List<CidadeDto>> obterTodas() {
		
		return  ResponseEntity.ok(service.obterTodas());
	}
	
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso",  response = List.class ),
			@ApiResponse(code = 404, message = "Não encontrado", response = NotFoundException.class),		
			@ApiResponse(code = 500, message = "Erro interno do servidor", response = Exception.class),
	})
	@ApiOperation(value = "Obter cidade por nome")
	@GetMapping("listar-por-nome/{cidade}")
	public ResponseEntity<List<CidadeDto>> listarPorNome(@PathVariable String cidade) {
		
		return  ResponseEntity.ok(service.listarPorNome(cidade));
	}

	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso",  response = List.class ),
			@ApiResponse(code = 404, message = "Não encontrado", response = NotFoundException.class),		
			@ApiResponse(code = 500, message = "Erro interno do servidor", response = Exception.class),
	})
	@ApiOperation(value = "Obter cidades por estado")
	@GetMapping("listar-por-estado/{estado}")
	public ResponseEntity<List<CidadeDto>> listarPorEstado(@PathVariable String estado) {
		
		return  ResponseEntity.ok(service.listarPorEstado(estado.toUpperCase()));
	}

	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso",  response = List.class ),
			@ApiResponse(code = 404, message = "Não encontrado", response = NotFoundException.class),		
			@ApiResponse(code = 500, message = "Erro interno do servidor", response = Exception.class),
	})
	@ApiOperation(value = "Criar nova cidade")
	@PostMapping
	public ResponseEntity<CidadeDto> cadastrar(@RequestBody CidadeRequest request){
		CidadeDto dto = service.cadastrar(request);
		
		return  dto != null ? ResponseEntity.status(201).body(dto)
				: ResponseEntity.unprocessableEntity().build();
	}
	
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso",  response = List.class ),
			@ApiResponse(code = 404, message = "Não encontrado", response = NotFoundException.class),		
			@ApiResponse(code = 500, message = "Erro interno do servidor", response = Exception.class),
	})
	@ApiOperation(value = "Atualizar Cidade")
	@PutMapping("{id}")
	public ResponseEntity<CidadeDto> atualizar(@PathVariable String id, @RequestBody CidadeRequest request){
		CidadeDto dto = service.atualizar(id, request);
		
		return dto != null ?  ResponseEntity.ok(dto) 
				: ResponseEntity.noContent().build();
	}
	
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso",  response = List.class ),
			@ApiResponse(code = 404, message = "Não encontrado", response = NotFoundException.class),		
			@ApiResponse(code = 500, message = "Erro interno do servidor", response = Exception.class),
	})
	@ApiOperation(value = "Deletar cidade por id")
	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> deletar(@PathVariable String id){
		boolean res = service.deletar(id);
		return res ? ResponseEntity.ok(res) : ResponseEntity.notFound().build();
	}

}
