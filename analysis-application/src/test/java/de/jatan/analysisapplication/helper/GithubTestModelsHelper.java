package de.jatan.analysisapplication.helper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import de.jatan.analysisapplication.Database.entities.GithubOrganizationEntry;
import de.jatan.analysisapplication.Database.entities.GithubOwnerEntity;
import de.jatan.analysisapplication.Database.entities.GithubRepositoryEntity;
import de.jatan.analysisapplication.Database.entities.JatanAnalysisDetailsEntry;
import de.jatan.analysisapplication.Database.entities.JatanAnalysisEntry;
import de.jatan.analysisapplication.Domain.Model.GithubOrganization;
import de.jatan.analysisapplication.Domain.Model.GithubOwner;
import de.jatan.analysisapplication.Domain.Model.GithubRepository;

public final class GithubTestModelsHelper {

  public final static GithubOrganizationEntry getValidTestGithubOrganizationEntity() {

    GithubRepositoryEntity repositoryEntity = getValidTestGithubRepositoryEntity();

    GithubOrganizationEntry githubOrganizationEntity = new GithubOrganizationEntry();
    githubOrganizationEntity.setDescription("Organization Description");
    githubOrganizationEntity.setLogin("Organization Login");
    githubOrganizationEntity.setUrl("https://organization-example.de");
    githubOrganizationEntity.setId(1L);
    githubOrganizationEntity.addRepository(repositoryEntity);
    return githubOrganizationEntity;
  }

  public final static GithubRepositoryEntity getValidTestGithubRepositoryEntity() {
    GithubRepositoryEntity githubRepositoryEntity = new GithubRepositoryEntity();
    GithubOwnerEntity githubOwnerEntity = new GithubOwnerEntity();
    GithubOrganizationEntry githubOrganizationEntry = new GithubOrganizationEntry();

    githubRepositoryEntity.setId(1L);
    githubRepositoryEntity.setDescription("Example Repository Description");
    githubRepositoryEntity.setLanguage("Java");
    githubRepositoryEntity.setName("Example Repo");
    githubRepositoryEntity.setUrl("https://example.de");
    githubRepositoryEntity.setGithub_organization(githubOrganizationEntry);
    githubRepositoryEntity.setGithub_owner(githubOwnerEntity);
    githubOwnerEntity.addRepository(githubRepositoryEntity);
    githubOwnerEntity.setId(1L);
    githubOwnerEntity.setLogin("OwnerLogin");
    githubOwnerEntity.setUrl("https://owner-example.de");
    githubOrganizationEntry.setId(1L);
    githubOrganizationEntry.setDescription("Organization Description");
    githubOrganizationEntry.setLogin("Organization Login");
    githubOrganizationEntry.setUrl("https://organization-example.de");
    githubOrganizationEntry.addRepository(githubRepositoryEntity);
    return githubRepositoryEntity;
  }

  public final static GithubOwnerEntity getValidTestGithubOwnerEntity() {
    GithubOwnerEntity githubOwnerEntity = new GithubOwnerEntity();
    githubOwnerEntity.addRepository(getValidTestGithubRepositoryEntity());
    githubOwnerEntity.setId(1L);
    githubOwnerEntity.setLogin("OwnerLogin");
    githubOwnerEntity.setUrl("https://owner-example.de");
    return githubOwnerEntity;
  }

  public final static GithubOwner getValidTestGithubOwner() {
    GithubOwner githubOwner = new GithubOwner();
    githubOwner.setLogin("validOrganization");
    githubOwner.setUrl("https://api.github.com/users/validOrganization");
    githubOwner.setHtml_url("https://github.com/validOrganization");
    githubOwner.setFollowers_url("https://api.github.com/users/validOrganization/followers");
    githubOwner.setFollowing_url("https://api.github.com/users/validOrganization/following{/other_user}");
    githubOwner.setSubscriptions_url("https://api.github.com/users/validOrganization/subscriptions");
    githubOwner.setOrganizations_url("https://api.github.com/users/validOrganization/orgs");
    githubOwner.setRepos_url("https://api.github.com/users/validOrganization/repos");
    return githubOwner;
  }

  public final static GithubOrganization getValidTestGithubOrganization() {
    GithubOrganization githubOrganization = new GithubOrganization();
    githubOrganization.setLogin("validOrganization");
    githubOrganization.setName("validOrganization");
    githubOrganization.setUrl("https://api.github.com/orgs/validOrganization");
    githubOrganization.setHtml_url("https://github.com/orgs/validOrganization");
    githubOrganization.setRepos_url("https://api.github.com/orgs/validOrganization/repos");
    githubOrganization.setMembers_url("https://api.github.com/orgs/validOrganization/members{/member}");
    githubOrganization.setDescription(
        "validOrganization isn’t your typical telephony provider. With 200 colleagues, we work together on extraordinary landline and telephony services just for you.");
    return githubOrganization;
  }

