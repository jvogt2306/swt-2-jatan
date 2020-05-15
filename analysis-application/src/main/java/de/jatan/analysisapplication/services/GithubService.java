package de.jatan.analysisapplication.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import de.jatan.analysisapplication.Domain.Model.GithubRepository;
import de.jatan.analysisapplication.Domain.Model.GithubUser;

@Service
public class GithubService {

  public GithubUser getGithubUser() {
    RestTemplate restTemplate = new RestTemplate();
    GithubUser githubUser = restTemplate.getForObject("https://api.github.com/users/jvogt2306", GithubUser.class);
    return githubUser;

  }

  public List<GithubRepository> getRepositories() {
    RestTemplate restTemplate = new RestTemplate();
    List<GithubRepository> list = Arrays
        .asList(restTemplate.getForObject("https://api.github.com/users/jvogt2306/repos", GithubRepository[].class));
    System.out.println(list.size());
    return list;
  }
}
