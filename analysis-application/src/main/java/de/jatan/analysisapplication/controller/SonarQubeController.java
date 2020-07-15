
package de.jatan.analysisapplication.controller;

import de.jatan.analysisapplication.Domain.Model.*;
import de.jatan.analysisapplication.services.SonarResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.jatan.analysisapplication.services.SonarQubeService;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(path = "/sonarqube")
public class SonarQubeController {

  @Autowired
  private SonarQubeService sonarQubeService;
  @Autowired
  private SonarResultsService sonarResultsService;

  @PostMapping(path = "/hook")
  public void printSonarQubeStack(@RequestBody SonarQubeResponse sonarbody) {
    SonarQubeConditions[] metrices = sonarbody.getQualityGate().getConditions();
    for (int i= 0; i< metrices.length;i++){
      System.out.println("SonarResults: "+ metrices[i].getMetric()+" And the value is: "+ metrices[i].getValue());
    }
    SonarResults sonarResults = sonarResultsService.getResults(sonarbody);
    SonarResultsMeasures[] measures = sonarResults.getComponent().getMeasures();
    for (int i = 0; i < measures.length; i++){
      System.out.println("SonarResults: "+ measures[i].getMetric()+" And the value is: "+ measures[i].getValue());
    }
  }
  @GetMapping(path = "/create")
  public void getHealth(@RequestParam String projectName, String language) {
    sonarQubeService.createSonarQubeProject(projectName);
    sonarQubeService.updateWebhookPropertieSonarQubeProject(projectName);
    sonarQubeService.scanRepository(projectName, language);
  }
}
