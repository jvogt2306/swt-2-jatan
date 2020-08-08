package de.jatan.analysisapplication.Domain.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class SonarQubeProject {
  private String name;
  private String key;
  private String url;
}
