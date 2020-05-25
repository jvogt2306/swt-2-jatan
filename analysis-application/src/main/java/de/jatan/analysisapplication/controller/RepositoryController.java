package de.jatan.analysisapplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.jatan.analysisapplication.Database.entities.RepositoryInformation;
import de.jatan.analysisapplication.Database.repositories.RepositoryInformationEntry;
import de.jatan.analysisapplication.controller.DTO.RepositoryInformationDTO;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(path = "/repository")
public class RepositoryController {

  @Autowired
  private RepositoryInformationEntry repositoryInformation;

  @PostMapping(path = "/add")
  public @ResponseBody RepositoryInformation addNewUser(@RequestBody RepositoryInformationDTO rDTO) {
    RepositoryInformation n = new RepositoryInformation();
    n.setDescription(rDTO.description);
    n.setUrl(rDTO.url);
    n.setName(rDTO.name);
    repositoryInformation.save(n);
    return n;
  }

  @GetMapping(path = "/all")
  public @ResponseBody Iterable<RepositoryInformation> getAllUsers() {
    return repositoryInformation.findAll();
  }

}
