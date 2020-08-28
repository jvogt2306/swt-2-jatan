package de.jatan.analysisapplication.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import de.jatan.analysisapplication.Domain.Model.SonarQubeProject;

@RunWith(SpringRunner.class)
public class SonarQubeProjectTest {

  public SonarQubeProject sonarQubeProject;

  @Before
  public void setUp() throws Exception {
    this.sonarQubeProject = new SonarQubeProject("sipgateio-basicauth-java", "sipgateio-basicauth-java",
        "http://localhost:9000/dashboard?id=sipgateio-basicauth-java");
  }

  @Test
  public void sonarQubeProject_contains_key() throws Exception {
    assertEquals("sipgateio-basicauth-java", this.sonarQubeProject.getKey());
  }

  @Test
  public void sonarQubeProject_contains_name() throws Exception {
    assertEquals("sipgateio-basicauth-java", this.sonarQubeProject.getName());
  }

  @Test
  public void sonarQubeProject_contains_url() throws Exception {
    assertEquals("http://localhost:9000/dashboard?id=sipgateio-basicauth-java", this.sonarQubeProject.getUrl());
  }

}
