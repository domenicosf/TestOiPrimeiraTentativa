package com.oi.internet.teste.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.oi.internet.teste.domain.Settings;
import com.oi.internet.teste.domain.Stars;
import com.oi.internet.teste.repository.SettingsRepository;
import com.oi.internet.teste.repository.StarsRepository;
import com.oi.internet.teste.services.exceptions.SettingsNaoEncontradaException;
import com.oi.internet.teste.services.exceptions.StarNaoEncontradaException;

@Service
public class SettingsService {

	@Autowired
	private SettingsRepository settingsRepository;

	@Autowired
	private StarsRepository starsRepository;

	public List<Settings> listar() {

		List<Settings> settings = settingsRepository.findAll();

		if (settings == null || settings.isEmpty()) {
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

	public void deletar(Long id) {
		try {
			settingsRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			throw new SettingsNaoEncontradaException("A contiguração não pôde ser encontrada.");
		}
	}

	private void verificarExistencia(Settings settings) {
		buscar(settings.getId());
	}

	public Stars salvarStarsDeUmaSettingExistente(Long settingsId, Stars stars) {
		Settings settings = buscar(settingsId);

		stars.setSettings(settings);

		return starsRepository.save(stars);
	}

	public Stars listarStarsDeUmaSettingExistente(Long settingsId) {
		Settings settings = buscar(settingsId);

		return settings.getStars();
	}

	public List<Stars> listarStars() {
		List<Stars> stars = starsRepository.findAll();

		if (stars == null || stars.isEmpty()) {
			throw new StarNaoEncontradaException("As estrelas(stars) não puderam ser encontradas.");
		}

		return stars;
	}

	public Stars salvarStar(Stars stars) {
		stars.setId(null);
		return starsRepository.save(stars);
	}

	public Stars buscarStar(Long id) {
		Stars star = starsRepository.findOne(id);

		if (star == null) {
			throw new StarNaoEncontradaException("A estrela não foi encontrada.");
		}

		return star;
	}

	private void verificarExistenciaStars(Stars star) {
		buscarStar(star.getId());
	}

	public void atualizarStar(Stars star) {
		verificarExistenciaStars(star);
		starsRepository.save(star);
	}

	public void deletarStar(Long id) {
		try {
			starsRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			throw new StarNaoEncontradaException("A estrela não pôde ser encontrada.");
		}
	}

}
