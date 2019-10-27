package br.com.famintos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.famintos.domain.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
Optional<Pessoa> findByUserNameAndSenha(@Param("userName") String userName, @Param("senha")String senha);

}
