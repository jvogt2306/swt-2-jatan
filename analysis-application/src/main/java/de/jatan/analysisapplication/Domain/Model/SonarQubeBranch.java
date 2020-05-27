package de.jatan.analysisapplication.Domain.Model;

public class SonarQubeBranch {

  private String isMain;

  private String name;

  private String type;

  private String url;

  public String getIsMain() {
    return isMain;
  }

  public void setIsMain(String isMain) {
    this.isMain = isMain;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public String toString() {
    return "ClassPojo [isMain = " + isMain + ", name = " + name + ", type = " + type + ", url = " + url + "]";
  }
}
