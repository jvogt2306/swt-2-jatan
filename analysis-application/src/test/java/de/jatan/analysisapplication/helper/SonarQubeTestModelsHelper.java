package de.jatan.analysisapplication.helper;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;

import de.jatan.analysisapplication.Database.entities.GithubOrganizationEntry;
import de.jatan.analysisapplication.Database.entities.GithubOwnerEntity;
import de.jatan.analysisapplication.Database.entities.GithubRepositoryEntity;
import de.jatan.analysisapplication.Database.entities.SonarqubeMeasuresEntity;
import de.jatan.analysisapplication.Domain.Model.SonarQubeBranch;
import de.jatan.analysisapplication.Domain.Model.SonarQubeConditions;
import de.jatan.analysisapplication.Domain.Model.SonarQubeProject;
import de.jatan.analysisapplication.Domain.Model.SonarQubeQualityGate;
import de.jatan.analysisapplication.Domain.Model.SonarQubeResponse;
import de.jatan.analysisapplication.Domain.Model.SonarQubeResults;
import de.jatan.analysisapplication.Domain.Model.SonarResultsComponents;
import de.jatan.analysisapplication.Domain.Model.SonarResultsMeasures;

public final class SonarQubeTestModelsHelper {

  public final static SonarQubeResponse getValidSonarQubeResponse() {
    return new SonarQubeResponse(
        new SonarQubeQualityGate("Sonar way",
            new SonarQubeConditions[] {
                new SonarQubeConditions("1", "new_reliability_rating", null, null, "GREATER_THAN", "NO_VALUE"),
                new SonarQubeConditions("1", "new_security_rating", "null", "null", "GREATER_THAN", "NO_VALUE"),
                new SonarQubeConditions("1", "new_maintainability_rating", null, null, "GREATER_THAN", "NO_VALUE"),
                new SonarQubeConditions("3", "new_duplicated_lines_density", null, null, "GREATER_THAN", "NO_VALUE") },
            "OK"),
        "http://localhost:9000", "2020-08-08T09:59:53+0000",
        new SonarQubeProject("sipgateio-basicauth-java", "sipgateio-basicauth-java",
            "http://localhost:9000/dashboard?id=sipgateio-basicauth-java"),
        "2020-08-08T09:59:53+0000",
        new SonarQubeBranch("true", "master", "BRANCH", "http://localhost:9000/dashboard?id=sipgateio-basicauth-java"),
        "AXPNgzwxPOfeEGx4QfAV", "SUCCESS", "b0bb5c78b9bd48aaa746fef673a9ef535ff82cca");
  }

  public final static SonarQubeResults getValidSonarQubeResult() {
    SonarQubeResults sonarQubeResults = new SonarQubeResults();
    SonarResultsComponents sonarResultsComponents = new SonarResultsComponents(
        new SonarResultsMeasures[] { new SonarResultsMeasures("code_smells", "10"),
            new SonarResultsMeasures("bugs", "20"), new SonarResultsMeasures("sqale_index", "30"),
            new SonarResultsMeasures("ncloc", "40"), new SonarResultsMeasures("reliability_rating", "1.0"),
            new SonarResultsMeasures("violations", "9"), new SonarResultsMeasures("complexity", "2000"),
            new SonarResultsMeasures("duplicated_lines", "10"), new SonarResultsMeasures("security_rating", "0.2"),
            new SonarResultsMeasures("vulnerabilities", "50") },
        "TRK", "sipgateio-basicauth-java", "AXPNlAM4POfeEGx4QfD8", "sipgateio-basicauth-java");

    sonarQubeResults.setComponent(sonarResultsComponents);
    return sonarQubeResults;
  }

  public final static SonarqubeMeasuresEntity getValidSonarQubeMeasureEntity() {
    GithubRepositoryEntity githubRepositoryEntity = new GithubRepositoryEntity();
    GithubOwnerEntity githubOwnerEntity = new GithubOwnerEntity();
    GithubOrganizationEntry githubOrganizationEntry = new GithubOrganizationEntry();
    githubRepositoryEntity.setId(1L);
    githubRepositoryEntity.setDescription("Example Repository Description");
    githubRepositoryEntity.setLanguage("Java");
    githubRepositoryEntity.setName("sipgateio-basicauth-java");
    githubRepositoryEntity.setUrl("https://example.de");
    githubRepositoryEntity.setGithub_organization(githubOrganizationEntry);
    githubRepositoryEntity.setGithub_owner(githubOwnerEntity);
    githubOwnerEntity.addRepository(githubRepositoryEntity);
    githubOwnerEntity.setId(1L);
    githubOwnerEntity.setLogin("sipgate-io");
    githubOwnerEntity.setUrl("https://owner-example.de");
    githubOrganizationEntry.setId(1L);
    githubOrganizationEntry.setDescription("Organization Description");
    githubOrganizationEntry.setLogin("sipgate-io");
    githubOrganizationEntry.setUrl("https://organization-example.de");
    githubOrganizationEntry.addRepository(githubRepositoryEntity);
    githubOrganizationEntry.setCreateDateTime(LocalDateTime.now());
    githubOrganizationEntry.setUpdateDateTime(LocalDateTime.now());

    SonarqubeMeasuresEntity sonarqubeMeasuresEntity = new SonarqubeMeasuresEntity();
    sonarqubeMeasuresEntity.setId(19L);
    sonarqubeMeasuresEntity.setBugs("20");
    sonarqubeMeasuresEntity.setSqale_index("30");
    sonarqubeMeasuresEntity.setProject("sipgateio-basicauth-java");
    sonarqubeMeasuresEntity.setDuplicated_lines("10");
    sonarqubeMeasuresEntity.setNcloc("40");
    sonarqubeMeasuresEntity.setCode_smells("10");
    sonarqubeMeasuresEntity.setReliability_rating("1.0");
    sonarqubeMeasuresEntity.setComplexity("2000");
    sonarqubeMeasuresEntity.setVulnerabilities("50");
    sonarqubeMeasuresEntity.setViolations("9");
    sonarqubeMeasuresEntity.setSecurity_rating("0.2");
    sonarqubeMeasuresEntity.setRepository(githubRepositoryEntity);
    sonarqubeMeasuresEntity.setUpdateDateTime(LocalDateTime.now());
    sonarqubeMeasuresEntity.setCreateDateTime(LocalDateTime.now());
    ;
    return sonarqubeMeasuresEntity;
  }

  public final static ProcessBuilder getValidProcessBuilder(String path) {
    ProcessBuilder processBuilder = new ProcessBuilder();
    processBuilder.redirectErrorStream(true);
    processBuilder.directory(new File(path));
    processBuilder.command("sonar-scanner", "-Dproject.settings=./sonar-project.properties",
        "-Dsonar.host.url=http://192.168.1.32:9000", "-Dsonar.login=cabdd35cbe9411b527a70d15c261b68055100c8d");
    processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
    return processBuilder;
  }

  public static SonarQubeProject getValidSonarQubeProject() {
    return new SonarQubeProject("TestProject", "TestProject", "https://testproject.de/sonarqube/project");
  }
}
