package de.jatan.analysisapplication.Domain.Model;

public class SonarQubeQualityGate {

  private String name;

  private SonarQubeConditions[] conditions;

  private String status;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public SonarQubeConditions[] getConditions() {
    return conditions;
  }

  public void setConditions(SonarQubeConditions[] conditions) {
    this.conditions = conditions;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "ClassPojo [name = " + name + ", conditions = " + conditions + ", status = " + status + "]";
  }
}
