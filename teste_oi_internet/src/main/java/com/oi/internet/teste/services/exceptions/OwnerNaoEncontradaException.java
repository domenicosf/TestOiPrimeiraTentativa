package com.oi.internet.teste.services.exceptions;

public class OwnerNaoEncontradaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1869300553614629710L;

	public OwnerNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public OwnerNaoEncontradaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
}
