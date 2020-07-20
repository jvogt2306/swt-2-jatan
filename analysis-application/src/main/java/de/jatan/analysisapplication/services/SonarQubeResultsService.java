package de.jatan.analysisapplication.services;

import de.jatan.analysisapplication.Database.entities.SonarqubeMeasuresEntity;
import de.jatan.analysisapplication.Database.repositories.GithubRepositoryRepository;
import de.jatan.analysisapplication.Database.repositories.SonarQubeMeasuresRepository;
import de.jatan.analysisapplication.Domain.Model.SonarQubeResponse;
import de.jatan.analysisapplication.Domain.Model.SonarResults;
import de.jatan.analysisapplication.Domain.Model.SonarResultsMeasures;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SonarQubeResultsService {

  @Autowired
  private SonarQubeMeasuresRepository sonarQubeMeasuresRepository;

  @Autowired
  private GithubRepositoryRepository githubRepositoryRepository;

  public SonarResults getResults(SonarQubeResponse sonarbody) {
    String projectKey = sonarbody.getProject().getKey();

    RestTemplate restTemplate = new RestTemplate();
    String urlSonarResults = ("http://localhost:7000/api/measures/component?component=" + projectKey);
    SonarResults sonarResults = restTemplate.getForObject(
        urlSonarResults + "&metricKeys=bugs,code_smells,sqale_index,coverage,ncloc,sqale_debt_ratio",
        SonarResults.class);
    return sonarResults;
  }

  public String getCode_smells(SonarResults sonarResults) {
    SonarResultsMeasures[] measures = sonarResults.getComponent().getMeasures();
    String code_smells = new String(" ");
    for (int i = 0; i < measures.length; i++) {
      if (measures[i].getMetric().equals("code_smells")) {
        code_smells = measures[i].getValue();
      }
    }
    return code_smells;
  }

  public String getBugs(SonarResults sonarResults) {
    SonarResultsMeasures[] measures = sonarResults.getComponent().getMeasures();
    String bugs = new String(" ");
    for (int i = 0; i < measures.length; i++) {
      if (measures[i].getMetric().equals("bugs")) {
        bugs = measures[i].getValue();
      }
    }
    return bugs;
  }

  public String getSqale_index(SonarResults sonarResults) {
    SonarResultsMeasures[] measures = sonarResults.getComponent().getMeasures();
    String sqale_index = new String(" ");
    for (int i = 0; i < measures.length; i++) {
      if (measures[i].getMetric().equals("sqale_index")) {
        sqale_index = measures[i].getValue();
      }
    }
    return sqale_index;
  }

  public String getCoverage(SonarResults sonarResults) {
    SonarResultsMeasures[] measures = sonarResults.getComponent().getMeasures();
    String coverage = new String(" ");
    for (int i = 0; i < measures.length; i++) {
      if (measures[i].getMetric().equals("coverage")) {
        coverage = measures[i].getValue();
      }
    }
    return coverage;
  }

  public String getLinesOfCode(SonarResults sonarResults) {
    SonarResultsMeasures[] measures = sonarResults.getComponent().getMeasures();
    String ncloc = new String(" ");
    for (int i = 0; i < measures.length; i++) {
      if (measures[i].getMetric().equals("ncloc")) {
        ncloc = measures[i].getValue();
      }
    }
    return ncloc;
  }

  public SonarqubeMeasuresEntity saveSonarQubeMeasures(SonarResults sonarResults) {
    Optional<SonarqubeMeasuresEntity> entityInDB = sonarQubeMeasuresRepository
        .findByProject(sonarResults.getComponent().getKey());
    if (entityInDB.isEmpty()) {
      return insertMeasuresIntoDatabase(sonarResults);
    } else {
      return updateMeasuresInDatabase(entityInDB.get(), sonarResults);
    }
  }

  private SonarqubeMeasuresEntity updateMeasuresInDatabase(SonarqubeMeasuresEntity sonarqubeMeasuresEntity,
      SonarResults sonarResults) {
    sonarqubeMeasuresEntity.setBugs(getBugs(sonarResults));
    sonarqubeMeasuresEntity.setCode_smells(getCode_smells(sonarResults));
    sonarqubeMeasuresEntity.setCoverage(getCoverage(sonarResults));
    sonarqubeMeasuresEntity.setNcloc(getLinesOfCode(sonarResults));
    sonarqubeMeasuresEntity.setSqale_index(getSqale_index(sonarResults));
    return sonarQubeMeasuresRepository.save(sonarqubeMeasuresEntity);
  }

  public SonarqubeMeasuresEntity insertMeasuresIntoDatabase(SonarResults sonarResults) {
    SonarqubeMeasuresEntity measures = new SonarqubeMeasuresEntity();
    measures.setBugs(getBugs(sonarResults));
    measures.setCode_smells(getCode_smells(sonarResults));
    measures.setCoverage(getCoverage(sonarResults));
    measures.setNcloc(getLinesOfCode(sonarResults));
    measures.setSqale_index(getSqale_index(sonarResults));
    measures.setProject(sonarResults.getComponent().getName());
    measures.setRepository(githubRepositoryRepository.findByName(sonarResults.getComponent().getKey()));
    return sonarQubeMeasuresRepository.save(measures);
  }
}
