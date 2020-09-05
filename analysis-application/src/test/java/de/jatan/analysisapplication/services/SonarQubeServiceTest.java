package de.jatan.analysisapplication.services;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import de.jatan.analysisapplication.Domain.Model.GithubRepository;
import de.jatan.analysisapplication.helper.GithubTestModelsHelper;
import de.jatan.analysisapplication.helper.SonarQubeTestModelsHelper;

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

  @Test
  public void should_return_true_when_create_SonarQubeProject_successfully() {
    when(sonarQubeService.createSonarQubeProject(GithubTestModelsHelper.getValidTestGithubRepositoryOne().getName()))
        .thenReturn(true);
    assertTrue(
        sonarQubeService.createSonarQubeProject(GithubTestModelsHelper.getValidTestGithubRepositoryOne().getName()));
    // public boolean createSonarQubeProject
  }

  @Test
  public void should_return_true_when_updateWebhookProperties_from_SonarProject_successfully() {
    when(sonarQubeService
        .updateWebhookPropertieSonarQubeProject(GithubTestModelsHelper.getValidTestGithubRepositoryOne().getName()))
            .thenReturn(true);
    assertTrue(sonarQubeService
        .updateWebhookPropertieSonarQubeProject(GithubTestModelsHelper.getValidTestGithubRepositoryOne().getName()));
    // public boolean updateWebhookPropertieSonarQubeProject(String projectName)
  }

  @Test
  public void should_return_true_when_scanRespositoryInSonarqube_successfully()
      throws IOException, InterruptedException {
    GithubRepository githubRepository = GithubTestModelsHelper.getValidTestGithubRepositoryOne();
    String path = SonarQubeService.REPOSITORYFOLDERABSOLUTE + githubRepository.getName();
    when(sonarQubeService.scanRespositoryInSonarqube(path)).thenReturn(true);
    assertTrue(sonarQubeService.scanRespositoryInSonarqube(path));
  }

  @Test
  public void should_throw_IOException_when_scanRespositoryInSonarqube_isFailed() throws InterruptedException {
  }

  @Test
  public void should_throw_InterruptedException_when_scanRespositoryInSonarqube_isFailed() throws IOException {
  }

  public void should_return_true_when_removeRepositoryFromSonarqube_successfully() throws Exception {
    when(sonarQubeService.removeRepositoryFromSonarQube(SonarQubeTestModelsHelper.getValidSonarQubeProject()))
        .thenReturn(true);
    assertTrue(sonarQubeService.removeRepositoryFromSonarQube(SonarQubeTestModelsHelper.getValidSonarQubeProject()));
    // public boolean removeRepositoryFromSonarQube(SonarQubeProject project)
  }

  public void should_return_true_when_removeRepositoryFromPath_successfully() throws Exception, IOException {
    GithubRepository githubRepository = GithubTestModelsHelper.getValidTestGithubRepositoryOne();
    String path = SonarQubeService.REPOSITORYFOLDERABSOLUTE + githubRepository.getName();
    when(sonarQubeService.removeRepositoryFromPath(path)).thenReturn(true);
    assertTrue(sonarQubeService.removeRepositoryFromPath(path));
    // public boolean removeRepositoryFromPath(String path) throws IOException }
  }

  @Test
  public void should_throw_IOException_when_removeRepositoryFromPath_failed() throws Exception, IOException {
  }

  @Test
  public void should_true_when_createSonarPropertiesFile_successfully() throws Exception, IOException {
    GithubRepository githubRepository = GithubTestModelsHelper.getValidTestGithubRepositoryOne();
    String path = SonarQubeService.REPOSITORYFOLDERABSOLUTE + githubRepository.getName();

    when(sonarQubeService.createSonarPropertiesFile(path, githubRepository.getName(), githubRepository.getLanguage()))
        .thenReturn(true);
    assertTrue(
        sonarQubeService.createSonarPropertiesFile(path, githubRepository.getName(), githubRepository.getLanguage()));
  }

  @Test
  public void should_throws_IOException_when_createSonarPropertiesFile_failed() throws Exception, IOException {
  }

  // private boolean checkIfProjectExist(String projectName)
  // private boolean checkIfWebhookExist(String projectName)
  // private boolean writeSonarProperties(BufferedWriter writer, String
  // projectName, String language) throws IOException
  // private boolean executeProcesses(ProcessBuilder processBuilder) throws
  // IOException, InterruptedException

}
