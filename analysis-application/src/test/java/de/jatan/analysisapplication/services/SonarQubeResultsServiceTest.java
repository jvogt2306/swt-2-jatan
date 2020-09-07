package de.jatan.analysisapplication.services;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.mockito.junit.MockitoJUnitRunner;

import de.jatan.analysisapplication.Database.entities.SonarqubeMeasuresEntity;
import de.jatan.analysisapplication.Database.repositories.SonarQubeMeasuresRepository;
import de.jatan.analysisapplication.Domain.Model.SonarResultsMeasures;
import de.jatan.analysisapplication.helper.SonarQubeResultTestModelHelper;
import de.jatan.analysisapplication.helper.SonarQubeTestModelsHelper;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
public class SonarQubeResultsServiceTest {

  @InjectMocks
  private SonarQubeResultsService sonarQubeResultsService;

  @Mock
  private SonarQubeMeasuresRepository mockSonarQubeMeasuresRepository;

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
  public void should_return_empty_string_when_measures_not_contains_code_smells() throws Exception {
    SonarResultsMeasures[] sonarResultMeasures = SonarQubeResultTestModelHelper.getValidSonarResultsMeasures();
    sonarResultMeasures = ArrayUtils.remove(sonarResultMeasures, 0);
    assertEquals("", sonarQubeResultsService.getCodeSmells(sonarResultMeasures));
  }

  @Test
  public void should_get_bugs_from_SonarqubeResultMeasures() throws Exception {
    assertTrue(
        sonarQubeResultsService.getBugs(SonarQubeResultTestModelHelper.getValidSonarResultsMeasures()).equals("20"));
  }

  @Test
  public void should_return_empty_string_when_measures_not_contains_bugs() throws Exception {
    SonarResultsMeasures[] sonarResultMeasures = SonarQubeResultTestModelHelper.getValidSonarResultsMeasures();
    sonarResultMeasures = ArrayUtils.remove(sonarResultMeasures, 1);
    assertEquals("", sonarQubeResultsService.getBugs(sonarResultMeasures));
  }

  @Test
  public void should_get_complexity_from_SonarqubeResultMeasures() throws Exception {
    assertTrue(sonarQubeResultsService.getComplexity(SonarQubeResultTestModelHelper.getValidSonarResultsMeasures())
        .equals("2000"));
  }

  @Test
  public void should_return_empty_string_when_measures_not_contains_complexity() throws Exception {
    SonarResultsMeasures[] sonarResultMeasures = SonarQubeResultTestModelHelper.getValidSonarResultsMeasures();
    sonarResultMeasures = ArrayUtils.remove(sonarResultMeasures, 6);
    assertEquals("", sonarQubeResultsService.getComplexity(sonarResultMeasures));
  }

  @Test
  public void should_get_duplicated_lines_from_SonarqubeResultMeasures() throws Exception {
    assertTrue(sonarQubeResultsService.getDuplicatedLines(SonarQubeResultTestModelHelper.getValidSonarResultsMeasures())
        .equals("10"));
  }

  @Test
  public void should_return_empty_string_when_measures_not_contains_duplicated_lines() throws Exception {
    SonarResultsMeasures[] sonarResultMeasures = SonarQubeResultTestModelHelper.getValidSonarResultsMeasures();
    sonarResultMeasures = ArrayUtils.remove(sonarResultMeasures, 7);
    assertEquals("", sonarQubeResultsService.getDuplicatedLines(sonarResultMeasures));
  }

  @Test
  public void should_get_ncloc_from_SonarqubeResultMeasures() throws Exception {
    assertTrue(sonarQubeResultsService.getLinesOfCode(SonarQubeResultTestModelHelper.getValidSonarResultsMeasures())
        .equals("40"));
  }

  @Test
  public void should_return_empty_string_when_measures_not_contains_ncloc() throws Exception {
    SonarResultsMeasures[] sonarResultMeasures = SonarQubeResultTestModelHelper.getValidSonarResultsMeasures();
    sonarResultMeasures = ArrayUtils.remove(sonarResultMeasures, 3);
    assertEquals("", sonarQubeResultsService.getLinesOfCode(sonarResultMeasures));
  }

  @Test
  public void should_get_reliability_rating_from_SonarqubeResultMeasures() throws Exception {
    assertTrue(sonarQubeResultsService
        .getReliabilityRating(SonarQubeResultTestModelHelper.getValidSonarResultsMeasures()).equals("1.0"));
  }

  @Test
  public void should_return_empty_string_when_measures_not_contains_reliability_rating() throws Exception {
    SonarResultsMeasures[] sonarResultMeasures = SonarQubeResultTestModelHelper.getValidSonarResultsMeasures();
    sonarResultMeasures = ArrayUtils.remove(sonarResultMeasures, 4);
    assertEquals("", sonarQubeResultsService.getReliabilityRating(sonarResultMeasures));
  }

