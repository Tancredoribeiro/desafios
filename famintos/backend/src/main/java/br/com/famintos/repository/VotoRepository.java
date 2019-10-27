package br.com.famintos.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import br.com.famintos.domain.Pessoa;
import br.com.famintos.domain.Voto;

public interface VotoRepository extends JpaRepository<Voto, Long> {

	Optional<Voto> findByPessoaAndData(@Param("passoa") Pessoa pessoa, @Param("data") Date data);
}
