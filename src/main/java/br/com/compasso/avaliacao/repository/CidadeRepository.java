package br.com.compasso.avaliacao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.compasso.avaliacao.model.Cidade;


@Repository
@Transactional
public interface CidadeRepository extends MongoRepository<Cidade, String> {

	Optional<List<Cidade>> findByNome(String nome);
	
	@Query(value = "SELECT * FROM cidade c WHERE c.nome = :nome")
	Optional<Cidade> findCidade(@Param("nome") String nome);
	
	List<Cidade> findByEstado(String estado);	
	
}
