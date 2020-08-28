package de.jatan.analysisapplication.models;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import de.jatan.analysisapplication.Domain.Model.SonarResultsComponents;
import de.jatan.analysisapplication.Domain.Model.SonarResultsMeasures;

@RunWith(SpringRunner.class)
public class SonarResultsComponentsTest {

  private SonarResultsComponents sonarResultsComponents;

  @Before
  public void setUp() throws Exception {
    this.sonarResultsComponents = new SonarResultsComponents(
        new SonarResultsMeasures[] { new SonarResultsMeasures("code_smells", "4"),
            new SonarResultsMeasures("bugs", "0"), new SonarResultsMeasures("sqale_debt_ratio", "4.6"),
            new SonarResultsMeasures("sqale_index", "32"), new SonarResultsMeasures("ncloc", "23") },
        "TRK", "sipgateio-basicauth-java", "AXPNlAM4POfeEGx4QfD8", "sipgateio-basicauth-java");

  }

  @Test
  public void SonarResultsComponents_contains_measures() throws Exception {
    assertEquals(5, sonarResultsComponents.getMeasures().length);
  }

  @Test
  public void SonarResultsComponents_contains_qualifier() throws Exception {
    assertEquals("TRK", sonarResultsComponents.getQualifier());
  }

  @Test
  public void SonarResultsComponents_contains_name() throws Exception {
    assertEquals("sipgateio-basicauth-java", sonarResultsComponents.getName());
  }

  @Test
  public void SonarResultsComponents_contains_id() throws Exception {
    assertEquals("AXPNlAM4POfeEGx4QfD8", sonarResultsComponents.getId());
  }

  @Test
  public void SonarResultsComponents_contains_key() throws Exception {
    assertEquals("sipgateio-basicauth-java", sonarResultsComponents.getKey());
  }

}
