package de.jatan.analysisapplication.Domain.Model;

public class SonarQubeProjectComponents {

  private String visibility;

  private String organization;

  private String qualifier;

  private String name;

  private String lastAnalysisDate;

  private String key;

  private String revision;

  public String getVisibility() {
    return visibility;
  }

  public void setVisibility(String visibility) {
    this.visibility = visibility;
  }

  public String getOrganization() {
    return organization;
  }

  public void setOrganization(String organization) {
    this.organization = organization;
  }

  public String getQualifier() {
    return qualifier;
  }

  public void setQualifier(String qualifier) {
    this.qualifier = qualifier;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastAnalysisDate() {
    return lastAnalysisDate;
  }

  public void setLastAnalysisDate(String lastAnalysisDate) {
    this.lastAnalysisDate = lastAnalysisDate;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getRevision() {
    return revision;
  }

  public void setRevision(String revision) {
    this.revision = revision;
  }

  @Override
  public String toString() {
    return "ClassPojo [visibility = " + visibility + ", organization = " + organization + ", qualifier = " + qualifier
        + ", name = " + name + ", lastAnalysisDate = " + lastAnalysisDate + ", key = " + key + ", revision = "
        + revision + "]";
  }
}
