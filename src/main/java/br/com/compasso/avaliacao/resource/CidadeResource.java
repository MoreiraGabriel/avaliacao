package br.com.compasso.avaliacao.resource;

import java.util.List;
import java.util.Optional;

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

import br.com.compasso.avaliacao.model.dto.CidadeDto;
import br.com.compasso.avaliacao.model.dto.request.CidadeRequest;
import br.com.compasso.avaliacao.model.dto.request.NomeRequest;
import br.com.compasso.avaliacao.service.impl.CidadeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("cidade")
@Api(value = "Resource para cidade.")
public class CidadeResource {
	
	@Autowired
	private CidadeServiceImpl service;
	
	@ApiOperation(value = "Listar cidade por id")
	@GetMapping("listar/{id}")
	public ResponseEntity<CidadeDto> obterPorId(@PathVariable Long id) {
		Optional<CidadeDto> dto = service.obterPorId(id);
		
		return  ResponseEntity.of(dto);
	}
	
	@ApiOperation(value = "Listar todas as cidades")
	@GetMapping("listar")
	public ResponseEntity<List<CidadeDto>> obterPorId() {
		
		return  ResponseEntity.ok(service.obterTodas());
	}
	
	@ApiOperation(value = "Listar cidade por nome")
	@GetMapping("listar-por-nome")
	public ResponseEntity<List<CidadeDto>> listarPorNome(@RequestBody NomeRequest request) {
		
		return  ResponseEntity.of(service.listarPorNome(request));
	}
	
	@ApiOperation(value = "Listar cidade por estado")
	@GetMapping("listar-por-estado")
	public ResponseEntity<List<CidadeDto>> listarPorEstado(@RequestBody NomeRequest request) {
		
		return  ResponseEntity.ok(service.listarPorEstado(request));
	}
	
	@ApiOperation(value = "Criar nova cidade")
	@PostMapping("cadastrar")
	public ResponseEntity<CidadeDto> cadastrar(@RequestBody CidadeRequest request){
		
		CidadeDto dto = service.cadastrar(request);
		return dto != null ? ResponseEntity.status(HttpStatus.CREATED).body(dto)
				: ResponseEntity.unprocessableEntity().build();
	}
	
	@ApiOperation(value = "Atualizar cidade")
	@PutMapping("atualizar")
	public ResponseEntity<CidadeDto> atualizar(@RequestBody CidadeRequest request){
		CidadeDto dto = service.atualizar(request);
		
		return dto != null ?  ResponseEntity.ok(dto) 
				: ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Deletar cidade por id")
	@DeleteMapping("deletar/{id}")
	public ResponseEntity<Boolean> deletar(@PathVariable Long id){
		boolean res = service.deletar(id);
		return res ? ResponseEntity.ok(res) : ResponseEntity.notFound().build();
	}

}
