package de.jatan.analysisapplication.controller;

import java.util.List;
import java.util.stream.Stream;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import de.jatan.analysisapplication.Domain.Model.GithubRepository;
import de.jatan.analysisapplication.Domain.Model.GithubUser;
import de.jatan.analysisapplication.controller.DTO.RepositoryDTO;
import de.jatan.analysisapplication.services.GithubService;
import de.jatan.analysisapplication.services.SonarQubeService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/github")
public class GithubController {

  @Autowired
  private GithubService githubService;
  @Autowired
  private SonarQubeService sonarQubeService;

  @GetMapping(path = "/repos")
  @ResponseBody
  public List<GithubRepository> getRepository(@RequestParam String login) {
    return githubService.getRepositories(login);
  }

  @GetMapping(path = "/user")
  public GithubUser getGithubUser(@RequestParam String login) {
    GithubUser user = githubService.getGithubUser(login);
    Stream.of(user).forEach(u -> System.out.println(u.toString()));
    return user;
  }

  @PostMapping(value = "/repository")
  public String postMethodName(@RequestBody RepositoryDTO entity)
      throws InvalidRemoteException, TransportException, GitAPIException {
    githubService.cloneRepository(entity);
    sonarQubeService.createSonarQubeProject(entity.projectName);
    sonarQubeService.updateWebhookPropertieSonarQubeProject(entity.projectName);
    sonarQubeService.scanRepository(entity.projectName, entity.language);
    return "finished";
  }
}
