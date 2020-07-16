package de.jatan.analysisapplication.services;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.client.RestTemplate;
import de.jatan.analysisapplication.Database.entities.GithubOwnerEntity;
import de.jatan.analysisapplication.Database.entities.GithubOrganizationEntry;
import de.jatan.analysisapplication.Database.entities.GithubRepositoryEntity;
import de.jatan.analysisapplication.Database.entities.GithubUserEntry;
import de.jatan.analysisapplication.Database.repositories.GithubOwnerRepository;
import de.jatan.analysisapplication.Database.repositories.GithubOrganizationRepository;
import de.jatan.analysisapplication.Database.repositories.GithubRepositoryRepository;
import de.jatan.analysisapplication.Database.repositories.GithubUserRepository;
import de.jatan.analysisapplication.Domain.Model.GithubOrganization;
import de.jatan.analysisapplication.Domain.Model.GithubOwner;
import de.jatan.analysisapplication.Domain.Model.GithubRepository;
import de.jatan.analysisapplication.Domain.Model.GithubUser;
import de.jatan.analysisapplication.controller.DTO.RepositoryDTO;
import io.github.cdimascio.dotenv.Dotenv;

@Service
public class GithubService {

  @Autowired
  private GithubOwnerRepository githubOwnerRepository;
  @Autowired
  private GithubUserRepository githubUserRepository;
  @Autowired
  private GithubRepositoryRepository githubRepositoryRepository;
  @Autowired
  private GithubOrganizationRepository githubOrganizationRepository;

  public GithubUser fetchGithubUser(String githubUsername) {
    RestTemplate restTemplate = new RestTemplate();
    GithubUser githubUser = restTemplate.getForObject("https://api.github.com/users/" + githubUsername,
        GithubUser.class);
    return githubUser;
  }

  public List<GithubRepository> fetchRepositoriesByUsername(String githubUsername) {
    RestTemplate restTemplate = new RestTemplate();
    List<GithubRepository> list = Arrays.asList(restTemplate
        .getForObject("https://api.github.com/users/" + githubUsername + " /repos", GithubRepository[].class));
    return list;
  }

  public List<GithubRepository> fetchRepositoriesByURL(String url) {
    RestTemplate restTemplate = new RestTemplate();
    List<GithubRepository> list = Arrays.asList(restTemplate.getForObject(url, GithubRepository[].class));
    return list;
  }

  public GithubOrganization fetchOrganizations(String organizationName) {
    RestTemplate restTemplate = new RestTemplate();
    GithubOrganization organization = restTemplate.getForObject("https://api.github.com/orgs/" + organizationName,
        GithubOrganization.class);
    return organization;
  }

  public boolean cloneRepository(RepositoryDTO dto) throws InvalidRemoteException, TransportException, GitAPIException {
    Dotenv dotenv = Dotenv.load();
    String githubUsername = dotenv.get("githubUsername");
    String githubPassword = dotenv.get("githubPassword");
    String applicationPath = System.getProperty("user.dir");
    FileSystemUtils
        .deleteRecursively(new File(applicationPath + "/src/main/resources/repositories/" + dto.projectName));
    Git.cloneRepository().setURI(dto.url)
        .setDirectory(new File(applicationPath + "/src/main/resources/repositories/" + dto.projectName))
        .setCredentialsProvider(new UsernamePasswordCredentialsProvider(githubUsername, githubPassword)).call();
    return true;
  }

  public GithubOwnerEntity insertRepository(GithubRepository repo) {
    GithubOwnerEntity githubOwner = githubOwnerRepository.findByLogin(repo.getOwner().getLogin()).get(0);
    GithubOrganizationEntry githubOrganization = githubOrganizationRepository.findByLogin(repo.getOwner().getLogin())
        .get(0);
    GithubRepositoryEntity repository = new GithubRepositoryEntity();
    repository.setDescription(modifyStringnWhenToForLongDatabase(repo.getDescription()));
    repository.setUrl(repo.getUrl());
    repository.setName(repo.getName());
    repository.setGithub_organization(githubOrganization);
    repository.setGithub_owner(githubOwner);
    githubOrganization.addRepository(repository);
    githubOwner.addRepository(repository);
    return githubOwnerRepository.save(githubOwner);
  }

  public GithubOwnerEntity insertGithubOwner(GithubOwner owner) {
    GithubOwnerEntity githubOwner = new GithubOwnerEntity();
    githubOwner.setLogin(owner.getLogin());
    githubOwner.setUrl(owner.getUrl());
    return githubOwnerRepository.save(githubOwner);
  }

  public GithubUserEntry insertUserInformation(GithubUser user) {
    GithubUserEntry userEntry = new GithubUserEntry();
    userEntry.setName(user.getName());
    userEntry.setLogin(user.getLogin());
    return githubUserRepository.save(userEntry);
  }

  public GithubOrganizationEntry insertGithubOrganization(GithubOrganization organization) {
    GithubOrganizationEntry n = new GithubOrganizationEntry();
    n.setDescription(organization.getDescription());
    n.setUrl(organization.getUrl());
    n.setLogin(organization.getLogin());
    return githubOrganizationRepository.save(n);
  }

  public String modifyStringnWhenToForLongDatabase(String str) {
    if (str != null && str.length() > 100) {
      return str.substring(0, 100);
    }
    return str;
  }

  public void insertGithubOwnerIsNotExist(GithubOwner owner) {
    if (!isOwnerExists(owner)) {
      insertGithubOwner(owner);
    }
  }

  public void insertGithubOrganizationIsNotExist(GithubOrganization organization) {
    if (!isOrganizationExists(organization)) {
      insertGithubOrganization(organization);
    }
  }

  private boolean isOrganizationExists(GithubOrganization organization) {
    if (githubOrganizationRepository.findByLogin(organization.getLogin()).size() > 0) {
      return true;
    }
    return false;
  }

  public boolean isOwnerExists(GithubOwner owner) {
    if (githubOwnerRepository.findByLogin(owner.getLogin()).size() > 0) {
      return true;
    }
    return false;
  }

  public Iterable<GithubOrganizationEntry> getAllOrganization() {
    return githubOrganizationRepository.findAll();
  }

  public Iterable<GithubUserEntry> getUserAllInformation() {
    return githubUserRepository.findAll();
  }

  public Iterable<GithubRepositoryEntity> getAllRepositories() {
    return githubRepositoryRepository.findAll();
  }
}
