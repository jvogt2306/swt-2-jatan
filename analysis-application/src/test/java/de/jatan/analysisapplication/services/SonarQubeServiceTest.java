package de.jatan.analysisapplication.services;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SonarQubeServiceTest {

  @Mock
  private SonarQubeService sonarQubeService;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void should_return_true_when_scanRepository_successfully() throws Exception {
    when(sonarQubeService.scanRepository("CorrectOrganization", "Java")).thenReturn(true);
    Assert.assertTrue(sonarQubeService.scanRepository("CorrectOrganization", "Java"));
  }

  @Test
  public void should_not_throw_Exception_when_create_valid_SonarQube_Project() throws Exception {
    String projectName = "TestProject";
    when(sonarQubeService.createSonarQubeProject(projectName)).thenReturn(true);
    boolean sonarQubeProjectExist = sonarQubeService.createSonarQubeProject(projectName);
    assertTrue(sonarQubeProjectExist);
  }

  @Test
  public void should_not_throw_Exception_when_updateWebhookPropertie_on_SonarQubeProject() throws Exception {
    String projectName = "TestProject";
    when(sonarQubeService.updateWebhookPropertieSonarQubeProject(projectName)).thenReturn(true);
    boolean webhookExist = sonarQubeService.updateWebhookPropertieSonarQubeProject(projectName);
    assertTrue(webhookExist);
  }
}
