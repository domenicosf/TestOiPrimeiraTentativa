package com.oi.internet.teste.parsers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oi.internet.teste.domain.ItemsFromApi;
import com.oi.internet.teste.domain.Owner;
import com.oi.internet.teste.domain.OwnerFromApi;
import com.oi.internet.teste.domain.Repository;
import com.oi.internet.teste.domain.RepositoryFromApi;
import com.oi.internet.teste.domain.Settings;
import com.oi.internet.teste.services.RepositoryService;

@Component
public class ParserDataFromApi {

	@Autowired
	RepositoryService repositoryService;

	public boolean saveRepositoriesList(RepositoryFromApi repository_api, Settings settings) {
		List<ItemsFromApi> listItems = repository_api.getItems();
		if (listItems == null || listItems.size() == 0)
			return false;
		for (ItemsFromApi item : listItems) {
			if (item != null && item.getOwner() != null && !item.getOwner().getLogin().isEmpty()) {
				// Validações com relação ao Setting criado no método post de
				// SettingsResource
				if ((item.getForks_count() >= settings.getForks())
						&& ((item.getStargazers_count() >= settings.getStars().getMin())
								&& (item.getStargazers_count() <= settings.getStars().getMax()))) {
					Repository repository = new Repository();
					repository.setDescription(item.getDescription());
					repository.setForks(item.getForks_count());
					repository.setFullName(item.getFull_name());
					repository.setName(item.getName());
					repository.setOpenIssues(item.getOpen_issues_count());
					repository.setStars(item.getStargazers_count());
					repository.setUrl(item.getUrl());
					repository.setOwner(criaOwner(item.getOwner()));
					repositoryService.salvar(repository);
				} else
					continue;
			}

		}
		return true;
	}

	private Owner criaOwner(OwnerFromApi owner_api) {
		Owner owner = new Owner();
		owner.setAvatar(owner_api.getAvatar_url());
		owner.setLogin(owner_api.getLogin());
		// Não recebemos o nome do autor pela Api do Git Hub
		owner.setName(owner_api.getType());
		owner.setProfile(owner_api.getHtml_url());
		owner.setRepository(owner_api.getRepos_url());
		return owner;
	}

}
