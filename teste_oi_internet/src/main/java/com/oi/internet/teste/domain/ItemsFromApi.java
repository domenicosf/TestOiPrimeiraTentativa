package com.oi.internet.teste.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemsFromApi {

	private Long id;
	private String name;
	private String full_name;
	private OwnerFromApi owner;
	@JsonProperty("private")
	private boolean privado;
	private String html_url;
	private String description;
	private boolean fork;
	private String url;
	private String created_at;
	private String updated_at;
	private String pushed_at;
	private String homepage;
	private Integer size;
	private Integer stargazers_count;
	private Integer watchers_count;
	private String language;
	private Integer forks_count;
	private Integer open_issues_count;
	private String master_branch;
	private String default_branch;
	private Float score;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public OwnerFromApi getOwner() {
		return owner;
	}

	public void setOwner(OwnerFromApi owner) {
		this.owner = owner;
	}

	public boolean isPrivado() {
		return privado;
	}

	public void setPrivado(boolean privado) {
		this.privado = privado;
	}

	public String getHtml_url() {
		return html_url;
	}

	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isFork() {
		return fork;
	}

	public void setFork(boolean fork) {
		this.fork = fork;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public String getPushed_at() {
		return pushed_at;
	}

	public void setPushed_at(String pushed_at) {
		this.pushed_at = pushed_at;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getStargazers_count() {
		return stargazers_count;
	}

	public void setStargazers_count(Integer stargazers_count) {
		this.stargazers_count = stargazers_count;
	}

	public Integer getWatchers_count() {
		return watchers_count;
	}

	public void setWatchers_count(Integer watchers_count) {
		this.watchers_count = watchers_count;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Integer getForks_count() {
		return forks_count;
	}

	public void setForks_count(Integer forks_count) {
		this.forks_count = forks_count;
	}

	public Integer getOpen_issues_count() {
		return open_issues_count;
	}

	public void setOpen_issues_count(Integer open_issues_count) {
		this.open_issues_count = open_issues_count;
	}

	public String getMaster_branch() {
		return master_branch;
	}

	public void setMaster_branch(String master_branch) {
		this.master_branch = master_branch;
	}

	public String getDefault_branch() {
		return default_branch;
	}

	public void setDefault_branch(String default_branch) {
		this.default_branch = default_branch;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

}
