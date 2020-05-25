package de.jatan.analysisapplication.Database.repositories;

import org.springframework.data.repository.CrudRepository;

import de.jatan.analysisapplication.Database.entities.RepositoryInformation;

public interface RepositoryInformationEntry extends CrudRepository<RepositoryInformation, Integer> {

}