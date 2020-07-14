package de.jatan.analysisapplication.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.jatan.analysisapplication.Database.entities.OrganizationInformationEntry;
import de.jatan.analysisapplication.Database.entities.RepositoryInformationEntity;
import de.jatan.analysisapplication.Database.entities.UserInformationEntry;
import de.jatan.analysisapplication.Domain.Model.GithubOrganization;
import de.jatan.analysisapplication.Domain.Model.GithubOwner;
import de.jatan.analysisapplication.Domain.Model.GithubRepository;
import de.jatan.analysisapplication.Domain.Model.GithubUser;
import de.jatan.analysisapplication.services.GithubService;

@RestController
@RequestMapping(path = "/github")
public class GithubController {

  @Autowired
  private GithubService githubService;

  @GetMapping(path = "/repository", params = "login")
  @ResponseBody
  public Iterable<RepositoryInformationEntity> getRepositoryByLogin(@RequestParam String login) {
    List<GithubRepository> repositories = githubService.fetchRepositories(login);
    repositories.stream().forEach(repo -> {
      GithubOwner owner = repo.getOwner();
      if (!githubService.isOwnerExists(owner)) {
        githubService.insertGithubOwner(owner);
      }
      githubService.insertRepository(repo);
    });
    return githubService.getAllRepositories();
  }

  @GetMapping(path = "/user")
  public UserInformationEntry getGithubUser(@RequestParam String login) {
    GithubUser user = githubService.fetchGithubUser(login);
    return githubService.insertUserInformation(user);
  }

  @GetMapping(path = "/organization")
  public OrganizationInformationEntry getGithubOrganization(@RequestParam String organizationName) {
    GithubOrganization organization = githubService.fetchOrganizations(organizationName);
    return githubService.insertGithubOrgansiation(organization);
  }
}
