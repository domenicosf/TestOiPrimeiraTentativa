package com.oi.internet.teste.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
		Repository repository = repositoryService.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(repository);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		repositoryService.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Repository repository, 
			@PathVariable("id") Long id) {
		repository.setId(id);
		repositoryService.atualizar(repository);
		
		return ResponseEntity.noContent().build();
	}

	
	@RequestMapping(value = "/owner",method = RequestMethod.GET)
	public ResponseEntity<List<Owner>> listarOwner() {
		return ResponseEntity.status(HttpStatus.OK).body(repositoryService.listarOwnerTop10());
	}
	
	@RequestMapping(value = "/owner/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscarOwner(@PathVariable("id") Long id) {
		Owner owner = repositoryService.buscarOwner(id);
		return ResponseEntity.status(HttpStatus.OK).body(owner);
	}

	@RequestMapping(value = "/owner/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarOwner(@PathVariable("id") Long id) {
		repositoryService.deletarOwner(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/owner/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizarOwner(@RequestBody Owner owner, 
			@PathVariable("id") Long id) {
		owner.setId(id);
		repositoryService.atualizarOwner(owner);
		
		return ResponseEntity.noContent().build();
	}
		
}
