package de.jatan.analysisapplication.Database.repositories;

import org.springframework.data.repository.CrudRepository;

import de.jatan.analysisapplication.Database.entities.OrganizationInformationEntry;

public interface OrganizationInformationRepository extends CrudRepository<OrganizationInformationEntry, Integer> {

    }