package de.jatan.analysisapplication.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import de.jatan.analysisapplication.helper.SonarQubeTestModelsHelper;

@RunWith(SpringRunner.class)
public class SonarQubeResponseTest {

  @Test
  public void sonarQubeResponse_branch_contains_name() throws Exception {
    assertEquals("master", SonarQubeTestModelsHelper.getValidSonarQubeResponse().getBranch().getName());
  }

  @Test
  public void sonarQubeResponse_branch_contains_type() throws Exception {
    assertEquals("BRANCH", SonarQubeTestModelsHelper.getValidSonarQubeResponse().getBranch().getType());
  }

  @Test
  public void sonarQubeResponse_branch_contains_is_main() throws Exception {

    assertEquals("true", SonarQubeTestModelsHelper.getValidSonarQubeResponse().getBranch().getIsMain());
  }

  @Test
  public void sonarQubeResponse_branch_contains_url() throws Exception {
    assertEquals("http://localhost:9000/dashboard?id=sipgateio-basicauth-java",
        SonarQubeTestModelsHelper.getValidSonarQubeResponse().getBranch().getUrl());
  }

  @Test
  public void sonarQubeResponse_project_contains_key() throws Exception {
    assertEquals("sipgateio-basicauth-java",
        SonarQubeTestModelsHelper.getValidSonarQubeResponse().getProject().getKey());
  }

  @Test
  public void sonarQubeResponse_project_contains_name() throws Exception {
    assertEquals("sipgateio-basicauth-java",
        SonarQubeTestModelsHelper.getValidSonarQubeResponse().getProject().getName());
  }

  @Test
  public void sonarQubeResponse_project_contains_url() throws Exception {
    assertEquals("http://localhost:9000/dashboard?id=sipgateio-basicauth-java",
        SonarQubeTestModelsHelper.getValidSonarQubeResponse().getProject().getUrl());
  }

  @Test
  public void sonarQubeResponse_qualitygate_contains_conditions() throws Exception {
    assertEquals(4, SonarQubeTestModelsHelper.getValidSonarQubeResponse().getQualityGate().getConditions().length);
  }

  @Test
  public void sonarQubeResponse_qualitygate_contains_name() throws Exception {
    assertEquals("Sonar way", SonarQubeTestModelsHelper.getValidSonarQubeResponse().getQualityGate().getName());
  }

  @Test
  public void sonarQubeResponse_qualitygate_contains_status() throws Exception {
    assertEquals("OK", SonarQubeTestModelsHelper.getValidSonarQubeResponse().getQualityGate().getStatus());
  }

  @Test
  public void sonarQubeResponse__contains_analysed_at() throws Exception {
    assertEquals("2020-08-08T09:59:53+0000", SonarQubeTestModelsHelper.getValidSonarQubeResponse().getAnalysedAt());
  }

  @Test
  public void sonarQubeResponse__contains_changedAt() throws Exception {
    assertEquals("2020-08-08T09:59:53+0000", SonarQubeTestModelsHelper.getValidSonarQubeResponse().getChangedAt());
  }

  @Test
  public void sonarQubeResponse_contains_revision() throws Exception {
    assertEquals("b0bb5c78b9bd48aaa746fef673a9ef535ff82cca",
        SonarQubeTestModelsHelper.getValidSonarQubeResponse().getRevision());
  }

  @Test
  public void sonarQubeResponse_contains_server_url() throws Exception {
    assertEquals("http://localhost:9000", SonarQubeTestModelsHelper.getValidSonarQubeResponse().getServerUrl());
  }

  @Test
  public void sonarQubeResponse_contains_status() throws Exception {
    assertEquals("SUCCESS", SonarQubeTestModelsHelper.getValidSonarQubeResponse().getStatus());
  }

  @Test
  public void sonarQubeResponse_contains_taskid() throws Exception {
    assertEquals("AXPNgzwxPOfeEGx4QfAV", SonarQubeTestModelsHelper.getValidSonarQubeResponse().getTaskId());
  }
}
