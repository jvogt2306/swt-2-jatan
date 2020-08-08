package de.jatan.analysisapplication.Domain.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class SonarQubeBranch {
  private String isMain;
  private String name;
  private String type;
  private String url;
}
