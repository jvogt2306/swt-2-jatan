package de.jatan.analysisapplication.helper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import de.jatan.analysisapplication.Database.entities.GithubOrganizationEntry;
import de.jatan.analysisapplication.Database.entities.GithubOwnerEntity;
import de.jatan.analysisapplication.Database.entities.GithubRepositoryEntity;
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
    githubOwner.setLogin("sipgate");
    githubOwner.setUrl("https://api.github.com/users/sipgate");
    githubOwner.setHtml_url("https://github.com/sipgate");
    githubOwner.setFollowers_url("https://api.github.com/users/sipgate/followers");
    githubOwner.setFollowing_url("https://api.github.com/users/sipgate/following{/other_user}");
    githubOwner.setSubscriptions_url("https://api.github.com/users/sipgate/subscriptions");
    githubOwner.setOrganizations_url("https://api.github.com/users/sipgate/orgs");
    githubOwner.setRepos_url("https://api.github.com/users/sipgate/repos");
    return githubOwner;
  }

  public final static GithubOrganization getValidTestGithubOrganization() {
    GithubOrganization githubOrganization = new GithubOrganization();
    githubOrganization.setLogin("sipgate");
    githubOrganization.setName("sipgate");
    githubOrganization.setUrl("https://api.github.com/orgs/sipgate");
    githubOrganization.setHtml_url("https://github.com/orgs/sipgate");
    githubOrganization.setRepos_url("https://api.github.com/orgs/sipgate/repos");
    githubOrganization.setMembers_url("https://api.github.com/orgs/sipgate/members{/member}");
    githubOrganization.setDescription(
        "sipgate isn’t your typical telephony provider. With 200 colleagues, we work together on extraordinary landline and telephony services just for you.");
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

  public final static List<GithubRepository> getListOfValidTestGithubRepositories() {
    return Stream.of(getValidTestGithubRepositoryOne(), getValidTestGithubRepositoryTwo()).collect(Collectors.toList());
  }
}
