package de.jatan.analysisapplication.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import de.jatan.analysisapplication.Domain.Model.SonarQubeConditions;

@RunWith(SpringRunner.class)
public class SonarQubeConditionsTest {

  public SonarQubeConditions sonarQubeConditions;

  @Before
  public void setUp() throws Exception {
    this.sonarQubeConditions = new SonarQubeConditions("1", "new_reliability_rating", "1", "2", "GREATER_THAN",
        "NO_VALUE");
  }

  @Test
  public void SonarQubeConditions_contains_ErrorThreshold() throws Exception {
    assertEquals("1", sonarQubeConditions.getErrorThreshold());
  }

  @Test
  public void SonarQubeConditions_contains_metric() throws Exception {
    assertEquals("new_reliability_rating", sonarQubeConditions.getMetric());
  }

  @Test
  public void SonarQubeConditions_contains_onLeakPeriod() throws Exception {
    assertEquals("1", sonarQubeConditions.getOnLeakPeriod());
  }

  @Test
  public void SonarQubeConditions_contains_operator() throws Exception {
    assertEquals("GREATER_THAN", sonarQubeConditions.getOperator());
  }

  @Test
  public void SonarQubeConditions_contains_status() throws Exception {
    assertEquals("NO_VALUE", sonarQubeConditions.getStatus());
  }

  @Test
  public void SonarQubeConditions_contains_value() throws Exception {
    assertEquals("2", sonarQubeConditions.getValue());
  }
}
