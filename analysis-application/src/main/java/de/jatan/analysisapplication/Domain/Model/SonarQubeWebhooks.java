package de.jatan.analysisapplication.Domain.Model;

public class SonarQubeWebhooks {
  public class Webhooks {
    private SonarQubeProjectWebhookLatestDelivery latestDelivery;

    private String name;

    private String key;

    private String url;

    public SonarQubeProjectWebhookLatestDelivery getLatestDelivery() {
      return latestDelivery;
    }

    public void setLatestDelivery(SonarQubeProjectWebhookLatestDelivery latestDelivery) {
      this.latestDelivery = latestDelivery;
    }

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
      return "ClassPojo [latestDelivery = " + latestDelivery + ", name = " + name + ", key = " + key + ", url = " + url
          + "]";
    }
  }
}
