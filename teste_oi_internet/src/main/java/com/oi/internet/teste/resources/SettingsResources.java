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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.oi.internet.teste.domain.RepositoryFromApi;
import com.oi.internet.teste.domain.Settings;
import com.oi.internet.teste.domain.Stars;
import com.oi.internet.teste.parsers.ParserDataFromApi;
import com.oi.internet.teste.services.SettingsService;

@RestController
@RequestMapping("/settings")
public class SettingsResources {

	@Autowired
	SettingsService settingsService;

	@Autowired
	ParserDataFromApi parser;

	private final String URL = "https://api.github.com/search/repositories?q=language:";

	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Settings>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(settingsService.listar());
	}

	@RequestMapping(method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> salvar(@RequestBody Settings settings) {

		RestTemplate restTemplate = new RestTemplate();

		// Composição da URI
		String uri_api = URL + settings.getLanguage().trim() + "&sort=stars&order=desc";

		RepositoryFromApi repository_api = restTemplate.getForObject(uri_api, RepositoryFromApi.class);

		settings = settingsService.salvar(settings);

		// Salvando os repositórios e os owners
		parser.saveRepositoriesList(repository_api, settings);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(settings.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(method = RequestMethod.PATCH, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> atualizar(@RequestBody Settings settings) {
		settingsService.atualizar(settings);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
		Settings settings = settingsService.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(settings);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		settingsService.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Settings settings, @PathVariable("id") Long id) {
		settings.setId(id);
		settingsService.atualizar(settings);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/stars", method = RequestMethod.GET)
	public ResponseEntity<List<Stars>> listarStars() {
		return ResponseEntity.status(HttpStatus.OK).body(settingsService.listarStars());
	}

	@RequestMapping(value = "/stars/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscarStar(@PathVariable("id") Long id) {
		Stars star = settingsService.buscarStar(id);
		return ResponseEntity.status(HttpStatus.OK).body(star);
	}

	@RequestMapping(value = "/stars/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarStar(@PathVariable("id") Long id) {
		settingsService.deletarStar(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/stars/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizarStar(@RequestBody Stars star, @PathVariable("id") Long id) {
		star.setId(id);
		settingsService.atualizarStar(star);
		;

		return ResponseEntity.noContent().build();
	}

}
