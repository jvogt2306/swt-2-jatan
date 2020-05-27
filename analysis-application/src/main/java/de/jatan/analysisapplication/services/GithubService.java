package de.jatan.analysisapplication.services;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import de.jatan.analysisapplication.Domain.Model.GithubRepository;
import de.jatan.analysisapplication.Domain.Model.GithubUser;
import de.jatan.analysisapplication.controller.DTO.RepositoryDTO;

@Service
public class GithubService {

  public GithubUser getGithubUser(String githubUsername) {
    RestTemplate restTemplate = new RestTemplate();
    GithubUser githubUser = restTemplate.getForObject("https://api.github.com/users/" + githubUsername,
        GithubUser.class);
    return githubUser;
  }

  public List<GithubRepository> getRepositories(String githubUsername) {
    RestTemplate restTemplate = new RestTemplate();
    List<GithubRepository> list = Arrays.asList(restTemplate
        .getForObject("https://api.github.com/users/" + githubUsername + " /repos", GithubRepository[].class));
    return list;
  }

  public String cloneRepository(RepositoryDTO dto) throws InvalidRemoteException, TransportException, GitAPIException {
    // Git.cloneRepository().setURI("https://github.com/eclipse/jgit.git").setDirectory(new
    // File("/path/to/repo")).call();

    Git.cloneRepository().setURI("https://github.com/jvogt2306/billman.git")
        .setDirectory(new File("/home/jan/git/swt-2-jatan/analysis-application/src/main/resources/repositories"))
        .call();

    return "suchen ..." + dto.url;
  }
}
