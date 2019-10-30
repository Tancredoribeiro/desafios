package br.com.famintos.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.famintos.domain.Pessoa;
import br.com.famintos.domain.Voto;
import br.com.famintos.dto.ClassificacaoGeralDTO;
import br.com.famintos.dto.ClassificacaoHojeDTO;
import br.com.famintos.dto.PessoaDTO;
import br.com.famintos.dto.VotoDTO;
import br.com.famintos.exception.PessoaJaVotouException;
import br.com.famintos.repository.VotoRepository;
import br.com.famintos.service.VotoService;
import br.com.famintos.utils.PessoaUtils;

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

	@Override
	public List<ClassificacaoHojeDTO> buscarClassificacaoHoje() {

		List<ClassificacaoHojeDTO> classificacoes = votoRepository.buscarClassificacaoPorData(new Date());

		if (!classificacoes.isEmpty()) {
			Long valorMaisVotado = classificacoes.get(0).getVotos();
			ClassificacaoHojeDTO primeiroMaisVotado = classificacoes.get(0);

			if (classificacoes.stream()
					.noneMatch(c -> !c.getRestaurante().getId().equals(primeiroMaisVotado.getRestaurante().getId())
							&& c.getVotos().equals(valorMaisVotado))) {
				classificacoes.get(0).setVencedor(true);
			}
		}
		return classificacoes;
	}

	@Override
	public List<PessoaDTO> buascarPessoasVotantesPorRestauranteId(@NotNull Long id) {
		List<Pessoa> pessoas = votoRepository.buascarPessoasVotantesPorRestauranteId(id);
		return pessoas.stream().map(p -> PessoaUtils.montarPessoaDTO(p)).collect(Collectors.toList());
	}

	@Override
	public List<ClassificacaoGeralDTO> buscarClassificacaoGeral() {
		List<ClassificacaoGeralDTO> classificacoes = votoRepository.buscarClassificacaoGeral();

		if (!classificacoes.isEmpty()) {
			Long valorMaisVotado = classificacoes.get(0).getVotos();
			ClassificacaoGeralDTO primeiroMaisVotado = classificacoes.get(0);

			if (classificacoes.stream()
					.noneMatch(c -> !c.getIdRestaurante().equals(primeiroMaisVotado.getIdRestaurante())
							&& c.getVotos().equals(valorMaisVotado))) {
				classificacoes.get(0).setVencedor(true);
			}
		}
		return classificacoes;
	}

}
