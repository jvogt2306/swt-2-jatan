package de.jatan.analysisapplication.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // need this in Spring Boot test
public class AnalysisControllerTests {

  @Autowired
  private MockMvc mockMvc;

  @InjectMocks
  private AnalysisController analysisController;

  @Before
  public void setUp() throws Exception {
    this.mockMvc = MockMvcBuilders.standaloneSetup(analysisController).build();
  }

  @Test
  public void should_be_true_when_application_is_running() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/health")).andDo(print())
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
  public void should_not_throw_exception_when_create_analysis() {
    // @GetMapping(path="/createAnalysis"
  }


  
}
