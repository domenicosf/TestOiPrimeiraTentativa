package com.oi.internet.teste.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oi.internet.teste.domain.Settings;
import com.oi.internet.teste.domain.Stars;
import com.oi.internet.teste.repository.SettingsRepository;
import com.oi.internet.teste.repository.StarsRepository;
import com.oi.internet.teste.services.exceptions.SettingsNaoEncontradaException;

@Service
public class SettingsService {

	@Autowired
	private SettingsRepository settingsRepository;

	@Autowired
	private StarsRepository starsRepository;

	public List<Settings> listar() {
		
		List<Settings> settings = settingsRepository.findAll();
		
		if(settings == null || settings.isEmpty()) {
			throw new SettingsNaoEncontradaException("As configurações não puderam ser encontradas.");
		}
		
		return settings;
	}

	public Settings salvar(Settings settings) {
		settings.setId(null);
		return settingsRepository.save(settings);
	}

	public Settings buscar(Long id) {
		Settings settings = settingsRepository.findOne(id);

		if (settings == null) {
			throw new SettingsNaoEncontradaException("As settings não foram encontradas.");
		}

		return settings;
	}

	public void atualizar(Settings settings) {
		verificarExistencia(settings);
		settingsRepository.save(settings);
	}

	private void verificarExistencia(Settings settings) {
		buscar(settings.getId());
	}

	public Stars salvarStars(Long settingsId, Stars stars) {
		Settings settings = buscar(settingsId);

		stars.setSettings(settings);

		return starsRepository.save(stars);
	}

	public Stars listarStars(Long settingsId) {
		Settings settings = buscar(settingsId);

		return settings.getStars();
	}

}
