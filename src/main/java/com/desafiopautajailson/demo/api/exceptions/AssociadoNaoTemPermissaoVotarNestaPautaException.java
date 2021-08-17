package com.desafiopautajailson.demo.api.exceptions;

public class AssociadoNaoTemPermissaoVotarNestaPautaException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AssociadoNaoTemPermissaoVotarNestaPautaException(String msg) {
		super(msg);
	}

	public AssociadoNaoTemPermissaoVotarNestaPautaException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
