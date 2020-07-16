package de.jatan.analysisapplication.controller;

import de.jatan.analysisapplication.Database.entities.GithubUserEntry;
import de.jatan.analysisapplication.Database.repositories.GithubUserRepository;
import de.jatan.analysisapplication.controller.DTO.UserInformationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class UserController {

  @Autowired
  private GithubUserRepository userRepository;

  @PostMapping(path = "/add")
  public @ResponseBody GithubUserEntry addNewUser(@RequestBody UserInformationDTO uDto) {
    GithubUserEntry n = new GithubUserEntry();
    n.setName(uDto.name);
    n.setLogin(uDto.login);
    userRepository.save(n);
    return n;
  }

  @GetMapping(path = "/all")
  public @ResponseBody Iterable<GithubUserEntry> getAllUsers() {
    return userRepository.findAll();
  }


}
