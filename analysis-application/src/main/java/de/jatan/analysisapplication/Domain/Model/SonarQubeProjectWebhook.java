package de.jatan.analysisapplication.Domain.Model;

public class SonarQubeProjectWebhook {
  private SonarQubeWebhooks[] webhooks;

  public SonarQubeWebhooks[] getWebhooks() {
    return webhooks;
  }

  public void setWebhooks(SonarQubeWebhooks[] webhooks) {
    this.webhooks = webhooks;
  }

  @Override
  public String toString() {
    return "ClassPojo [webhooks = " + webhooks + "]";
  }
}
