package de.jatan.analysisapplication.Database.repositories;

import org.springframework.data.repository.CrudRepository;

import de.jatan.analysisapplication.Database.entities.GithubUserEntry;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface GithubUserRepository extends CrudRepository<GithubUserEntry, Integer> {

}
