package de.jatan.analysisapplication.controller;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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

    String request = "{\"qualityGate\":{\"name\":\"Sonar way\",\"conditions\":[{\"errorThreshold\":\"1\",\"metric\":\"new_reliability_rating\",\"operator\":\"GREATER_THAN\",\"status\":\"NO_VALUE\"},{\"errorThreshold\":\"1\",\"metric\":\"new_security_rating\",\"onLeakPeriod\":\"null\",\"value\":\"null\",\"operator\":\"GREATER_THAN\",\"status\":\"NO_VALUE\"},{\"errorThreshold\":\"1\",\"metric\":\"new_maintainability_rating\",\"operator\":\"GREATER_THAN\",\"status\":\"NO_VALUE\"},{\"errorThreshold\":\"3\",\"metric\":\"new_duplicated_lines_density\",\"operator\":\"GREATER_THAN\",\"status\":\"NO_VALUE\"}],\"status\":\"OK\"},\"serverUrl\":\"http://localhost:9000\",\"analysedAt\":\"2020-08-08T09:59:53+0000\",\"project\":{\"name\":\"sipgateio-basicauth-java\",\"key\":\"sipgateio-basicauth-java\",\"url\":\"http://localhost:9000/dashboard?id\u003dsipgateio-basicauth-java\"},\"changedAt\":\"2020-08-08T09:59:53+0000\",\"branch\":{\"isMain\":\"true\",\"name\":\"master\",\"type\":\"BRANCH\",\"url\":\"http://localhost:9000/dashboard?id\u003dsipgateio-basicauth-java\"},\"taskId\":\"AXPNgzwxPOfeEGx4QfAV\",\"status\":\"SUCCESS\",\"revision\":\"b0bb5c78b9bd48aaa746fef673a9ef535ff82cca\"}";
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/sonarqube/hook").content(request).contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());

  }
}
