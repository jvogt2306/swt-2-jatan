
package de.jatan.analysisapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.jatan.analysisapplication.Domain.Model.SonarQubeResponse;
import de.jatan.analysisapplication.services.SonarQubeService;

@RestController
@RequestMapping(path = "/sonarqube")
public class SonarQubeController {

  @Autowired
  private SonarQubeService sonarQubeService;

  @PostMapping(path = "/hook")
  public void printSonarQubeStack(@RequestBody SonarQubeResponse sonarbody) {
    System.err.println(sonarbody.getProject().getKey());
  }

  @GetMapping(path = "/create")
  public void getHealth(@RequestParam String projectName, String language) {
    System.err.println(language);
    sonarQubeService.scanRepository(projectName, language);
    // sonarQubeService.temp();
  }
}
