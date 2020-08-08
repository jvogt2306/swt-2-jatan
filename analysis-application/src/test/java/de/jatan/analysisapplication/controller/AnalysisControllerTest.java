package de.jatan.analysisapplication.controller;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import de.jatan.analysisapplication.exceptions.GithubOrganisationNotFoundException;
import de.jatan.analysisapplication.helper.GithubTestModelsHelper;
import de.jatan.analysisapplication.services.GithubService;
import de.jatan.analysisapplication.services.JatanAnalysisService;
import de.jatan.analysisapplication.services.SonarQubeService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // need this in Spring Boot test
public class AnalysisControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @InjectMocks
  private AnalysisController analysisController;

  @Mock
  private GithubService githubService;

  @Mock
  private SonarQubeService sonarQubeService;

  @Mock
  private JatanAnalysisService jatanAnalysisService;

  @Before
  public void setUp() throws Exception {
    this.mockMvc = MockMvcBuilders.standaloneSetup(analysisController).build();
    doThrow(GithubOrganisationNotFoundException.class).when(githubService)
        .fetchOrganizations("invalidOrganizationName");
  }

  @Test
  public void should_be_true_when_application_is_running() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/jatan/health")).andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("Status: true"));
  }

  @Test
  public void should_be_return_all_organisation_analysis_details() throws Exception {
    when(jatanAnalysisService.getAllCompanyAnalysisDetails())
        .thenReturn(GithubTestModelsHelper.getListOfValidTestJatanAnalysisDetailsRepository());

    mockMvc.perform(MockMvcRequestBuilders.get("/jatan/searchAnalysisDetails"))
        .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$[*]", hasSize(2)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].login", is("Zeitgold")));

  }

  @Test
  public void should_be_return_all_organization_analysis() throws Exception {
    when(jatanAnalysisService.getAllCompaniesAnalysis())
        .thenReturn(GithubTestModelsHelper.getListOfValidTestJatanAnalysisRepository());
    mockMvc.perform(MockMvcRequestBuilders.get("/jatan/searchAnalysis"))
        .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$[*]", hasSize(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].login", is("Zeitgold")));
  }

  @Test
  public void should_not_throw_exception_when_create_analysis() throws Exception {
    when(githubService.fetchOrganizations("validOrganization"))
        .thenReturn(GithubTestModelsHelper.getValidTestGithubOrganization());
    when(githubService.insertGithubOrganizationIsNotExist(GithubTestModelsHelper.getValidTestGithubOrganization()))
        .thenReturn(true);
    when(githubService.fetchRepositoriesByURL(GithubTestModelsHelper.getValidTestGithubOrganization().getRepos_url()))
        .thenReturn(GithubTestModelsHelper.getListOfValidTestGithubRepositories());
    GithubTestModelsHelper.getListOfValidTestGithubRepositories().forEach(repository -> {
      when(githubService.insertGithubOwnerIsNotExist(repository.getOwner())).thenReturn(true);
      when(githubService.insertGithubRepositoryIsNotExist(repository)).thenReturn(true);
      when(sonarQubeService.createSonarQubeProject(repository.getName())).thenReturn(true);
      when(sonarQubeService.updateWebhookPropertieSonarQubeProject(repository.getName())).thenReturn(true);
      when(sonarQubeService.scanRepository(repository.getName(), repository.getLanguage())).thenReturn(true);
    });
    when(githubService.cloneRepository(GithubTestModelsHelper.getListOfValidTestGithubRepositories().get(0)))
        .thenReturn(true);
    when(githubService.cloneRepository(GithubTestModelsHelper.getListOfValidTestGithubRepositories().get(1)))
        .thenReturn(true);

    mockMvc.perform(MockMvcRequestBuilders.get("/jatan/createAnalysis").param("organizationName", "validOrganization"))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void should_throw_GithubOrganisationNotFoundException_when_not_valid_organizationname() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/jatan/createAnalysis").param("organizationName", "invalidOrganizationName"))
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(result -> assertTrue(result.getResolvedException() instanceof GithubOrganisationNotFoundException));
  }
}
