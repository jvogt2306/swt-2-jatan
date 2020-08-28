package de.jatan.analysisapplication.models;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import de.jatan.analysisapplication.Domain.Model.SonarQubeWebhooks;

@RunWith(SpringRunner.class)
public class SonarQubeWebhookTest {

  private SonarQubeWebhooks sonarQubeWebhooks;

  @Before
  public void setUp() throws Exception {
    this.sonarQubeWebhooks = new SonarQubeWebhooks();
    this.sonarQubeWebhooks.setKey("AXPkU_8ubFdWhLNptKRJ");
    this.sonarQubeWebhooks.setName("Sonarqube-Spring");
    this.sonarQubeWebhooks.setUrl("http://192.168.1.32:8080/sonarqube/hook");
  }

  @Test
  public void SonarResultsComponents_contains_key() throws Exception {
    assertEquals("AXPkU_8ubFdWhLNptKRJ", this.sonarQubeWebhooks.getKey());
  }

  @Test
  public void SonarResultsComponents_contains_name() throws Exception {
    assertEquals("Sonarqube-Spring", this.sonarQubeWebhooks.getName());
  }

  @Test
  public void SonarResultsComponents_contains_url() throws Exception {
    assertEquals("http://192.168.1.32:8080/sonarqube/hook", this.sonarQubeWebhooks.getUrl());
  }
}
