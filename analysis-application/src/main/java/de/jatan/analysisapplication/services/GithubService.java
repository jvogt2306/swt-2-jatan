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
import de.jatan.analysisapplication.controller.DTO.RepositoryDTO;
import io.github.cdimascio.dotenv.Dotenv;

@Service
public class GithubService {

  @Autowired
  private GithubOwnerRepository githubOwnerRepository;
  @Autowired
  private UserInformationEntryRepository userInformationRepository;
  @Autowired
  private RepositoryInformationRepository repositoryInformationRepository;
  @Autowired
  private OrganizationInformationRepository organizationRepository;

  public GithubUser fetchGithubUser(String githubUsername) {
    RestTemplate restTemplate = new RestTemplate();
    GithubUser githubUser = restTemplate.getForObject("https://api.github.com/users/" + githubUsername,
        GithubUser.class);
    return githubUser;
  }

  public List<GithubRepository> fetchRepositories(String githubUsername) {
    RestTemplate restTemplate = new RestTemplate();
    List<GithubRepository> list = Arrays.asList(restTemplate
        .getForObject("https://api.github.com/users/" + githubUsername + " /repos", GithubRepository[].class));
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
    RepositoryInformationEntity repository = new RepositoryInformationEntity();
    repository.setDescription(repo.getDescription());
    repository.setUrl(repo.getUrl());
    repository.setName(repo.getName());
    repository.setGithub_owner(githubOwner);
    githubOwner.addRepository(repository);
    return githubOwnerRepository.save(githubOwner);
  }

  public GithubOwnerEntity insertGithubOwner(GithubOwner owner) {
    GithubOwnerEntity githubOwner = new GithubOwnerEntity();
    githubOwner.setLogin(owner.getLogin());
    githubOwner.setUrl(owner.getUrl());
    return githubOwnerRepository.save(githubOwner);
  }

  public UserInformationEntry insertUserInformation(GithubUser user) {
    UserInformationEntry userEntry = new UserInformationEntry();
    userEntry.setName(user.getName());
    userEntry.setLogin(user.getLogin());
    return userInformationRepository.save(userEntry);
  }

  public OrganizationInformationEntry insertGithubOrgansiation(GithubOrganization organization) {
    OrganizationInformationEntry n = new OrganizationInformationEntry();
    n.setDescription(organization.getDescription());
    n.setUrl(organization.getUrl());
    n.setLogin(organization.getLogin());
    return organizationRepository.save(n);
  }

  public boolean isOwnerExists(GithubOwner owner) {
    if (githubOwnerRepository.findByLogin(owner.getLogin()).size() > 0) {
      return true;
    }
    return false;
  }

  public Iterable<UserInformationEntry> getUserAllInformation() {
    return userInformationRepository.findAll();
  }

  public Iterable<RepositoryInformationEntity> getAllRepositories() {
    return repositoryInformationRepository.findAll();
  }

}
