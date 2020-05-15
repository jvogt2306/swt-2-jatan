package de.jatan.analysisapplication.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import de.jatan.analysisapplication.Domain.Model.Placeholder;

@Service
public class PlaceholderService {

  public Placeholder[] getAllEmployeesJSONWithObect() {
    RestTemplate restTemplate = new RestTemplate();
    Placeholder[] placeholders = restTemplate.getForObject("https://jsonplaceholder.typicode.com/todos",
        Placeholder[].class);
    return placeholders;
  }
}
