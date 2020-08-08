package de.jatan.analysisapplication.Domain.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class SonarQubeResponse {
  private SonarQubeQualityGate qualityGate;

  private String serverUrl;

  private String analysedAt;

  private SonarQubeProject project;

  private String changedAt;

  private SonarQubeBranch branch;

  private String taskId;

  private String status;

  private String revision;

}
