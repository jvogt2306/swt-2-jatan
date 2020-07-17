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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.jatan.analysisapplication.Database.entities.GithubRepositoryEntity;
import de.jatan.analysisapplication.Database.entities.GithubUserEntry;
import de.jatan.analysisapplication.Domain.Model.GithubOrganization;
import de.jatan.analysisapplication.Domain.Model.GithubRepository;
import de.jatan.analysisapplication.Domain.Model.GithubUser;
import de.jatan.analysisapplication.controller.DTO.RepositoryDTO;
import de.jatan.analysisapplication.services.GithubService;
import de.jatan.analysisapplication.services.SonarQubeService;

@RestController
@RequestMapping(path = "/github")
public class GithubController {

  @Autowired
  private GithubService githubService;

  @Autowired
  private SonarQubeService sonarQubeService;

  @GetMapping(path = "/repository", params = "login")
  @ResponseBody
  public Iterable<GithubRepositoryEntity> getRepositoryByLogin(@RequestParam String login) {
    List<GithubRepository> repositories = githubService.fetchRepositoriesByUsername(login);
    repositories.stream().forEach(repo -> {
      githubService.insertGithubOwnerIsNotExist(repo.getOwner());
      githubService.insertRepository(repo);
    });
    return githubService.getAllRepositories();
  }

  @GetMapping(path = "/user")
  public GithubUserEntry getGithubUser(@RequestParam String login) {
    GithubUser user = githubService.fetchGithubUser(login);
    return githubService.insertUserInformation(user);
  }

  @GetMapping(path = "/organization")
  @ResponseStatus(value = HttpStatus.OK)
  public void getGithubOrganization(@RequestParam String organizationName)
      throws InvalidRemoteException, TransportException, GitAPIException, InterruptedException {
    // GithubOrganization organization =
    // githubService.fetchOrganizations(organizationName);
    githubService.getAllOrganization();
  }
}
