
package de.jatan.analysisapplication.controller;

import de.jatan.analysisapplication.Domain.Model.*;
import de.jatan.analysisapplication.services.SonarQubeResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.jatan.analysisapplication.services.SonarQubeService;

@RestController
@RequestMapping(path = "/sonarqube")
public class SonarQubeController {

  @Autowired
  private SonarQubeService sonarQubeService;
  @Autowired
  private SonarQubeResultsService sonarQubeResultsService;

  @PostMapping(path = "/hook")
  public void printSonarQubeStack(@RequestBody SonarQubeResponse sonarbody) {
    SonarResults sonarResults = sonarQubeResultsService.getResults(sonarbody);
    sonarQubeResultsService.saveSonarQubeMeasures(sonarResults);
  }

  @GetMapping(path = "/create")
  public void getHealth(@RequestParam String projectName, String language) {
    sonarQubeService.createSonarQubeProject(projectName);
    sonarQubeService.updateWebhookPropertieSonarQubeProject(projectName);
    sonarQubeService.scanRepository(projectName, language);
  }
}
