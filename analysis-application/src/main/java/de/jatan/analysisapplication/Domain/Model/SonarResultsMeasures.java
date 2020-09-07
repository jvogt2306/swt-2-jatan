package de.jatan.analysisapplication.Domain.Model;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@SuppressWarnings("unused")
public class SonarResultsMeasures {
  private String metric;
  private String value;
}
