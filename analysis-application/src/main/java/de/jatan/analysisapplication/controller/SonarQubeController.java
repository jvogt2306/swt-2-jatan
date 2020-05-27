
package de.jatan.analysisapplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.jatan.analysisapplication.Domain.Model.SonarQubeResponse;

@RestController
@RequestMapping(path = "/sonarqube")
public class SonarQubeController {

  @PostMapping(path = "/hook")
  public void printSonarQubeStack(@RequestBody SonarQubeResponse sonarbody) {
    System.err.println(sonarbody.getProject().getKey());
  }

  @GetMapping(path = "/create")
  public String getHealth() {
    return "erstelle neues sonar-project";
  }
}
