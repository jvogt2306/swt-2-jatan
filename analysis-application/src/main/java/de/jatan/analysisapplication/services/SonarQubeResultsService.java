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
import org.springframework.beans.factory.annotation.Value;

@Service
public class SonarQubeResultsService {

  private String sonarUser = "admin";
  private String sonarPassword = "admin";
  @Value("${sonar.address}")
  private String sonarAddress;


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
    String searchSonarProjectEndpoint = sonarAddress + "/api/measures/component";

    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(searchSonarProjectEndpoint)
        .queryParam("component", projectKey).queryParam("metricKeys",
            "bugs,code_smells,sqale_index,ncloc,sqale_debt_ratio,vulnerabilities,security_rating,duplicated_lines,complexity,violations,reliability_rating");
    ResponseEntity<SonarResults> responseEntity = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, null,
        SonarResults.class);
    return responseEntity.getBody();
  }

  public String getCode_smells(SonarResultsMeasures[] measures) {
    String code_smells = new String("");
    for (int i = 0; i < measures.length; i++) {
      if (measures[i].getMetric().equals("code_smells")) {
        code_smells = measures[i].getValue();
      }
    }
    return code_smells;
  }

  public String getBugs(SonarResultsMeasures[] measures) {
    String bugs = new String("");
    for (int i = 0; i < measures.length; i++) {
      if (measures[i].getMetric().equals("bugs")) {
        bugs = measures[i].getValue();
      }
    }
    return bugs;
  }

  public String getSqale_index(SonarResultsMeasures[] measures) {
    String sqale_index = new String("");
    for (int i = 0; i < measures.length; i++) {
      if (measures[i].getMetric().equals("sqale_index")) {
        sqale_index = measures[i].getValue();
      }
    }
    return sqale_index;
  }

  public String getLinesOfCode(SonarResultsMeasures[] measures) {
    String ncloc = new String("");
    for (int i = 0; i < measures.length; i++) {
      if (measures[i].getMetric().equals("ncloc")) {
        ncloc = measures[i].getValue();
      }
    }
    return ncloc;
  }

  public String getVulnerabilities(SonarResultsMeasures[] measures) {
    String vulnerabilities = new String("");
    for (int i = 0; i < measures.length; i++) {
      if (measures[i].getMetric().equals("ncloc")) {
        vulnerabilities = measures[i].getValue();
      }
    }
    return vulnerabilities;
  }

  public String getSecurityRating(SonarResultsMeasures[] measures) {
    String securityRating = new String("");
    for (int i = 0; i < measures.length; i++) {
      if (measures[i].getMetric().equals("security_rating")) {
        securityRating = measures[i].getValue();
      }
    }
    return securityRating;
  }

  public String getDuplicatedLines(SonarResultsMeasures[] measures) {
    String duplicatedLines = new String("");
    for (int i = 0; i < measures.length; i++) {
      if (measures[i].getMetric().equals("duplicated_lines")) {
        duplicatedLines = measures[i].getValue();
      }
    }
    return duplicatedLines;
  }

  public String getComplexity(SonarResultsMeasures[] measures) {
    String complexity = new String("");
    for (int i = 0; i < measures.length; i++) {
      if (measures[i].getMetric().equals("complexity")) {
        complexity = measures[i].getValue();
      }
    }
    return complexity;
  }

  public String getViolations(SonarResultsMeasures[] measures) {
    String violations = new String("");
    for (int i = 0; i < measures.length; i++) {
      if (measures[i].getMetric().equals("violations")) {
        violations = measures[i].getValue();
      }
    }
    return violations;
  }

  public String getReliabilityRating(SonarResultsMeasures[] measures) {
    String reliabilityRating = new String("");
    for (int i = 0; i < measures.length; i++) {
      if (measures[i].getMetric().equals("reliability_rating")) {
        reliabilityRating = measures[i].getValue();
      }
    }
    return reliabilityRating;
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
    SonarResultsMeasures[] measures = sonarResults.getComponent().getMeasures();
    sonarqubeMeasuresEntity.setBugs(getBugs(measures));
    sonarqubeMeasuresEntity.setCode_smells(getCode_smells(measures));
    sonarqubeMeasuresEntity.setNcloc(getLinesOfCode(measures));
    sonarqubeMeasuresEntity.setSqale_index(getSqale_index(measures));
    sonarqubeMeasuresEntity.setVulnerabilities(getVulnerabilities(measures));
    sonarqubeMeasuresEntity.setSecurity_rating(getSecurityRating(measures));
    sonarqubeMeasuresEntity.setDuplicated_lines(getDuplicatedLines(measures));
    sonarqubeMeasuresEntity.setComplexity(getComplexity(measures));
    sonarqubeMeasuresEntity.setViolations(getViolations(measures));
    sonarqubeMeasuresEntity.setReliability_rating(getReliabilityRating(measures));
    return sonarQubeMeasuresRepository.save(sonarqubeMeasuresEntity);
  }

  public SonarqubeMeasuresEntity insertMeasuresIntoDatabase(SonarResults sonarResults) {
    SonarqubeMeasuresEntity sonarqubeMeasuresEntity = new SonarqubeMeasuresEntity();
    SonarResultsMeasures[] measures = sonarResults.getComponent().getMeasures();

    sonarqubeMeasuresEntity.setBugs(getBugs(measures));
    sonarqubeMeasuresEntity.setCode_smells(getCode_smells(measures));
    sonarqubeMeasuresEntity.setNcloc(getLinesOfCode(measures));
    sonarqubeMeasuresEntity.setSqale_index(getSqale_index(measures));
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
