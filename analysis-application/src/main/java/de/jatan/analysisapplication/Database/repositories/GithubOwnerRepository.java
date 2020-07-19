package de.jatan.analysisapplication.Database.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.jatan.analysisapplication.Database.entities.GithubOwnerEntity;

public interface GithubOwnerRepository extends CrudRepository<GithubOwnerEntity, Integer> {
  List<GithubOwnerEntity> findByLogin(String login);
}
