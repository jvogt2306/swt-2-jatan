package de.jatan.analysisapplication.services;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import de.jatan.analysisapplication.helper.SonarQubeResultTestModelHelper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class SonarQubeResultsServiceTest {

  @Autowired
  private SonarQubeResultsService sonarQubeResultsService;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

  }

  @Test
  public void should_get_code_smells_from_SonarqubeResultMeasures() throws Exception {
    assertTrue(sonarQubeResultsService.getCodeSmells(SonarQubeResultTestModelHelper.getValidSonarResultsMeasures())
        .equals("10"));
  }

  @Test
  public void should_get_bugs_from_SonarqubeResultMeasures() throws Exception {
    assertTrue(
        sonarQubeResultsService.getBugs(SonarQubeResultTestModelHelper.getValidSonarResultsMeasures()).equals("20"));
  }

  @Test
  public void should_get_complexity_from_SonarqubeResultMeasures() throws Exception {
    assertTrue(sonarQubeResultsService.getComplexity(SonarQubeResultTestModelHelper.getValidSonarResultsMeasures())
        .equals("2000"));
  }

  @Test
  public void should_get_duplicated_lines_from_SonarqubeResultMeasures() throws Exception {
    assertTrue(sonarQubeResultsService.getDuplicatedLines(SonarQubeResultTestModelHelper.getValidSonarResultsMeasures())
        .equals("10"));
  }

  @Test
  public void should_get_ncloc_from_SonarqubeResultMeasures() throws Exception {
    assertTrue(sonarQubeResultsService.getLinesOfCode(SonarQubeResultTestModelHelper.getValidSonarResultsMeasures())
        .equals("40"));
  }

  @Test
  public void should_get_reliability_rating_from_SonarqubeResultMeasures() throws Exception {
    assertTrue(sonarQubeResultsService
        .getReliabilityRating(SonarQubeResultTestModelHelper.getValidSonarResultsMeasures()).equals("1.0"));
  }

  @Test
  public void should_get_security_rating_from_SonarqubeResultMeasures() throws Exception {
    assertTrue(sonarQubeResultsService.getSecurityRating(SonarQubeResultTestModelHelper.getValidSonarResultsMeasures())
        .equals("0.2"));
  }

  @Test
  public void should_get_sqale_index_from_SonarqubeResultMeasures() throws Exception {
    assertTrue(sonarQubeResultsService.getSqaleIndex(SonarQubeResultTestModelHelper.getValidSonarResultsMeasures())
        .equals("30"));
  }

  @Test
  public void should_get_violations_from_SonarqubeResultMeasures() throws Exception {
    assertTrue(sonarQubeResultsService.getViolations(SonarQubeResultTestModelHelper.getValidSonarResultsMeasures())
        .equals("9"));
  }

  @Test
  public void should_get_vulnerabilities_from_SonarqubeResultMeasures() throws Exception {
    assertTrue(sonarQubeResultsService.getVulnerabilities(SonarQubeResultTestModelHelper.getValidSonarResultsMeasures())
        .equals("50"));
  }
}
