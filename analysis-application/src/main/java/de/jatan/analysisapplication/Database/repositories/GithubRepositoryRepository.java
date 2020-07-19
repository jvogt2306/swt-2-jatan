package de.jatan.analysisapplication.Database.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.jatan.analysisapplication.Database.entities.GithubRepositoryEntity;

public interface GithubRepositoryRepository extends CrudRepository<GithubRepositoryEntity, Integer> {
  List<GithubRepositoryEntity> findByName(String name);
}
