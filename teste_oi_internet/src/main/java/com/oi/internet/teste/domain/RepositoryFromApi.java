package com.oi.internet.teste.domain;

import java.util.List;

public class RepositoryFromApi {

	private Long id;
	private Integer total_count;
	private boolean incomplete_results;
	private List<ItemsFromApi> items;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTotal_count() {
		return total_count;
	}

	public void setTotal_count(Integer total_count) {
		this.total_count = total_count;
	}

	public boolean isIncomplete_results() {
		return incomplete_results;
	}

	public void setIncomplete_results(boolean incomplete_results) {
		this.incomplete_results = incomplete_results;
	}

	public List<ItemsFromApi> getItems() {
		return items;
	}

	public void setItems(List<ItemsFromApi> items) {
		this.items = items;
	}

}
