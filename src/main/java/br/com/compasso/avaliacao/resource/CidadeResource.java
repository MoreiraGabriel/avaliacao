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
	
	@GetMapping("listar/{id}")
	public ResponseEntity<CidadeDto> obterPorId(@PathVariable Long id) {
		Optional<CidadeDto> dto = service.obterPorId(id);
		
		return  ResponseEntity.of(dto);
	}
	
	@GetMapping("listar")
	public ResponseEntity<List<CidadeDto>> obterPorId() {
		
		return  ResponseEntity.ok(service.obterTodas());
	}
	
	@GetMapping("listar-por-nome")
	public ResponseEntity<CidadeDto> listarPorNome(@RequestBody String nome) {
		
		return  ResponseEntity.ok(service.listarPorNome(nome));
	}
	
	@GetMapping("listar-por-estado")
	public ResponseEntity<List<CidadeDto>> listarPorEstado(@RequestBody String estado) {
		
		return  ResponseEntity.ok(service.listarPorEstado(estado));
	}
	
	@PostMapping("cadastrar")
	public ResponseEntity<CidadeDto> cadastrar(@RequestBody CidadeRequest request){
		return ResponseEntity.ok(service.cadastrar(request));
	}
	
	@PutMapping("atualizar")
	public ResponseEntity<CidadeDto> atualizar(@RequestBody CidadeRequest request){
		CidadeDto dto = service.atualizar(request);
		
		return dto != null ?  ResponseEntity.ok(dto) 
				: ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("deletar/{id}")
	public ResponseEntity<Boolean> deletar(@PathVariable Long id){
		return ResponseEntity.ok(service.deletar(id));
	}

}
