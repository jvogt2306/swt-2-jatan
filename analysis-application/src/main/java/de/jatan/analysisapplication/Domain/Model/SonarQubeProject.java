package de.jatan.analysisapplication.Domain.Model;

public class SonarQubeProject {

  private String name;

  private String key;

  private String url;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public String toString() {
    return "ClassPojo [name = " + name + ", key = " + key + ", url = " + url + "]";
  }
}
