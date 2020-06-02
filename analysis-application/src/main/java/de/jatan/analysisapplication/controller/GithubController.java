package de.jatan.analysisapplication.controller;

import java.util.List;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.jatan.analysisapplication.Database.entities.OrganizationInformationEntry;
import de.jatan.analysisapplication.Database.entities.UserInformationEntry;
import de.jatan.analysisapplication.Database.repositories.OrganizationInformationRepository;
import de.jatan.analysisapplication.Database.repositories.UserInformationEntryRepository;
import de.jatan.analysisapplication.Domain.Model.GithubOrganization;
import de.jatan.analysisapplication.Domain.Model.GithubRepository;
import de.jatan.analysisapplication.Domain.Model.GithubUser;
import de.jatan.analysisapplication.services.GithubService;

@RestController
@RequestMapping(path = "/github")
public class GithubController {

  @Autowired
  private GithubService githubService;
  
  @Autowired
  private UserInformationEntryRepository userRepository;

  @Autowired
  private OrganizationInformationRepository organizationRepository;

  @GetMapping(path = "/repos")
  @ResponseBody
  public List<GithubRepository> getRepository(@RequestParam String login) {
    return githubService.getRepositories(login);
  }

  @GetMapping(path = "/user")
  public UserInformationEntry getGithubUser(@RequestParam String login) {
    GithubUser user = githubService.getGithubUser(login);
    Stream.of(user).forEach(u -> System.out.println(u.toString()));
    UserInformationEntry n = new UserInformationEntry();
    n.setName(user.getName());
    n.setLogin(user.getLogin());
    userRepository.save(n);
    return n;
  }

  @GetMapping(path = "/organizations")
  public GithubOrganization getGithubOrganization(@RequestParam String organizationName) {
    GithubOrganization organization = githubService.getOrganizations(organizationName);
   Stream.of(organization).forEach(u -> System.out.println(u.toString()));
   OrganizationInformationEntry n = new OrganizationInformationEntry();
    n.setDescription(organization.getDescription());
    n.setUrl(organization.getUrl());
    n.setLogin(organization.getLogin());
    organizationRepository.save(n);
  
    return organization;
  }
}
