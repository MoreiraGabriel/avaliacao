package br.com.compasso.avaliacao.resource;

import java.util.List;
import java.util.Optional;

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

import br.com.compasso.avaliacao.model.dto.CidadeDto;
import br.com.compasso.avaliacao.model.dto.request.CidadeRequest;
import br.com.compasso.avaliacao.service.CidadeService;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("cidade")
@Api(value = "Resource para cidade.")
public class CidadeResource {
	
	@Autowired
	private CidadeService service;
	
	@GetMapping("{id}")
	public ResponseEntity<CidadeDto> obterPorId(@PathVariable String id) {
		Optional<CidadeDto> dto = service.obterPorId(id);
		
		return  ResponseEntity.of(dto);
	}
	
	@GetMapping
	public ResponseEntity<List<CidadeDto>> obterTodas() {
		
		return  ResponseEntity.ok(service.obterTodas());
	}
	
	@GetMapping("listar-por-nome/{cidade}")
	public ResponseEntity<List<CidadeDto>> listarPorNome(@PathVariable String cidade) {
		
		return  ResponseEntity.ok(service.listarPorNome(cidade));
	}
	
	@GetMapping("listar-por-estado/{estado}")
	public ResponseEntity<List<CidadeDto>> listarPorEstado(@PathVariable String estado) {
		
		return  ResponseEntity.ok(service.listarPorEstado(estado.toUpperCase()));
	}
	
	@PostMapping
	public ResponseEntity<CidadeDto> cadastrar(@RequestBody CidadeRequest request){
		return ResponseEntity.ok(service.cadastrar(request));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<CidadeDto> atualizar(@PathVariable String id, @RequestBody CidadeRequest request){
		CidadeDto dto = service.atualizar(id, request);
		
		return dto != null ?  ResponseEntity.ok(dto) 
				: ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> deletar(@PathVariable String id){
		boolean res = service.deletar(id);
		return res ? ResponseEntity.ok(res) : ResponseEntity.notFound().build();
	}

}
