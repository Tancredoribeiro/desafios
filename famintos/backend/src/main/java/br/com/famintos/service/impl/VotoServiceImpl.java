package br.com.famintos.service.impl;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpInc;
import org.springframework.stereotype.Service;

import br.com.famintos.domain.Pessoa;
import br.com.famintos.domain.Voto;
import br.com.famintos.dto.RestauranteDTO;
import br.com.famintos.dto.VotoDTO;
import br.com.famintos.exception.PessoaJaVotouException;
import br.com.famintos.repository.VotoRepository;
import br.com.famintos.service.VotoService;

@Service
public class VotoServiceImpl implements VotoService {

	private VotoRepository votoRepository;

	@Autowired
	public VotoServiceImpl(VotoRepository votoRepository) {
		super();
		this.votoRepository = votoRepository;
	}

	@Override
	public List<Voto> buscarTodos() {
		return votoRepository.findAll();
	}

	@Override
	public VotoDTO votar(VotoDTO dto) {
		Date data = new Date();
		Voto voto = new Voto();
		BeanUtils.copyProperties(dto, voto);
		Optional<Voto> optional = votoRepository.findByPessoaAndData(voto.getPessoa(), data);
		optional.ifPresent(v -> {
			throw new PessoaJaVotouException("Usuário ja votou hoje.");
		});
		voto.setData(data);

		BeanUtils.copyProperties(votoRepository.save(voto), dto);

		return dto;
	}

}