  public final static GithubRepository getValidTestGithubRepositoryOne() {
    GithubRepository githubRepository = new GithubRepository();
    githubRepository.setDescription("AWS Lambda function to forward Stream data to Kinesis Firehose");
    githubRepository.setLanguage("JavaScript");
    githubRepository.setOwner(getValidTestGithubOwner());
    githubRepository.setName("lambda-streams-to-firehose");
    githubRepository.setUrl("https://api.github.com/repos/Zeitgold/lambda-streams-to-firehose");
    return githubRepository;
  }

  public final static GithubRepository getValidTestGithubRepositoryTwo() {
    GithubRepository githubRepository = new GithubRepository();
    githubRepository.setDescription("React Native TextInput styled with Material Design.");
    githubRepository.setLanguage("Java");
    githubRepository.setOwner(getValidTestGithubOwner());
    githubRepository.setName("react-native-md-textinput");
    githubRepository.setUrl("https://api.github.com/repos/Zeitgold/react-native-md-textinput");
    return githubRepository;
  }

  public final static JatanAnalysisDetailsEntry getValidTestJatanAnalysisDetailsEntityOne() {
    JatanAnalysisDetailsEntry jatanAnalysisDetailsEntry = new JatanAnalysisDetailsEntry();
    jatanAnalysisDetailsEntry.setLogin("Zeitgold");
    jatanAnalysisDetailsEntry.setName("lambda-streams-to-firehose");
    jatanAnalysisDetailsEntry.setLanguage("JavaScript");
    jatanAnalysisDetailsEntry.setBugs("0");
    jatanAnalysisDetailsEntry.setCodeSmells("14");
    jatanAnalysisDetailsEntry.setLinesOfCode("326");
    jatanAnalysisDetailsEntry.setTechnicalDept("67");
    jatanAnalysisDetailsEntry.setBugsPerLine(0);
    jatanAnalysisDetailsEntry.setCodeSmellsPerLine(0.042);
    jatanAnalysisDetailsEntry.setCodeSmellsPerLine(0);
    jatanAnalysisDetailsEntry.setTechnicalDeptPerLine(0.20);
    return jatanAnalysisDetailsEntry;
  }

  public final static JatanAnalysisDetailsEntry getValidTestJatanAnalysisDetailsEntityTwo() {
    JatanAnalysisDetailsEntry jatanAnalysisDetailsEntry = new JatanAnalysisDetailsEntry();
    jatanAnalysisDetailsEntry.setLogin("Zeitgold");
    jatanAnalysisDetailsEntry.setName("snowplow-enrich");
    jatanAnalysisDetailsEntry.setLanguage("JavaScript");
    jatanAnalysisDetailsEntry.setBugs("0");
    jatanAnalysisDetailsEntry.setCodeSmells("4");
    jatanAnalysisDetailsEntry.setLinesOfCode("404");
    jatanAnalysisDetailsEntry.setTechnicalDept("13");
    jatanAnalysisDetailsEntry.setBugsPerLine(0);
    jatanAnalysisDetailsEntry.setCodeSmellsPerLine(0.099);
    jatanAnalysisDetailsEntry.setCodeSmellsPerLine(0);
    jatanAnalysisDetailsEntry.setTechnicalDeptPerLine(0.0321);
    return jatanAnalysisDetailsEntry;
  }

  public final static JatanAnalysisEntry getValidTestJatanAnalysisEntity() {
    JatanAnalysisEntry jatanAnalysisEntry = new JatanAnalysisEntry();
    jatanAnalysisEntry.setLogin("Zeitgold");
    jatanAnalysisEntry.setBugs(0);
    jatanAnalysisEntry.setCodeSmells(4);
    jatanAnalysisEntry.setTechnicalDept(13);
    jatanAnalysisEntry.setBugsPerLine(0);
    jatanAnalysisEntry.setCodeSmellsPerLine(0.099);
    jatanAnalysisEntry.setTechnicalDeptPerLine(0.0321);
    return jatanAnalysisEntry;
  }

  public final static List<JatanAnalysisEntry> getListOfValidTestJatanAnalysisRepository() {
    return Stream.of(getValidTestJatanAnalysisEntity()).collect(Collectors.toList());
  }

  public final static List<JatanAnalysisDetailsEntry> getListOfValidTestJatanAnalysisDetailsRepository() {
    return Stream.of(getValidTestJatanAnalysisDetailsEntityOne(), getValidTestJatanAnalysisDetailsEntityTwo())
        .collect(Collectors.toList());
  }

  public final static List<GithubRepository> getListOfValidTestGithubRepositories() {
    return Stream.of(getValidTestGithubRepositoryOne(), getValidTestGithubRepositoryTwo()).collect(Collectors.toList());
  }
}
