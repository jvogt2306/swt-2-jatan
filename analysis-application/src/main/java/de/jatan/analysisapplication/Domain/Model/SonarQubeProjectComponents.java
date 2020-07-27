package de.jatan.analysisapplication.Domain.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@SuppressWarnings("unused")
public class SonarQubeProjectComponents {

  private String visibility;

  private String organization;

  private String qualifier;

  private String name;

  private String lastAnalysisDate;

  private String key;

  private String revision;

}
