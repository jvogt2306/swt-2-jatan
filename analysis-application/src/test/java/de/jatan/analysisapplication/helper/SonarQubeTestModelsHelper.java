package de.jatan.analysisapplication.helper;

import de.jatan.analysisapplication.Database.entities.GithubOrganizationEntry;
import de.jatan.analysisapplication.Database.entities.GithubOwnerEntity;
import de.jatan.analysisapplication.Database.entities.GithubRepositoryEntity;
import de.jatan.analysisapplication.Database.entities.SonarqubeMeasuresEntity;
import de.jatan.analysisapplication.Domain.Model.SonarQubeBranch;
import de.jatan.analysisapplication.Domain.Model.SonarQubeConditions;
import de.jatan.analysisapplication.Domain.Model.SonarQubeProject;
import de.jatan.analysisapplication.Domain.Model.SonarQubeQualityGate;
import de.jatan.analysisapplication.Domain.Model.SonarQubeResponse;
import de.jatan.analysisapplication.Domain.Model.SonarResults;
import de.jatan.analysisapplication.Domain.Model.SonarResultsComponents;
import de.jatan.analysisapplication.Domain.Model.SonarResultsMeasures;

public final class SonarQubeTestModelsHelper {

  public final static SonarQubeResponse getValidSonarQubeResponse() {
    SonarQubeResponse sonarQubeResponse = new SonarQubeResponse();
    new SonarQubeResponse(
        new SonarQubeQualityGate("Sonar way",
            new SonarQubeConditions[] {
                new SonarQubeConditions("1", "new_reliability_rating", null, null, "GREATER_THAN", "NO_VALUE"),
                new SonarQubeConditions("1", "new_security_rating", "null", "null", "GREATER_THAN", "NO_VALUE"),
                new SonarQubeConditions("1", "new_maintainability_rating", null, null, "GREATER_THAN", "NO_VALUE"),
                new SonarQubeConditions("80", "new_coverage", null, null, "LESS_THAN", "NO_VALUE"),
                new SonarQubeConditions("3", "new_duplicated_lines_density", null, null, "GREATER_THAN", "NO_VALUE") },
            "OK"),
        "http://localhost:9000", "2020-08-08T09:59:53+0000",
        new SonarQubeProject("sipgateio-basicauth-java", "sipgateio-basicauth-java",
            "http://localhost:9000/dashboard?id=sipgateio-basicauth-java"),
        "2020-08-08T09:59:53+0000",
        new SonarQubeBranch("true", "master", "BRANCH", "http://localhost:9000/dashboard?id=sipgateio-basicauth-java"),
        "AXPNgzwxPOfeEGx4QfAV", "SUCCESS", "b0bb5c78b9bd48aaa746fef673a9ef535ff82cca");
    return sonarQubeResponse;
  }

  public final static SonarResults getValidSonarQubeResult() {
    return new SonarResults(new SonarResultsComponents(
        new SonarResultsMeasures[] { new SonarResultsMeasures("code_smells", "4"),
            new SonarResultsMeasures("bugs", "0"), new SonarResultsMeasures("coverage", "0.0"),
            new SonarResultsMeasures("sqale_debt_ratio", "4.6"), new SonarResultsMeasures("sqale_index", "32"),
            new SonarResultsMeasures("ncloc", "23") },
        "TRK", "sipgateio-basicauth-java", "AXPNlAM4POfeEGx4QfD8", "sipgateio-basicauth-java"));
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

    SonarqubeMeasuresEntity sonarqubeMeasuresEntity = new SonarqubeMeasuresEntity();
    sonarqubeMeasuresEntity.setId(19L);
    sonarqubeMeasuresEntity.setSqale_index("32");
    sonarqubeMeasuresEntity.setProject("sipgateio-basicauth-java");
    sonarqubeMeasuresEntity.setNcloc("23");
    sonarqubeMeasuresEntity.setCoverage("0.0");
    sonarqubeMeasuresEntity.setCode_smells("4");
    sonarqubeMeasuresEntity.setBugs("0");
    sonarqubeMeasuresEntity.setRepository(githubRepositoryEntity);
    return sonarqubeMeasuresEntity;
  }
}
