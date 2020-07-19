package de.jatan.analysisapplication.controller.DTO;

public class RepositoryDTO {
  public String language;
  public String branch;
  public String url;
  public String projectName;

  public String getLanguage() {
    return this.language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getBranch() {
    return this.branch;
  }

  public void setBranch(String branch) {
    this.branch = branch;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getProjectName() {
    return this.projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }
}


