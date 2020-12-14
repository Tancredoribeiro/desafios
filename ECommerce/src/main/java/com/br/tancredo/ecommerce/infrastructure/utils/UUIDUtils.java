package com.br.tancredo.ecommerce.infrastructure.utils;

import java.util.UUID;

import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;

public class UUIDUtils {

	
	public static UUID validar(String uuid, String nomeCampo) {
		
		if (StringUtils.isBlank(uuid)) {
			throw new ConstraintViolationException(String.format("O campo %s é obrigatório.", nomeCampo), null);
		}
		
		try {
			return UUID.fromString(uuid);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(String.format("O campo idCleinte está em um formato inválido: %s", uuid));
		}
	}
}
