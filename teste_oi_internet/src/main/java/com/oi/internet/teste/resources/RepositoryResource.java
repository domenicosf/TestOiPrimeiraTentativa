package com.oi.internet.teste.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.oi.internet.teste.domain.Owner;
import com.oi.internet.teste.domain.Repository;
import com.oi.internet.teste.services.RepositoryService;

@RestController
public class RepositoryResource {
	
	@Autowired
	RepositoryService repositoryService;
	
	@RequestMapping(value = "/repository",method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Repository>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(repositoryService.listarTop10());
	}

	@RequestMapping(value = "/repository",method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> salvar(@RequestBody Repository repository) {
		repository = repositoryService.salvar(repository);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(repository.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/repository", method = RequestMethod.PATCH, produces = {
			MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> atualizar(@RequestBody Repository repository) {
		repositoryService.atualizar(repository);

		return ResponseEntity.noContent().build();
	}

	
	@RequestMapping(value = "/owner",method = RequestMethod.GET)
	public ResponseEntity<List<Owner>> listarOwner() {
		return ResponseEntity.status(HttpStatus.OK).body(repositoryService.listarOwnerTop10());
	}
	
	
}
