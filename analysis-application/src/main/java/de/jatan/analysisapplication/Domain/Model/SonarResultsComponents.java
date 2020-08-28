package de.jatan.analysisapplication.Domain.Model;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@SuppressWarnings("unused")
public class SonarResultsComponents {
  private SonarResultsMeasures[] measures;

  private String qualifier;

  private String name;

  private String id;

  private String key;

}
