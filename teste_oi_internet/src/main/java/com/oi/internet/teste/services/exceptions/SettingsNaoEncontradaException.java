package com.oi.internet.teste.services.exceptions;

public class SettingsNaoEncontradaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1869300553614629710L;

	public SettingsNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public SettingsNaoEncontradaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
}
