package de.jatan.analysisapplication.Database.repositories;

import org.springframework.data.repository.CrudRepository;

import de.jatan.analysisapplication.Database.entities.GithubOwnerEntity;

public interface GithubOwnerRepository extends CrudRepository<GithubOwnerEntity, Integer> {
  GithubOwnerEntity findByLogin(String login);
}
