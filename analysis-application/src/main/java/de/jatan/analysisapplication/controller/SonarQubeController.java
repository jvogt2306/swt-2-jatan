
package de.jatan.analysisapplication.controller;

import de.jatan.analysisapplication.Domain.Model.*;
import de.jatan.analysisapplication.services.SonarQubeResultsService;
import de.jatan.analysisapplication.services.SonarQubeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/sonarqube")
public class SonarQubeController {

  @Autowired
  private SonarQubeResultsService sonarQubeResultsService;

  @Autowired
  private SonarQubeService sonarQubeService;

  @PostMapping(path = "/hook")
  @ResponseStatus(value = HttpStatus.OK)
  public void printSonarQubeStack(@RequestBody SonarQubeResponse sonarbody) {
    SonarResults sonarResults = sonarQubeResultsService.getResults(sonarbody);
    sonarQubeResultsService.saveSonarQubeMeasures(sonarResults);
    sonarQubeService.removeRepositoryFromSonarQube(sonarbody.getProject());
  }
}
