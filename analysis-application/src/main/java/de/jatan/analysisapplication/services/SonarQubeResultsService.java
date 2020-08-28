package de.jatan.analysisapplication.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import de.jatan.analysisapplication.Database.entities.SonarqubeMeasuresEntity;
import de.jatan.analysisapplication.Database.repositories.GithubRepositoryRepository;
import de.jatan.analysisapplication.Database.repositories.SonarQubeMeasuresRepository;
import de.jatan.analysisapplication.Domain.Model.SonarQubeResponse;
import de.jatan.analysisapplication.Domain.Model.SonarQubeResults;
import de.jatan.analysisapplication.Domain.Model.SonarResultsMeasures;
import de.jatan.analysisapplication.config.GlobalConfiguration;

@Service
@Configuration
@PropertySource("classpath:application.properties")
public class SonarQubeResultsService {

  private final RestTemplate restTemplate;

  @Autowired
  private SonarQubeMeasuresRepository sonarQubeMeasuresRepository;

  @Autowired
  private GithubRepositoryRepository githubRepositoryRepository;

  public SonarQubeResultsService(final RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder
        .basicAuthentication(GlobalConfiguration.SonarUser, GlobalConfiguration.SonarPassword).build();
  }

  public SonarQubeResults getResults(final SonarQubeResponse sonarbody) {
    final String projectKey = sonarbody.getProject().getKey();
    final String searchSonarProjectEndpoint = GlobalConfiguration.SonarAdress + "/api/measures/component";
    final UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(searchSonarProjectEndpoint)
        .queryParam("component", projectKey).queryParam("metricKeys",
            "bugs,code_smells,sqale_index,ncloc,vulnerabilities,security_rating,duplicated_lines,complexity,violations,reliability_rating");
    final ResponseEntity<SonarQubeResults> responseEntity = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,
        null, SonarQubeResults.class);
    return responseEntity.getBody();
  }

  public String getCodeSmells(final SonarResultsMeasures[] measures) {
    for (int i = 0; i < measures.length; i++) {
      if (measures[i].getMetric().equals("code_smells")) {
        return measures[i].getValue();
      }
    }
    return "";
  }

  public String getBugs(final SonarResultsMeasures[] measures) {
    for (int i = 0; i < measures.length; i++) {
      if (measures[i].getMetric().equals("bugs")) {
        return measures[i].getValue();
      }
    }
    return "";
  }

  public String getSqaleIndex(final SonarResultsMeasures[] measures) {
    for (int i = 0; i < measures.length; i++) {
      if (measures[i].getMetric().equals("sqale_index")) {
        return measures[i].getValue();
      }
    }
    return "";
  }

  public String getLinesOfCode(final SonarResultsMeasures[] measures) {
    for (int i = 0; i < measures.length; i++) {
      if (measures[i].getMetric().equals("ncloc")) {
        return measures[i].getValue();
      }
    }
    return "";
  }

  public String getVulnerabilities(final SonarResultsMeasures[] measures) {
    for (int i = 0; i < measures.length; i++) {
      if (measures[i].getMetric().equals("vulnerabilities")) {
        return measures[i].getValue();
      }
    }
    return "";
  }

  public String getSecurityRating(final SonarResultsMeasures[] measures) {
    for (int i = 0; i < measures.length; i++) {
      if (measures[i].getMetric().equals("security_rating")) {
        return measures[i].getValue();
      }
    }
    return "";
  }

  public String getDuplicatedLines(final SonarResultsMeasures[] measures) {
    for (int i = 0; i < measures.length; i++) {
      if (measures[i].getMetric().equals("duplicated_lines")) {
        return measures[i].getValue();
      }
    }
    return "";
  }

  public String getComplexity(final SonarResultsMeasures[] measures) {
    for (int i = 0; i < measures.length; i++) {
      if (measures[i].getMetric().equals("complexity")) {
        return measures[i].getValue();
      }
    }
    return "";
  }

  public String getViolations(final SonarResultsMeasures[] measures) {

    for (int i = 0; i < measures.length; i++) {
      if (measures[i].getMetric().equals("violations")) {
        return measures[i].getValue();
      }
    }
    return "";
  }

  public String getReliabilityRating(final SonarResultsMeasures[] measures) {
    for (int i = 0; i < measures.length; i++) {
      if (measures[i].getMetric().equals("reliability_rating")) {
        return measures[i].getValue();
      }
    }
    return "";
  }

  public SonarqubeMeasuresEntity saveSonarQubeMeasures(final SonarQubeResults sonarResults) {
    final Optional<SonarqubeMeasuresEntity> entityInDB = sonarQubeMeasuresRepository
        .findByProject(sonarResults.getComponent().getKey());
    if (entityInDB.isEmpty()) {
      return insertMeasuresIntoDatabase(sonarResults);
    } else {
      return updateMeasuresInDatabase(entityInDB.get(), sonarResults);
    }
  }

  private SonarqubeMeasuresEntity updateMeasuresInDatabase(final SonarqubeMeasuresEntity sonarqubeMeasuresEntity,
      final SonarQubeResults sonarResults) {
    final SonarResultsMeasures[] measures = sonarResults.getComponent().getMeasures();
    sonarqubeMeasuresEntity.setBugs(getBugs(measures));
    sonarqubeMeasuresEntity.setCode_smells(getCodeSmells(measures));
    sonarqubeMeasuresEntity.setNcloc(getLinesOfCode(measures));
    sonarqubeMeasuresEntity.setSqale_index(getSqaleIndex(measures));
    sonarqubeMeasuresEntity.setVulnerabilities(getVulnerabilities(measures));
    sonarqubeMeasuresEntity.setSecurity_rating(getSecurityRating(measures));
    sonarqubeMeasuresEntity.setDuplicated_lines(getDuplicatedLines(measures));
    sonarqubeMeasuresEntity.setComplexity(getComplexity(measures));
    sonarqubeMeasuresEntity.setViolations(getViolations(measures));
    sonarqubeMeasuresEntity.setReliability_rating(getReliabilityRating(measures));
    return sonarQubeMeasuresRepository.save(sonarqubeMeasuresEntity);
  }

  public SonarqubeMeasuresEntity insertMeasuresIntoDatabase(final SonarQubeResults sonarResults) {
    final SonarqubeMeasuresEntity sonarqubeMeasuresEntity = new SonarqubeMeasuresEntity();
    final SonarResultsMeasures[] measures = sonarResults.getComponent().getMeasures();

    sonarqubeMeasuresEntity.setBugs(getBugs(measures));
    sonarqubeMeasuresEntity.setCode_smells(getCodeSmells(measures));
    sonarqubeMeasuresEntity.setNcloc(getLinesOfCode(measures));
    sonarqubeMeasuresEntity.setSqale_index(getSqaleIndex(measures));
    sonarqubeMeasuresEntity.setVulnerabilities(getVulnerabilities(measures));
    sonarqubeMeasuresEntity.setSecurity_rating(getSecurityRating(measures));
    sonarqubeMeasuresEntity.setDuplicated_lines(getDuplicatedLines(measures));
    sonarqubeMeasuresEntity.setComplexity(getComplexity(measures));
    sonarqubeMeasuresEntity.setViolations(getViolations(measures));
    sonarqubeMeasuresEntity.setReliability_rating(getReliabilityRating(measures));
    sonarqubeMeasuresEntity.setProject(sonarResults.getComponent().getName());
    sonarqubeMeasuresEntity.setRepository(githubRepositoryRepository.findByName(sonarResults.getComponent().getKey()));
    return sonarQubeMeasuresRepository.save(sonarqubeMeasuresEntity);
  }
}
