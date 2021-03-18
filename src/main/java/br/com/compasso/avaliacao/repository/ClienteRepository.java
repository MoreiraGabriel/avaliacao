package br.com.compasso.avaliacao.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.compasso.avaliacao.model.Cliente;

@Repository
@Transactional
public interface ClienteRepository extends MongoRepository<Cliente, Long>{
	
	List<Cliente> findByNome(String nome);
}
