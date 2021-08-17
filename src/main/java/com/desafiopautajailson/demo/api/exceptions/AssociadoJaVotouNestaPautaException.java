package com.desafiopautajailson.demo.api.exceptions;

public class AssociadoJaVotouNestaPautaException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AssociadoJaVotouNestaPautaException(String msg) {
		super(msg);
	}

	public AssociadoJaVotouNestaPautaException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
