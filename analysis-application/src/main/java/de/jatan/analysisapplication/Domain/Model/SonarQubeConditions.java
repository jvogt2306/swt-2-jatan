package de.jatan.analysisapplication.Domain.Model;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
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
