package de.jatan.analysisapplication.services;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
public class SonarQubeServiceTest {

  private SonarQubeService sonarQubeService;

  @Test
  public void scanRepository_should_return_true_when_scan_successfully() {
    when(sonarQubeService.scanRepository("CorrectOrganization", "Java")).thenReturn(true);
    Assert.assertTrue(sonarQubeService.scanRepository("CorrectOrganization", "Java"));
  }
}
