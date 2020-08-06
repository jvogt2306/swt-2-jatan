package de.jatan.analysisapplication.controller;

import java.util.List;

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
import de.jatan.analysisapplication.services.GithubService;
import de.jatan.analysisapplication.services.JatanAnalysisService;
import de.jatan.analysisapplication.services.SonarQubeService;
import de.jatan.analysisapplication.Database.entities.JatanAnalysisDetailsEntry;
import de.jatan.analysisapplication.Database.entities.JatanAnalysisEntry;
import de.jatan.analysisapplication.Domain.Model.GithubOrganization;
import de.jatan.analysisapplication.Domain.Model.GithubRepository;
import de.jatan.analysisapplication.exceptions.GithubOrganisationNotFoundException;

@RestController
@RequestMapping(path = "/jatan")
public class AnalysisController {

  @Autowired
  private GithubService githubService;
  @Autowired
  private SonarQubeService sonarQubeService;
  @Autowired
  private JatanAnalysisService jatanAnalysisService;

  @GetMapping(path = "/searchAnalysis")
  @ResponseStatus(value = HttpStatus.OK)
  public Iterable<JatanAnalysisEntry> getAllCompanyAnalysis() {
    return jatanAnalysisService.getAllCompaniesAnalysis();
  }

  @GetMapping(path = "/searchAnalysisDetails")
  @ResponseStatus(value = HttpStatus.OK)
  public Iterable<JatanAnalysisDetailsEntry> getAllCompanyAnalysisDetails() {
    return jatanAnalysisService.getAllCompanyAnalysisDetails();
  }

  @GetMapping(path = "/createAnalysis")
  @ResponseStatus(value = HttpStatus.OK)
  public void getGithubOrganization(@RequestParam String organizationName) throws InvalidRemoteException,
      TransportException, GitAPIException, InterruptedException, GithubOrganisationNotFoundException {

    GithubOrganization organization = githubService.fetchOrganizations(organizationName);
    githubService.insertGithubOrganizationIsNotExist(organization);
    List<GithubRepository> repositories = githubService.fetchRepositoriesByURL(organization.getRepos_url());

    repositories.stream().forEach(repo -> {
      if (repo.getLanguage() != null
          && (repo.getLanguage().equals("JavaScript") || repo.getLanguage().equals("Java"))) {
        githubService.insertGithubOwnerIsNotExist(repo.getOwner());
        githubService.insertGithubRepositoryIsNotExist(repo);
        try {
          githubService.cloneRepository(repo);
        } catch (GitAPIException e1) {
          e1.printStackTrace();
        }
        sonarQubeService.createSonarQubeProject(repo.getName());
        sonarQubeService.updateWebhookPropertieSonarQubeProject(repo.getName());
        sonarQubeService.scanRepository(repo.getName(), repo.getLanguage());
      }
    });
  }

  @GetMapping(path = "/health")
  @ResponseStatus(value = HttpStatus.OK)
  public String health() {
    return "Status: " + true;
  }
}
