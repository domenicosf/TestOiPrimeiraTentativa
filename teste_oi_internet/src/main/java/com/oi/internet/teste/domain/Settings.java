package com.oi.internet.teste.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
public class Settings {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonInclude(Include.NON_NULL)
	private String language;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE},fetch=FetchType.EAGER)
	@JoinColumn(name = "STARS_ID")
	@JsonInclude(Include.NON_NULL)
	private Stars stars;
	
	@JsonInclude(Include.NON_NULL)
	private Integer forks;
	
	@OneToOne(mappedBy="settings", fetch=FetchType.LAZY)
	@JsonIgnore
	@JsonInclude(Include.NON_NULL)
	private Repository repository;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Stars getStars() {
		return stars;
	}

	public void setStars(Stars stars) {
		this.stars = stars;
	}

	public Integer getForks() {
		return forks;
	}

	public void setForks(Integer forks) {
		this.forks = forks;
	}

	public Repository getRepository() {
		return repository;
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}
	
	

}
