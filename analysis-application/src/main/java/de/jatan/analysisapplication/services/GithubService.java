package de.jatan.analysisapplication.services;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.client.RestTemplate;
import de.jatan.analysisapplication.Domain.Model.GithubRepository;
import de.jatan.analysisapplication.Domain.Model.GithubUser;
import de.jatan.analysisapplication.controller.DTO.RepositoryDTO;
import io.github.cdimascio.dotenv.Dotenv;

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

  public boolean cloneRepository(RepositoryDTO dto) throws InvalidRemoteException, TransportException, GitAPIException {
    Dotenv dotenv = Dotenv.load();
    String githubUsername = dotenv.get("githubUsername");
    String githubPassword = dotenv.get("githubPassword");
    String applicationPath = System.getProperty("user.dir");
    FileSystemUtils.deleteRecursively(new File(applicationPath + "/src/main/resources/repositories/" + dto.projectName));
    Git.cloneRepository().setURI(dto.url)
        .setDirectory(new File(applicationPath + "/src/main/resources/repositories/" + dto.projectName))
        .setCredentialsProvider(new UsernamePasswordCredentialsProvider(githubUsername, githubPassword)).call();
    return true;
  }
}
