package de.jatan.analysisapplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.jatan.analysisapplication.Database.entities.OrganizationInformationEntry;
import de.jatan.analysisapplication.Database.repositories.OrganizationInformationRepository;
import de.jatan.analysisapplication.controller.DTO.OrganizationInformationDTO;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(path = "/organizations")
public class OrganizationController {

  @Autowired
  private OrganizationInformationRepository organizations;

  @PostMapping(path = "/add")
  public @ResponseBody OrganizationInformationEntry addNewUser(@RequestBody OrganizationInformationDTO oDto) {
    OrganizationInformationEntry n = new OrganizationInformationEntry();
    n.setDescription(oDto.description);
    n.setUrl(oDto.url);
    organizations.save(n);
    System.out.println("Added");
    return n;
  }

  @GetMapping(path = "/all")
  public @ResponseBody Iterable<OrganizationInformationEntry> getAllUsers() {
    return organizations.findAll();
  }

}
