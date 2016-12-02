package com.oi.internet.teste.services.exceptions;

public class RepositoryNaoEncontradaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1869300553614629710L;

	public RepositoryNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public RepositoryNaoEncontradaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
}
