package de.jatan.analysisapplication.Domain.Model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@SuppressWarnings("unused")
public class SonarQubeProjectResponse {

  private SonarQubeProjectComponents[] components;

}
