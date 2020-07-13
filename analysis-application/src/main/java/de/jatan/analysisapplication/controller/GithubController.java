package de.jatan.analysisapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.jatan.analysisapplication.Database.entities.GithubOwnerEntity;
import de.jatan.analysisapplication.Database.entities.OrganizationInformationEntry;
import de.jatan.analysisapplication.Database.entities.RepositoryInformationEntity;
import de.jatan.analysisapplication.Database.entities.UserInformationEntry;
import de.jatan.analysisapplication.Database.repositories.GithubOwnerRepository;
import de.jatan.analysisapplication.Database.repositories.OrganizationInformationRepository;
import de.jatan.analysisapplication.Database.repositories.RepositoryInformationRepository;
import de.jatan.analysisapplication.Database.repositories.UserInformationEntryRepository;
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
  @Autowired
  private OrganizationInformationRepository organizationRepository;
  @Autowired
  private UserInformationEntryRepository userRepository;
  @Autowired
  private RepositoryInformationRepository repositoryInformation;
  @Autowired
  private GithubOwnerRepository githubOwnerRepository;

  @GetMapping(path = "/repository", params = "login")
  @ResponseBody
  public RepositoryInformationEntity getRepositoryByLogin(@RequestParam String login) {
    List<GithubRepository> repositories = githubService.getRepositories(login);
    GithubRepository oneRepository = repositories.get(0);

    if (!isOwnerExists(oneRepository.getOwner())) {
      GithubOwnerEntity githubOwner = new GithubOwnerEntity();
      githubOwner.setLogin(oneRepository.getOwner().getLogin());
      githubOwner.setUrl(oneRepository.getOwner().getUrl());
      githubOwnerRepository.save(githubOwner);
    }
    RepositoryInformationEntity repoEntity = new RepositoryInformationEntity();
    repoEntity.setLogin(login);
    repoEntity.setName("me");
    insertRepositoryToDB(oneRepository);
    // repositories.stream().forEach(repo -> insertRepositoryToDB(repo));
    return repositoryInformation.save(repoEntity);
  }

  private boolean isOwnerExists(GithubOwner owner) {
    if (githubOwnerRepository.findByLogin(owner.getLogin()).size() > 0) {
      return true;
    }
    return false;
  }

  @GetMapping(path = "/repositories", params = "login")
  @ResponseBody
  public Iterable<RepositoryInformationEntity> getRepositoriesByLogin(@RequestParam String login) {
    List<GithubRepository> repositories = githubService.getRepositories(login);
    repositories.stream().forEach(repo -> insertRepositoryToDB2(repo));
    return repositoryInformation.findAll();
  }

  private void insertRepositoryToDB2(GithubRepository repo) {
    RepositoryInformationEntity n = new RepositoryInformationEntity();
    n.setDescription(repo.getDescription());
    n.setUrl(repo.getUrl());
    n.setName(repo.getName());
    repositoryInformation.save(n);
  }


  @GetMapping(path = "/all")
  public @ResponseBody Iterable<RepositoryInformationEntity> getAllRepositories() {
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

  private RepositoryInformationEntity insertRepositoryToDB(GithubRepository repo) {
    List<GithubOwnerEntity> githubOwner = githubOwnerRepository.findByLogin(repo.getOwner().getLogin());
    GithubOwnerEntity firstGithubOwner = githubOwner.get(0);
    RepositoryInformationEntity repository = new RepositoryInformationEntity();
    repository.setDescription(repo.getDescription());
    repository.setUrl(repo.getUrl());
    repository.setName(repo.getName());
    //firstGithubOwner.addRepository_information(repository);
    githubOwnerRepository.save(firstGithubOwner);
    return repository;
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