  @Test
  public void should_get_security_rating_from_SonarqubeResultMeasures() throws Exception {
    assertTrue(sonarQubeResultsService.getSecurityRating(SonarQubeResultTestModelHelper.getValidSonarResultsMeasures())
        .equals("0.2"));
  }

  @Test
  public void should_return_empty_string_when_measures_not_contains_secuity_rating() throws Exception {
    SonarResultsMeasures[] sonarResultMeasures = SonarQubeResultTestModelHelper.getValidSonarResultsMeasures();
    sonarResultMeasures = ArrayUtils.remove(sonarResultMeasures, 8);
    assertEquals("", sonarQubeResultsService.getSecurityRating(sonarResultMeasures));
  }

  @Test
  public void should_get_sqale_index_from_SonarqubeResultMeasures() throws Exception {
    assertTrue(sonarQubeResultsService.getSqaleIndex(SonarQubeResultTestModelHelper.getValidSonarResultsMeasures())
        .equals("30"));
  }

  @Test
  public void should_return_empty_string_when_measures_not_contains_sqale_index() throws Exception {
    SonarResultsMeasures[] sonarResultMeasures = SonarQubeResultTestModelHelper.getValidSonarResultsMeasures();
    sonarResultMeasures = ArrayUtils.remove(sonarResultMeasures, 2);
    assertEquals("", sonarQubeResultsService.getSqaleIndex(sonarResultMeasures));
  }

  @Test
  public void should_get_violations_from_SonarqubeResultMeasures() throws Exception {
    assertTrue(sonarQubeResultsService.getViolations(SonarQubeResultTestModelHelper.getValidSonarResultsMeasures())
        .equals("9"));
  }

  @Test
  public void should_return_empty_string_when_measures_not_contains_violations() throws Exception {
    SonarResultsMeasures[] sonarResultMeasures = SonarQubeResultTestModelHelper.getValidSonarResultsMeasures();
    sonarResultMeasures = ArrayUtils.remove(sonarResultMeasures, 5);
    assertEquals("", sonarQubeResultsService.getViolations(sonarResultMeasures));
  }

  @Test
  public void should_get_vulnerabilities_from_SonarqubeResultMeasures() throws Exception {
    assertTrue(sonarQubeResultsService.getVulnerabilities(SonarQubeResultTestModelHelper.getValidSonarResultsMeasures())
        .equals("50"));
  }

  @Test
  public void should_return_empty_string_when_measures_not_contains_vulnerabilities() throws Exception {
    SonarResultsMeasures[] sonarResultMeasures = SonarQubeResultTestModelHelper.getValidSonarResultsMeasures();
    sonarResultMeasures = ArrayUtils.remove(sonarResultMeasures, 9);
    assertEquals("", sonarQubeResultsService.getVulnerabilities(sonarResultMeasures));
  }

  @Test
  public void should_return_SonarqubeMeasuresEntity_when_new_entity_save_in_repository() throws Exception {
    SonarqubeMeasuresEntity sonarqubeMeasuresEntity = SonarQubeTestModelsHelper.getValidSonarQubeMeasureEntity();
    when(mockSonarQubeMeasuresRepository.save(any(SonarqubeMeasuresEntity.class))).thenReturn(sonarqubeMeasuresEntity);

    final SonarqubeMeasuresEntity response = sonarQubeResultsService
        .saveSonarQubeMeasures(SonarQubeTestModelsHelper.getValidSonarQubeResult());
    assertEquals(sonarqubeMeasuresEntity.getBugs(), response.getBugs());
    assertEquals(sonarqubeMeasuresEntity.getCode_smells(), response.getCode_smells());
    assertEquals(sonarqubeMeasuresEntity.getComplexity(), response.getComplexity());
    assertEquals(sonarqubeMeasuresEntity.getCreateDateTime(), response.getCreateDateTime());
    assertEquals(sonarqubeMeasuresEntity.getDuplicated_lines(), response.getDuplicated_lines());
    assertEquals(sonarqubeMeasuresEntity.getId(), response.getId());
    assertEquals(sonarqubeMeasuresEntity.getNcloc(), response.getNcloc());
    assertEquals(sonarqubeMeasuresEntity.getProject(), response.getProject());
    assertEquals(sonarqubeMeasuresEntity.getReliability_rating(), response.getReliability_rating());
    assertEquals(sonarqubeMeasuresEntity.getRepository(), response.getRepository());
    assertEquals(sonarqubeMeasuresEntity.getSecurity_rating(), response.getSecurity_rating());
    assertEquals(sonarqubeMeasuresEntity.getSqale_index(), response.getSqale_index());
    assertEquals(sonarqubeMeasuresEntity.getUpdateDateTime(), response.getUpdateDateTime());
    assertEquals(sonarqubeMeasuresEntity.getViolations(), response.getViolations());
    assertEquals(sonarqubeMeasuresEntity.getVulnerabilities(), response.getVulnerabilities());
  }
}
