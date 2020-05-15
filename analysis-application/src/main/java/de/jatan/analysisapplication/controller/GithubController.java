package de.jatan.analysisapplication.controller;

import java.util.List;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import de.jatan.analysisapplication.Domain.Model.GithubRepository;
import de.jatan.analysisapplication.Domain.Model.GithubUser;
import de.jatan.analysisapplication.services.GithubService;

@RestController
@RequestMapping(path = "/github")
public class GithubController {

  @Autowired
  private GithubService githubService;

  /*
   * Todo: Requestparam (repository UserID)
   */
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
}
