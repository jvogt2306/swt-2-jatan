package de.jatan.analysisapplication.Domain.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@SuppressWarnings("unused")
public class SonarQubeConditions {
  private String errorThreshold;
  private String metric;
  private String onLeakPeriod;
  private String value;
  private String operator;
  private String status;
}
