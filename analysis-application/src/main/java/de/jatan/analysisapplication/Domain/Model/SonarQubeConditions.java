package de.jatan.analysisapplication.Domain.Model;

public class SonarQubeConditions {

  private String errorThreshold;

  private String metric;

  private String onLeakPeriod;

  private String value;

  private String operator;

  private String status;

  public String getErrorThreshold() {
    return errorThreshold;
  }

  public void setErrorThreshold(String errorThreshold) {
    this.errorThreshold = errorThreshold;
  }

  public String getMetric() {
    return metric;
  }

  public void setMetric(String metric) {
    this.metric = metric;
  }

  public String getOnLeakPeriod() {
    return onLeakPeriod;
  }

  public void setOnLeakPeriod(String onLeakPeriod) {
    this.onLeakPeriod = onLeakPeriod;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "ClassPojo [errorThreshold = " + errorThreshold + ", metric = " + metric + ", onLeakPeriod = " + onLeakPeriod
        + ", value = " + value + ", operator = " + operator + ", status = " + status + "]";
  }
}
