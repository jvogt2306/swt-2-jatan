package de.jatan.analysisapplication.controller;

import de.jatan.analysisapplication.Domain.Model.Placeholder;
import de.jatan.analysisapplication.services.PlaceholderService;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/placeholder")
public class PlaceholderController {

  @Autowired
  private PlaceholderService placeholderService;

  @GetMapping(path = "/restListOfObjects")
  public Placeholder[] getAllEmployeesJSONWithObect(Model model) {
    Placeholder[] placeholders = placeholderService.getAllEmployeesJSONWithObect();
    Stream.of(placeholders).forEach(p -> System.out.println(p));
    return placeholders;
  }
}
