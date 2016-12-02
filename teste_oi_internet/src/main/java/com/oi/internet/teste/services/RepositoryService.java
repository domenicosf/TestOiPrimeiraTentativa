package com.oi.internet.teste.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oi.internet.teste.domain.Owner;
import com.oi.internet.teste.domain.Repository;
import com.oi.internet.teste.domain.Settings;
import com.oi.internet.teste.repository.OwnerRepository;
import com.oi.internet.teste.repository.RepositoryDAO;
import com.oi.internet.teste.repository.SettingsRepository;
import com.oi.internet.teste.services.exceptions.RepositoryNaoEncontradaException;

@Service
public class RepositoryService {

	@Autowired
	private RepositoryDAO repositoryDAO;

	@Autowired
	private OwnerRepository ownerRepository;

	@Autowired
	private SettingsRepository settingsRepository;

	public List<Repository> listar() {
		return repositoryDAO.findAll();
	}

	public List<Repository> listarTop10() {
		List<Repository> repositoryList = repositoryDAO.findTop10ByOrderByStarsDescForksDesc();

		if (repositoryList == null || repositoryList.isEmpty()) {
			throw new RepositoryNaoEncontradaException("Os repositórios não puderam ser encontrados.");
		}

		return repositoryList;
	}

	public Repository salvar(Repository repository) {
		repository.setId(null);
		return repositoryDAO.save(repository);
	}

	public Repository buscar(Long id) {
		Repository repository = repositoryDAO.findOne(id);

		if (repository == null) {
			throw new RepositoryNaoEncontradaException("O repository não foi encontrado.");
		}

		return repository;
	}

	public void atualizar(Repository repository) {
		verificarExistencia(repository);
		repositoryDAO.save(repository);
	}

	private void verificarExistencia(Repository repository) {
		buscar(repository.getId());
	}

	public Owner salvarOwner(Long repositoryId, Owner owner) {
		Repository repository = buscar(repositoryId);

		owner.setRepo(repository);

		return ownerRepository.save(owner);
	}

	public Owner listarOwner(Long repositoryId) {
		Repository repository = buscar(repositoryId);

		return repository.getOwner();
	}
	
	public List<Owner> listarOwnerTop10() {
		List<Repository> repositoryList = repositoryDAO.findTop10ByOrderByStarsDescForksDesc();
		List<Owner> ownerList = new ArrayList<Owner>();
		if (repositoryList == null || repositoryList.isEmpty()) {
			throw new RepositoryNaoEncontradaException("Os repositórios não puderam ser encontrados.");
		}
		
		for(Repository repository : repositoryList){
			Owner owner = repository.getOwner();
			if(owner!=null){
				ownerList.add(owner);
			}
		}
		
		/*
		if(ownerList.isEmpty()){
			throw new OwnerNaoEncontradaException("Não existem owners para serem listados");
		}*/

		return ownerList;
	}

	public Settings salvarSettings(Long repositoryId, Settings settings) {
		Repository repository = buscar(repositoryId);

		settings.setRepository(repository);

		return settingsRepository.save(settings);
	}

	public Settings listarSettings(Long repositoryId) {
		Repository repository = buscar(repositoryId);

		return repository.getSettings();
	}

}
