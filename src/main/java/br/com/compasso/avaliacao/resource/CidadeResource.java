package br.com.compasso.avaliacao.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.avaliacao.model.dto.CidadeDto;
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

}
