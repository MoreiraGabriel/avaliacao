package br.com.compasso.avaliacao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.compasso.avaliacao.model.Cidade;


@Repository
@Transactional
public interface CidadeRepository extends MongoRepository<Cidade, String> {

	Optional<List<Cidade>> findByNome(String nome);
	
	List<Cidade> findByEstado(String estado);	
	
}
