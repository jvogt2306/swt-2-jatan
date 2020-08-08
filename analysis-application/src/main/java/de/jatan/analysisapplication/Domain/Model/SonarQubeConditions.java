package de.jatan.analysisapplication.Domain.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class SonarQubeConditions {
  private String errorThreshold;
  private String metric;
  private String onLeakPeriod;
  private String value;
  private String operator;
  private String status;
}
