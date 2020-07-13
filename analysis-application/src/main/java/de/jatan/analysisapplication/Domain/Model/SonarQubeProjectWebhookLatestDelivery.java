package de.jatan.analysisapplication.Domain.Model;

public class SonarQubeProjectWebhookLatestDelivery {
  private String at;

  private String success;

  private String httpStatus;

  private String id;

  private String durationMs;

  public String getAt() {
    return at;
  }

  public void setAt(String at) {
    this.at = at;
  }

  public String getSuccess() {
    return success;
  }

  public void setSuccess(String success) {
    this.success = success;
  }

  public String getHttpStatus() {
    return httpStatus;
  }

  public void setHttpStatus(String httpStatus) {
    this.httpStatus = httpStatus;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDurationMs() {
    return durationMs;
  }

  public void setDurationMs(String durationMs) {
    this.durationMs = durationMs;
  }

  @Override
  public String toString() {
    return "ClassPojo [at = " + at + ", success = " + success + ", httpStatus = " + httpStatus + ", id = " + id
        + ", durationMs = " + durationMs + "]";
  }
}
