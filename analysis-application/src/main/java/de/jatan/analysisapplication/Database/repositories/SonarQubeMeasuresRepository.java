package de.jatan.analysisapplication.Database.repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.jatan.analysisapplication.Database.entities.SonarqubeMeasuresEntity;

@Repository
public interface SonarQubeMeasuresRepository extends CrudRepository<SonarqubeMeasuresEntity, Integer> {
  Optional<SonarqubeMeasuresEntity> findByProject(String repository);
}
