package de.jatan.analysisapplication.Domain.Model;

public class SonarQubeProjectResponse {

  private SonarQubeProjectComponents[] components;

  private SonarQubeProjectPaging paging;

  public SonarQubeProjectComponents[] getComponents() {
    return components;
  }

  public void setComponents(SonarQubeProjectComponents[] components) {
    this.components = components;
  }

  public SonarQubeProjectPaging getPaging() {
    return paging;
  }

  public void setPaging(SonarQubeProjectPaging paging) {
    this.paging = paging;
  }

  @Override
  public String toString() {
    return "ClassPojo [components = " + components + ", paging = " + paging + "]";
  }
}
