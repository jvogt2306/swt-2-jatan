package de.jatan.analysisapplication.services;

import de.jatan.analysisapplication.Database.entities.SonarqubeMeasuresEntity;
import de.jatan.analysisapplication.Database.repositories.GithubRepositoryRepository;
import de.jatan.analysisapplication.Database.repositories.SonarQubeMeasuresRepository;
import de.jatan.analysisapplication.Domain.Model.SonarQubeResponse;
import de.jatan.analysisapplication.Domain.Model.SonarResults;
import de.jatan.analysisapplication.Domain.Model.SonarResultsMeasures;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class SonarQubeResultsService {

  private String sonarUser = "admin";
  private String sonarPassword = "admin";

  private final RestTemplate restTemplate;

  @Autowired
  private SonarQubeMeasuresRepository sonarQubeMeasuresRepository;

  @Autowired
  private GithubRepositoryRepository githubRepositoryRepository;

  public SonarQubeResultsService(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.basicAuthentication(sonarUser, sonarPassword).build();
  }

  public SonarResults getResults(SonarQubeResponse sonarbody) {
    String projectKey = sonarbody.getProject().getKey();

    String searchSonarProjectEndpoint = "http://localhost:9000/api/measures/component";

    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(searchSonarProjectEndpoint)
        .queryParam("component", projectKey)
        .queryParam("metricKeys", "bugs,code_smells,sqale_index,coverage,ncloc,sqale_debt_ratio");
    ResponseEntity<SonarResults> responseEntity = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, null,
        SonarResults.class);
    return responseEntity.getBody();
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
