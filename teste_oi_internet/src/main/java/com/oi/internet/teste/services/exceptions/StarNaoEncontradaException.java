package com.oi.internet.teste.services.exceptions;

public class StarNaoEncontradaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 812959320960225753L;

	public StarNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public StarNaoEncontradaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
