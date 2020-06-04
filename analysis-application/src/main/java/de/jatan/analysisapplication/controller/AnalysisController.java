package de.jatan.analysisapplication.controller;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import de.jatan.analysisapplication.controller.DTO.RepositoryDTO;
import de.jatan.analysisapplication.services.GithubService;
import de.jatan.analysisapplication.services.SonarQubeService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/jatan")
public class AnalysisController {

  @Autowired
  private GithubService githubService;
  @Autowired
  private SonarQubeService sonarQubeService;

  @GetMapping(path = "/searchAnalysis", params = "repository")
  public void getAnalysisByRepository(@RequestParam String repository) {
    // Array von Repositories
  }

  @PostMapping(path = "/searchAnalysis", params = "organisation")
  public void getAnalysisByOrganisation(@RequestParam String organisation) {
    // Array von Organsiationen
  }

  @GetMapping(path = "/statusAnalysis")
  public void getStatusOfAnalysis(@RequestParam String id) {
    // Aktuellen Status der Analyse erfragen:
    // Möglicher Zustande: created, analysing, finished, error
  }

  @PostMapping(value = "/createAnalysis")
  @ResponseStatus(value = HttpStatus.OK)
  public void postMethodName(@RequestBody RepositoryDTO entity)
      throws InvalidRemoteException, TransportException, GitAPIException, InterruptedException {
    githubService.cloneRepository(entity);
    sonarQubeService.createSonarQubeProject(entity.projectName);
    sonarQubeService.updateWebhookPropertieSonarQubeProject(entity.projectName);
    sonarQubeService.scanRepository(entity.projectName, entity.language);
  }
}
