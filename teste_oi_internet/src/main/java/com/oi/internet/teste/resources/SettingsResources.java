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

import com.oi.internet.teste.domain.Settings;
import com.oi.internet.teste.services.SettingsService;

@RestController
@RequestMapping("/settings")
public class SettingsResources {

	@Autowired
	SettingsService settingsService;

	@RequestMapping(method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Settings>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(settingsService.listar());
	}

	@RequestMapping(method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> salvar(@RequestBody Settings settings) {
		settings = settingsService.salvar(settings);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(settings.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(method = RequestMethod.PATCH, produces = {
			MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> atualizar(@RequestBody Settings settings) {
		settingsService.atualizar(settings);

		return ResponseEntity.noContent().build();
	}

}
