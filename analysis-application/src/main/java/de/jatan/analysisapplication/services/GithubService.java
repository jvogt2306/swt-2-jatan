package de.jatan.analysisapplication.services;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import de.jatan.analysisapplication.Domain.Model.GithubOrganization;
import de.jatan.analysisapplication.Domain.Model.GithubRepository;
import de.jatan.analysisapplication.Domain.Model.GithubUser;

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

  public GithubOrganization getOrganizations(String organizationName) {
    RestTemplate restTemplate = new RestTemplate();
    GithubOrganization organization = restTemplate.getForObject("https://api.github.com/orgs/" + organizationName,
        GithubOrganization.class);
    return organization;
  }
}
