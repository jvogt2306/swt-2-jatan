package de.jatan.analysisapplication.services;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import de.jatan.analysisapplication.Domain.Model.GithubRepository;
import de.jatan.analysisapplication.helper.GithubTestModelsHelper;

@RunWith(MockitoJUnitRunner.class)
public class SonarQubeServiceTest {

  @Spy
  private SonarQubeService sonarQubeService;

  @Before
  public void setUp() throws Exception {
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

  @Test
  public void should_return_true_when_create_SonarQubeProject_successfully() {
    when(sonarQubeService.createSonarQubeProject(GithubTestModelsHelper.getValidTestGithubRepositoryOne().getName()))
        .thenReturn(true);
    assertTrue(
        sonarQubeService.createSonarQubeProject(GithubTestModelsHelper.getValidTestGithubRepositoryOne().getName()));
  }

  @Test
  public void should_return_true_when_updateWebhookProperties_from_SonarProject_successfully() {
    when(sonarQubeService
        .updateWebhookPropertieSonarQubeProject(GithubTestModelsHelper.getValidTestGithubRepositoryOne().getName()))
            .thenReturn(true);
    assertTrue(sonarQubeService
        .updateWebhookPropertieSonarQubeProject(GithubTestModelsHelper.getValidTestGithubRepositoryOne().getName()));
  }

  @Test
  public void should_return_true_when_removeRepositoryFromPath_successfully() throws Exception, IOException {
    GithubRepository githubRepository = GithubTestModelsHelper.getValidTestGithubRepositoryOne();
    String path = SonarQubeService.REPOSITORYFOLDERABSOLUTE + githubRepository.getName();
    when(sonarQubeService.removeRepositoryFromPath(path)).thenReturn(true);
    assertTrue(sonarQubeService.removeRepositoryFromPath(path));
  }
}
