package de.jatan.analysisapplication.Database.repositories;

import org.springframework.data.repository.CrudRepository;

import de.jatan.analysisapplication.Database.entities.GithubOrganizationEntry;

public interface GithubOrganizationRepository extends CrudRepository<GithubOrganizationEntry, Integer> {
  GithubOrganizationEntry findByLogin(String login);

}
