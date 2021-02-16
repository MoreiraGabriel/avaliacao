package br.com.compasso.avaliacao.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.compasso.avaliacao.model.Cidade;


@Repository
@Transactional
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	Optional<List<Cidade>> findByNome(String nome);
	
	@Query(value = "SELECT * FROM cidade c WHERE c.nome = :nome", nativeQuery = true)
	Optional<Cidade> findCidade(@Param("nome") String nome);
	
	List<Cidade> findByEstado(String estado);
	
	
}
