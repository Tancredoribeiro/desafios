package com.br.tancredo.ecommerce.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		StringBuilder builder = new StringBuilder();
		builder.append(ex.getContentType());
		builder.append("tipo de mídia não é suportado. Os tipos de mídia suportados são: ");
		ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
		return buildResponseEntity(new ErrorMessage("Tipo de mídia não é suportado.", false, builder.toString()),
				HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@ExceptionHandler({javax.validation.ConstraintViolationException.class, IllegalArgumentException.class })
	protected ResponseEntity<Object> handleConstraintViolation(RuntimeException ex) {
		return buildResponseEntity(new ErrorMessage("Erro de validação.", false, ex.getLocalizedMessage()),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({OrdemNaoInformadaException.class})
	protected ResponseEntity<Object> handleCustomConstraintViolation(RuntimeException ex) {
		return buildResponseEntity(new ErrorMessage("Erro de validação.", false, ex.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return buildResponseEntity(new ErrorMessage("Solicitação JSON malformada", false, ex.getLocalizedMessage()),
				HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return buildResponseEntity(
				new ErrorMessage(String.format("Não foi possível encontrar o método % s para o URL %s",
						ex.getHttpMethod(), ex.getRequestURL()), false, ex.getLocalizedMessage()),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ClienteNaoEncontradoException.class, ProdutoNaoEncontradoException.class })
	protected ResponseEntity<Object> handleEntityNotFound(ClienteNaoEncontradoException ex) {
		return buildResponseEntity(new ErrorMessage("Registro não encontrado.", false, ex.getMessage()),
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
			WebRequest request) {
		return buildResponseEntity(new ErrorMessage("Erro no banco de dados.", false, ex.getLocalizedMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		String menssagem = String.format("O parâmetro '%s' do valor '%s' não pôde ser convertido para o tipo '%s'",
				ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());
		return buildResponseEntity(new ErrorMessage(menssagem, false, ex.getLocalizedMessage()),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(javax.persistence.EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(javax.persistence.EntityNotFoundException ex) {
		return buildResponseEntity(new ErrorMessage("Registro não encontrado.", false, ex.getLocalizedMessage()),
				HttpStatus.NOT_FOUND);
	}
	
	
	

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errosList = new ArrayList<>();
		StringBuilder erros = new StringBuilder();
		erros.append("erros: ");
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String errorMessage = error.getDefaultMessage();
			errosList.add(errorMessage);
		});
		erros.append(errosList.toString());
		return buildResponseEntity(new ErrorMessage("Erro de validação.", false, erros.toString()),
				HttpStatus.BAD_REQUEST);
	}

	private ResponseEntity<Object> buildResponseEntity(ErrorMessage errorMessage, HttpStatus status) {
		return new ResponseEntity<>(errorMessage, status);
	}
}
