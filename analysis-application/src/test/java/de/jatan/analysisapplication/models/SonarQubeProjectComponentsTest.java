package de.jatan.analysisapplication.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import de.jatan.analysisapplication.Domain.Model.SonarQubeProjectComponents;

@RunWith(SpringRunner.class)
public class SonarQubeProjectComponentsTest {

  public SonarQubeProjectComponents sonarQubeProjectComponents;

  @Before
  public void setUp() throws Exception {
    this.sonarQubeProjectComponents = new SonarQubeProjectComponents();
    this.sonarQubeProjectComponents.setKey("validComponent");
    this.sonarQubeProjectComponents.setLastAnalysisDate("2020-08-08T09:59:53+0000");
    this.sonarQubeProjectComponents.setOrganization("default-organization");
    this.sonarQubeProjectComponents.setName("validComponent");
    this.sonarQubeProjectComponents.setQualifier("TRK");
    this.sonarQubeProjectComponents.setVisibility("public");
    this.sonarQubeProjectComponents.setRevision("c71ffbf9266875113946ebe04c92a2df17dc5590");
  }

  @Test
  public void sonarQubeProjectComponents_contains_key() throws Exception {
    assertEquals("validComponent", this.sonarQubeProjectComponents.getKey());
  }

  @Test
  public void sonarQubeProjectComponents_contains_name() throws Exception {
    assertEquals("validComponent", this.sonarQubeProjectComponents.getName());
  }

  @Test
  public void sonarQubeProjectComponents_contains_lastAnalysisDate() throws Exception {
    assertEquals("2020-08-08T09:59:53+0000", this.sonarQubeProjectComponents.getLastAnalysisDate());
  }

  @Test
  public void sonarQubeProjectComponents_contains_organization() throws Exception {
    assertEquals("default-organization", this.sonarQubeProjectComponents.getOrganization());
  }

  @Test
  public void sonarQubeProjectComponents_contains_qualifier() throws Exception {
    assertEquals("TRK", this.sonarQubeProjectComponents.getQualifier());
  }

  @Test
  public void sonarQubeProjectComponents_contains_revision() throws Exception {
    assertEquals("c71ffbf9266875113946ebe04c92a2df17dc5590", this.sonarQubeProjectComponents.getRevision());
  }

  @Test
  public void sonarQubeProjectComponents_contains_visible() throws Exception {
    assertEquals("public", this.sonarQubeProjectComponents.getVisibility());
  }
}
