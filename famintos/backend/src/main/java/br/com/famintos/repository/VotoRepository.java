package br.com.famintos.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.famintos.domain.Pessoa;
import br.com.famintos.domain.Voto;
import br.com.famintos.dto.ClassificacaoGeralDTO;
import br.com.famintos.dto.ClassificacaoHojeDTO;

public interface VotoRepository extends JpaRepository<Voto, Long> {

	Optional<Voto> findByPessoaAndData(@Param("passoa") Pessoa pessoa, @Param("data") Date data);

	@Query("select new br.com.famintos.dto.ClassificacaoHojeDTO(r, count(*) as votos)  from Voto v inner join v.restaurante r "
			+ " where v.data = :data group by r, v.data order by votos desc, r.nome asc ")
	List<ClassificacaoHojeDTO> buscarClassificacaoPorData(@Param("data") Date data);
	
	@Query("select new br.com.famintos.dto.ClassificacaoGeralDTO(r.id, r.nome, count(*) as votos, v.data)  from Voto v inner join v.restaurante r "
			+ " group by r, v.data order by votos desc, r.nome asc ")
	List<ClassificacaoGeralDTO> buscarClassificacaoGeral();

	@Query("select p from Voto v inner join v.restaurante r inner join v.pessoa p where r.id = :id")
	List<Pessoa> buascarPessoasVotantesPorRestauranteId(@Param("id") Long id);
}
