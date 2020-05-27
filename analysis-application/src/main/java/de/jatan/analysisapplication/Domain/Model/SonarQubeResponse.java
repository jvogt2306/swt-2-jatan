package de.jatan.analysisapplication.Domain.Model;

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

  public SonarQubeQualityGate getQualityGate() {
    return qualityGate;
  }

  public void setQualityGate(SonarQubeQualityGate qualityGate) {
    this.qualityGate = qualityGate;
  }

  public String getServerUrl() {
    return serverUrl;
  }

  public void setServerUrl(String serverUrl) {
    this.serverUrl = serverUrl;
  }

  public String getAnalysedAt() {
    return analysedAt;
  }

  public void setAnalysedAt(String analysedAt) {
    this.analysedAt = analysedAt;
  }

  public SonarQubeProject getProject() {
    return project;
  }

  public void setProject(SonarQubeProject project) {
    this.project = project;
  }

  public String getChangedAt() {
    return changedAt;
  }

  public void setChangedAt(String changedAt) {
    this.changedAt = changedAt;
  }

  public SonarQubeBranch getBranch() {
    return branch;
  }

  public void setBranch(SonarQubeBranch branch) {
    this.branch = branch;
  }

  public String getTaskId() {
    return taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getRevision() {
    return revision;
  }

  public void setRevision(String revision) {
    this.revision = revision;
  }

  @Override
  public String toString() {
    return "ClassPojo [qualityGate = " + qualityGate + ", serverUrl = " + serverUrl + ", analysedAt = " + analysedAt
        + ", project = " + project + ", changedAt = " + changedAt + ", branch = " + branch + ", taskId = " + taskId
        + ", status = " + status + ", revision = " + revision + "]";
  }
}
