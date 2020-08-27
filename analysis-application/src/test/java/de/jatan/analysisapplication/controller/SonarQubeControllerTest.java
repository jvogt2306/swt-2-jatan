package de.jatan.analysisapplication.controller;

import static org.mockito.Mockito.when;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import de.jatan.analysisapplication.Domain.Model.SonarQubeBranch;
import de.jatan.analysisapplication.Domain.Model.SonarQubeConditions;
import de.jatan.analysisapplication.Domain.Model.SonarQubeProject;
import de.jatan.analysisapplication.Domain.Model.SonarQubeQualityGate;
import de.jatan.analysisapplication.Domain.Model.SonarQubeResponse;
import de.jatan.analysisapplication.helper.SonarQubeTestModelsHelper;
import de.jatan.analysisapplication.services.SonarQubeResultsService;
import de.jatan.analysisapplication.services.SonarQubeService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SonarQubeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @InjectMocks
  private SonarQubeController sonarQubeController;

  @Mock
  private SonarQubeResultsService sonarQubeResultsService;

  @Mock
  private SonarQubeService sonarQubeService;

  @Before
  public void setUp() throws Exception {
    this.mockMvc = MockMvcBuilders.standaloneSetup(sonarQubeController).build();
  }

  @Test
  public void should_not_throws_exception_when_sonarqubehook_is_received() throws Exception {
    when(sonarQubeResultsService.getResults(SonarQubeTestModelsHelper.getValidSonarQubeResponse()))
        .thenReturn(SonarQubeTestModelsHelper.getValidSonarQubeResult());
    when(sonarQubeResultsService.saveSonarQubeMeasures(SonarQubeTestModelsHelper.getValidSonarQubeResult()))
        .thenReturn(SonarQubeTestModelsHelper.getValidSonarQubeMeasureEntity());
    when(sonarQubeService
        .removeRepositoryFromSonarQube(SonarQubeTestModelsHelper.getValidSonarQubeResponse().getProject()))
            .thenReturn(true);

    Gson g = new Gson();
    String request = g.toJson(SonarQubeTestModelsHelper.getValidSonarQubeResponse(), SonarQubeResponse.class);
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/sonarqube/hook").content(request).contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());

  }
}
