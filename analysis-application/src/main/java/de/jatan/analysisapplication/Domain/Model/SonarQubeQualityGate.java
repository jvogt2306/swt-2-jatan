package de.jatan.analysisapplication.Domain.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@SuppressWarnings("unused")
public class SonarQubeQualityGate {

  private String name;

  private SonarQubeConditions[] conditions;

  private String status;
}
