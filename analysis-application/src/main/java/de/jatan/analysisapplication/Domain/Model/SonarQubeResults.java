package de.jatan.analysisapplication.Domain.Model;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@SuppressWarnings("unused")
public class SonarQubeResults {
  private SonarResultsComponents component;

}
