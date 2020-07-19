package de.jatan.analysisapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.jatan.analysisapplication.Database.entities.GithubOrganizationEntry;
import de.jatan.analysisapplication.Database.entities.GithubRepositoryEntity;
import de.jatan.analysisapplication.services.GithubService;

@RestController
@RequestMapping(path = "/db")
public class DatabaseController {

  @Autowired
  private GithubService githubService;

  @GetMapping(path = "/repositories")
  @ResponseBody
  public Iterable<GithubRepositoryEntity> getRepositories() {
    return githubService.getAllRepositories();
  }

  @GetMapping(path = "/organizations")
  @ResponseStatus(value = HttpStatus.OK)
  public Iterable<GithubOrganizationEntry> getOrganizations() {
    return githubService.getAllOrganization();
  }

  @GetMapping(path = "/owner")
  @ResponseStatus(value = HttpStatus.OK)
  public Iterable<GithubOrganizationEntry> getOwners() {
    return githubService.getAllOrganization();
  }

}
