package com.oi.internet.teste.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.oi.internet.teste.domain.DetalhesErro;
import com.oi.internet.teste.services.exceptions.OwnerNaoEncontradaException;
import com.oi.internet.teste.services.exceptions.RepositoryNaoEncontradaException;
import com.oi.internet.teste.services.exceptions.SettingsNaoEncontradaException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(SettingsNaoEncontradaException.class)
	public ResponseEntity<DetalhesErro> handleLivroNaoEncontradoException
							(SettingsNaoEncontradaException e, HttpServletRequest request) {
		
		DetalhesErro erro = new DetalhesErro();
		erro.setMessage("Configuração não existente");
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(RepositoryNaoEncontradaException.class)
	public ResponseEntity<DetalhesErro> handleLivroNaoEncontradoException
							(RepositoryNaoEncontradaException e, HttpServletRequest request) {
		
		DetalhesErro erro = new DetalhesErro();
		erro.setMessage("Repositório não existente");
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(OwnerNaoEncontradaException.class)
	public ResponseEntity<DetalhesErro> handleLivroNaoEncontradoException
							(OwnerNaoEncontradaException e, HttpServletRequest request) {
		
		DetalhesErro erro = new DetalhesErro();
		erro.setMessage("Nenhum owner encontrado");
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<DetalhesErro> handleDataIntegrityViolationException
							(DataIntegrityViolationException e, HttpServletRequest request) {
		
		DetalhesErro erro = new DetalhesErro();
		erro.setMessage("Requisição inválida");
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	/*
	@ExceptionHandler(AutorExistenteException.class)
	public ResponseEntity<DetalhesErro> handleAutorExistenteException
							(AutorExistenteException e, HttpServletRequest request) {
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(409l);
		erro.setTitulo("Autor já existente");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/409");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}
	
	@ExceptionHandler(AutorNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handleAutorNaoEncontradoException
							(AutorNaoEncontradoException e, HttpServletRequest request) {
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("O autor não pôde ser encontrado");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<DetalhesErro> handleDataIntegrityViolationException
							(DataIntegrityViolationException e, HttpServletRequest request) {
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(400l);
		erro.setTitulo("Requisição inválida");
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/400");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	*/
}