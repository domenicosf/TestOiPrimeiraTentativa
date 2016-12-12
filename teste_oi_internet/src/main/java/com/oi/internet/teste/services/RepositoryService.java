package com.oi.internet.teste.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.oi.internet.teste.domain.Owner;
import com.oi.internet.teste.domain.Repository;
import com.oi.internet.teste.repository.OwnerRepository;
import com.oi.internet.teste.repository.RepositoryDAO;
import com.oi.internet.teste.services.exceptions.OwnerNaoEncontradaException;
import com.oi.internet.teste.services.exceptions.RepositoryNaoEncontradaException;

@Service
public class RepositoryService {

	@Autowired
	private RepositoryDAO repositoryDAO;

	@Autowired
	private OwnerRepository ownerRepository;

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

	public void deletar(Long id) {
		try {
			repositoryDAO.delete(id);
		} catch (EmptyResultDataAccessException e) {
			throw new RepositoryNaoEncontradaException("O repositório não pôde ser encontrado.");
		}
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

		for (Repository repository : repositoryList) {
			Owner owner = repository.getOwner();
			if (owner != null) {
				ownerList.add(owner);
			}
		}

		/*
		 * if(ownerList.isEmpty()){ throw new
		 * OwnerNaoEncontradaException("Não existem owners para serem listados"
		 * ); }
		 */

		return ownerList;
	}
	
	public List<Owner> listarOwners() {
		List<Owner> owners = ownerRepository.findAll();

		if (owners == null || owners.isEmpty()) {
			throw new OwnerNaoEncontradaException("Os proprietários não puderam ser encontrados.");
		}

		return owners;
	}

	public Owner salvarOwner(Owner owner) {
		owner.setId(null);
		return ownerRepository.save(owner);
	}

	public Owner buscarOwner(Long id) {
		Owner owner = ownerRepository.findOne(id);

		if (owner == null) {
			throw new OwnerNaoEncontradaException("O proprietário não foi encontrado.");
		}

		return owner;
	}

	private void verificarExistenciaOwner(Owner owner) {
		buscarOwner(owner.getId());
	}

	public void atualizarOwner(Owner owner) {
		verificarExistenciaOwner(owner);
		ownerRepository.save(owner);
	}

	public void deletarOwner(Long id) {
		try {
			ownerRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			throw new OwnerNaoEncontradaException("O proprietário não pode ser encontrado.");
		}
	}

}

