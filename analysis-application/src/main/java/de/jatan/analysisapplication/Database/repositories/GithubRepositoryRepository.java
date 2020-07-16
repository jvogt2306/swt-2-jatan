package de.jatan.analysisapplication.Database.repositories;

import org.springframework.data.repository.CrudRepository;

import de.jatan.analysisapplication.Database.entities.GithubRepositoryEntity;

public interface GithubRepositoryRepository extends CrudRepository<GithubRepositoryEntity, Integer> {

}
