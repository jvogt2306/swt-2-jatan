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
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import de.jatan.analysisapplication.Database.entities.GithubOwnerEntity;
import de.jatan.analysisapplication.Database.entities.GithubOrganizationEntry;
import de.jatan.analysisapplication.Database.entities.GithubRepositoryEntity;
import de.jatan.analysisapplication.Database.repositories.GithubOwnerRepository;
import de.jatan.analysisapplication.Database.repositories.GithubOrganizationRepository;
import de.jatan.analysisapplication.Database.repositories.GithubRepositoryRepository;
import de.jatan.analysisapplication.Domain.Model.GithubOrganization;
import de.jatan.analysisapplication.Domain.Model.GithubOwner;
import de.jatan.analysisapplication.Domain.Model.GithubRepository;
import de.jatan.analysisapplication.config.GlobalConfiguration;
import de.jatan.analysisapplication.exceptions.GithubOrganisationNotFoundException;

@Service
public class GithubService {

  @Autowired
  private GithubOwnerRepository githubOwnerRepository;
  @Autowired
  private GithubRepositoryRepository githubRepositoryRepository;
  @Autowired
  private GithubOrganizationRepository githubOrganizationRepository;
  @Autowired
  private RestTemplate restTemplate;

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  public List<GithubRepository> fetchRepositoriesByUsername(String githubUsername) {
    List<GithubRepository> list = Arrays.asList(restTemplate()
        .getForObject("https://api.github.com/users/" + githubUsername + " /repos", GithubRepository[].class));
    return list;
  }

  public List<GithubRepository> fetchRepositoriesByURL(String url) {
    List<GithubRepository> list = Arrays.asList(this.restTemplate.getForObject(url, GithubRepository[].class));
    return list;
  }

  public GithubOrganization fetchOrganizations(String organizationName) throws GithubOrganisationNotFoundException {
    try {
      GithubOrganization organization = this.restTemplate
          .getForObject("https://api.github.com/orgs/" + organizationName, GithubOrganization.class);
      return organization;
    } catch (RestClientException e) {
      throw new GithubOrganisationNotFoundException(
          "The organization " + organizationName + " cant be found in Github");
    }
  }

  public boolean cloneRepository(GithubRepository repository)
      throws InvalidRemoteException, TransportException, GitAPIException {
    final String applicationPath = System.getProperty("user.dir");
    FileSystemUtils
        .deleteRecursively(new File(applicationPath + "/src/main/resources/repositories/" + repository.getName()));
    Git.cloneRepository().setURI(repository.getSvn_url())
        .setDirectory(new File(applicationPath + "/src/main/resources/repositories/" + repository.getName()))
        .setCredentialsProvider(new UsernamePasswordCredentialsProvider(GlobalConfiguration.GithubUsername,
            GlobalConfiguration.GithubPassword))
        .call();
    return true;
  }

  public GithubRepositoryEntity insertRepository(GithubRepository repo) {
    GithubOwnerEntity githubOwner = githubOwnerRepository.findByLogin(repo.getOwner().getLogin());
    GithubOrganizationEntry githubOrganization = githubOrganizationRepository.findByLogin(repo.getOwner().getLogin());
    GithubRepositoryEntity repository = new GithubRepositoryEntity();
    repository.setDescription(modifyStringnWhenToForLongDatabase(repo.getDescription()));
    repository.setUrl(repo.getUrl());
    repository.setName(repo.getName());
    repository.setGithub_organization(githubOrganization);
    repository.setGithub_owner(githubOwner);
    repository.setLanguage(repo.getLanguage());
    githubOwner.addRepository(repository);
    githubOrganization.addRepository(repository);
    githubRepositoryRepository.save(repository);
    return repository;
  }

  public GithubOwnerEntity insertGithubOwner(GithubOwner owner) {
    GithubOwnerEntity githubOwner = new GithubOwnerEntity();
    githubOwner.setLogin(owner.getLogin());
    githubOwner.setUrl(owner.getUrl());
    GithubOwnerEntity response = githubOwnerRepository.save(githubOwner);
    return response;
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

  public boolean insertGithubRepositoryIsNotExist(GithubRepository repo) {
    if (!isRepositoryExists(repo)) {
      insertRepository(repo);
    }
    return true;
  }

  public boolean insertGithubOwnerIsNotExist(GithubOwner owner) {
    if (!isOwnerExists(owner)) {
      insertGithubOwner(owner);
    }
    return true;
  }

  public boolean insertGithubOrganizationIsNotExist(GithubOrganization organization) {
    if (!isOrganizationExists(organization)) {
      insertGithubOrganization(organization);
    }
    return true;
  }

  public boolean isRepositoryExists(GithubRepository repository) {
    if (githubRepositoryRepository.findByName(repository.getName()) != null) {
      return true;
    }
    return false;
  }

  public boolean isOrganizationExists(GithubOrganization organization) {
    if (githubOrganizationRepository.findByLogin(organization.getLogin()) != null) {
      return true;
    }
    return false;
  }

  public boolean isOwnerExists(GithubOwner owner) {
    if (githubOwnerRepository.findByLogin(owner.getLogin()) != null) {
      return true;
    }
    return false;
  }

  public Iterable<GithubOrganizationEntry> getAllOrganization() {
    return githubOrganizationRepository.findAll();
  }

  public Iterable<GithubRepositoryEntity> getAllRepositories() {
    return githubRepositoryRepository.findAll();
  }

}
