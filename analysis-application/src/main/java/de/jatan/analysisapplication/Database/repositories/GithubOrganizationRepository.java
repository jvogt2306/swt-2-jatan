package de.jatan.analysisapplication.Database.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.jatan.analysisapplication.Database.entities.GithubOrganizationEntry;

public interface GithubOrganizationRepository extends CrudRepository<GithubOrganizationEntry, Integer> {
  List<GithubOrganizationEntry> findByLogin(String login);

}
