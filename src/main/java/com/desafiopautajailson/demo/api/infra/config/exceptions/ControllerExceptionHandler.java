package com.desafiopautajailson.demo.api.infra.config.exceptions;

import com.desafiopautajailson.demo.api.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ObjetoNaoEncontradoException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjetoNaoEncontradoException e, HttpServletRequest request) {
		log.error("Não encontrado");
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(AssociadoJaVotouNestaPautaException.class)
	public ResponseEntity<StandardError> associadoJaVotou(AssociadoJaVotouNestaPautaException e, HttpServletRequest request) {
		log.error("Associado ja votou");
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.UNAUTHORIZED.value(), "Associado ja votou", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
	}
	
	@ExceptionHandler(AssociadoNaoTemPermissaoVotarNestaPautaException.class)
	public ResponseEntity<StandardError> associadoNaoTemPermissao(AssociadoNaoTemPermissaoVotarNestaPautaException e, HttpServletRequest request) {
		log.error("UNABLE_TO_VOTE: Voto nao permitido");
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(), "UNABLE_TO_VOTE: Voto nao permitido", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}
	
	@ExceptionHandler(SessaoParaVotoEncerradaException.class)
	public ResponseEntity<StandardError> sessaoEncerrada(SessaoParaVotoEncerradaException e, HttpServletRequest request) {
		log.error("Sessão encerrada");
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.METHOD_NOT_ALLOWED.value(), "Sessão encerrada", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(err);
	}
	
	@ExceptionHandler(SessaoParaVotoEmAndamentoException.class)
	public ResponseEntity<StandardError> sessaoAberta(SessaoParaVotoEmAndamentoException e, HttpServletRequest request) {
		log.error("Sessão em andamento");
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.METHOD_NOT_ALLOWED.value(), "Sessão em andamento", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(err);
	}

}
