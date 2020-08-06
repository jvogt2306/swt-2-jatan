package de.jatan.analysisapplication.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.concurrent.CompletableFuture;

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
import de.jatan.analysisapplication.services.GithubService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // need this in Spring Boot test
public class AnalysisControllerTests {

  @Autowired
  private MockMvc mockMvc;

  @InjectMocks
  private AnalysisController analysisController;

  @Mock
  private GithubService githubService;

  @Before
  public void setUp() throws Exception {
    this.mockMvc = MockMvcBuilders.standaloneSetup(analysisController).build();
    doThrow(GithubOrganisationNotFoundException.class).when(githubService)
        .fetchOrganizations("invalidOrganizationName");
    /*
     * when(githubService.fetchRepositoriesByURL("")) .thenReturn(null);
     * when(("invalidOrganizationName")).thenReturn(value);
     */
  }

  @Test
  public void should_be_true_when_application_is_running() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/jatan/health")).andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("Status: true"));
  }

  @Test
  public void should_be_return_all_organisation_analysis_details() {
    // path = "/searchAnalysisDetails")
  }

  @Test
  public void should_be_return_all_organization_analysis() {
    // path = "/searchAnalysis")
  }

  @Test
  public void should_not_throw_exception_when_create_analysis() throws Exception {
    /*
     * mockMvc .perform(
     * MockMvcRequestBuilders.get("/jatan/createAnalysis").param("organizationName",
     * "validOrganizationName")) .andExpect(MockMvcResultMatchers.status().isOk())
     */
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
