package de.jatan.analysisapplication.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.jatan.analysisapplication.Database.entities.OrganizationInformationEntry;
import de.jatan.analysisapplication.Database.entities.RepositoryInformation;
import de.jatan.analysisapplication.Database.entities.UserInformationEntry;
import de.jatan.analysisapplication.Database.repositories.OrganizationInformationRepository;
import de.jatan.analysisapplication.Database.repositories.RepositoryInformationEntry;
import de.jatan.analysisapplication.Database.repositories.UserInformationEntryRepository;
import de.jatan.analysisapplication.Domain.Model.GithubOrganization;
import de.jatan.analysisapplication.Domain.Model.GithubRepository;
import de.jatan.analysisapplication.Domain.Model.GithubUser;
import de.jatan.analysisapplication.services.GithubService;

@RestController
@RequestMapping(path = "/github")
public class GithubController {

  @Autowired
  private GithubService githubService;
  @Autowired
  private UserInformationEntryRepository userRepository;
  @Autowired
  private OrganizationInformationRepository organizationRepository;
  @Autowired
  private RepositoryInformationEntry repositoryInformation;

  @GetMapping(path = "/repository", params = "login")
  @ResponseBody
  public Iterable<RepositoryInformation> getRepositoryByLogin(@RequestParam String login) {
    List<GithubRepository> repositories = githubService.getRepositories(login);
    repositories.stream().forEach(repo -> insertRepositoryToDB(repo));
    return repositoryInformation.findAll();
  }

  @GetMapping(path = "/all")
  public @ResponseBody Iterable<RepositoryInformation> getAllRepositories() {
    return repositoryInformation.findAll();
  }

  @GetMapping(path = "/user")
  public UserInformationEntry getGithubUser(@RequestParam String login) {
    GithubUser user = githubService.getGithubUser(login);
    UserInformationEntry userEntry = new UserInformationEntry();
    userEntry.setName(user.getName());
    userEntry.setLogin(user.getLogin());
    return userRepository.save(userEntry);
  }

  @GetMapping(path = "/organization")
  public OrganizationInformationEntry getGithubOrganization(@RequestParam String organizationName) {
    GithubOrganization organization = githubService.getOrganizations(organizationName);
    OrganizationInformationEntry n = new OrganizationInformationEntry();
    n.setDescription(organization.getDescription());
    n.setUrl(organization.getUrl());
    n.setLogin(organization.getLogin());
    return organizationRepository.save(n);
  }

  private void insertRepositoryToDB(GithubRepository repo) {
    RepositoryInformation n = new RepositoryInformation();
    n.setDescription(repo.getDescription());
    n.setUrl(repo.getUrl());
    n.setName(repo.getName());
    repositoryInformation.save(n);
  }

  @GetMapping(path = "/db/organizations")
  public @ResponseBody Iterable<OrganizationInformationEntry> getAllUsers() {
    return organizationRepository.findAll();
  }

  @GetMapping(path = "/db/user")
  public @ResponseBody Iterable<UserInformationEntry> getGithubUser() {
    return userRepository.findAll();
  }
}
