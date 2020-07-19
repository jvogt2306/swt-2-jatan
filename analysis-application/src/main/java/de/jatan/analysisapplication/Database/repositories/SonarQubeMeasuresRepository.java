package de.jatan.analysisapplication.Database.repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import de.jatan.analysisapplication.Database.entities.SonarqubeMeasuresEntity;

public interface SonarQubeMeasuresRepository extends CrudRepository<SonarqubeMeasuresEntity, Integer> {
  Optional<SonarqubeMeasuresEntity> findByProject(String repository);
}
