package com.oi.internet.teste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oi.internet.teste.domain.Repository;

public interface RepositoryDAO extends JpaRepository<Repository, Long> {
	
	public List<Repository> findTop10ByOrderByStarsDescForksDesc();

}
