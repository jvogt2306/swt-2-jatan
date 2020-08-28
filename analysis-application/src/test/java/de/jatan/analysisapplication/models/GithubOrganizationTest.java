package de.jatan.analysisapplication.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import de.jatan.analysisapplication.helper.GithubTestModelsHelper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GithubOrganizationTest {

  @Test
  public void createGithubOrganization_contains_avatar_url() throws Exception {
    assertEquals("https://github.com/avatar", GithubTestModelsHelper.getValidTestGithubOrganization().getAvatar_url());
  }

  @Test
  public void createGithubOrganization_contains_blog() throws Exception {
    assertEquals("https://github.com/blob", GithubTestModelsHelper.getValidTestGithubOrganization().getBlog());
  }

  @Test
  public void createGithubOrganization_contains_company() throws Exception {
    assertEquals("validCompany", GithubTestModelsHelper.getValidTestGithubOrganization().getCompany());
  }

  @Test
  public void createGithubOrganization_contains_create_at() throws Exception {
    assertEquals("2020-06-16T07:34:00Z", GithubTestModelsHelper.getValidTestGithubOrganization().getCreated_at());
  }

  @Test
  public void createGithubOrganization_contains_description() throws Exception {
    assertEquals(
        "validOrganization isn’t your typical telephony provider. With 200 colleagues, we work together on extraordinary landline and telephony services just for you.",
        GithubTestModelsHelper.getValidTestGithubOrganization().getDescription());
  }

  @Test
  public void createGithubOrganization_contains_email() throws Exception {
    assertEquals("valid@email.de", GithubTestModelsHelper.getValidTestGithubOrganization().getEmail());
  }

  @Test
  public void createGithubOrganization_contains_event_url() throws Exception {
    assertEquals("https://github.com/events/validEventUrl",
        GithubTestModelsHelper.getValidTestGithubOrganization().getEvents_url());
  }

  @Test
  public void createGithubOrganization_contains_followers() throws Exception {
    assertEquals("0", GithubTestModelsHelper.getValidTestGithubOrganization().getFollowers());
  }

  @Test
  public void createGithubOrganization_contains_following() throws Exception {
    assertEquals("0", GithubTestModelsHelper.getValidTestGithubOrganization().getFollowing());
  }

  @Test
  public void createGithubOrganization_contains_has_organization_projects() throws Exception {
    assertEquals("true", GithubTestModelsHelper.getValidTestGithubOrganization().getHas_organization_projects());
  }

  @Test
  public void createGithubOrganization_contains_has_repository_projects() throws Exception {
    assertEquals("true", GithubTestModelsHelper.getValidTestGithubOrganization().getHas_repository_projects());
  }

  @Test
  public void createGithubOrganization_contains_hooks_url() throws Exception {
    assertEquals("https://github.com/validHooks",
        GithubTestModelsHelper.getValidTestGithubOrganization().getHooks_url());
  }

  @Test
  public void createGithubOrganization_contains_html_url() throws Exception {
    assertEquals("https://github.com/orgs/validOrganization",
        GithubTestModelsHelper.getValidTestGithubOrganization().getHtml_url());
  }

  @Test
  public void createGithubOrganization_contains_id() throws Exception {
    assertEquals(1L, GithubTestModelsHelper.getValidTestGithubOrganization().getId());
  }

  @Test
  public void createGithubOrganization_contains_is_verified() throws Exception {
    assertEquals("true", GithubTestModelsHelper.getValidTestGithubOrganization().getIs_verified());
  }

  @Test
  public void createGithubOrganization_contains_issues_url() throws Exception {
    assertEquals("https://github.com/Issues", GithubTestModelsHelper.getValidTestGithubOrganization().getIssues_url());
  }

  @Test
  public void createGithubOrganization_contains_location() throws Exception {
    assertEquals("Germany", GithubTestModelsHelper.getValidTestGithubOrganization().getLocation());
  }

  @Test
  public void createGithubOrganization_contains_login() throws Exception {
    assertEquals("validOrganization", GithubTestModelsHelper.getValidTestGithubOrganization().getLogin());
  }

  @Test
  public void createGithubOrganization_contains_members_url() throws Exception {
    assertEquals("https://api.github.com/orgs/validOrganization/members{/member}",
        GithubTestModelsHelper.getValidTestGithubOrganization().getMembers_url());
  }

  @Test
  public void createGithubOrganization_contains_name() throws Exception {
    assertEquals("validOrganization", GithubTestModelsHelper.getValidTestGithubOrganization().getName());
  }

  @Test
  public void createGithubOrganization_contains_node_id() throws Exception {
    assertEquals("validNodeId", GithubTestModelsHelper.getValidTestGithubOrganization().getNode_id());
  }

  @Test
  public void createGithubOrganization_contains_public_gists() throws Exception {
    assertEquals("0", GithubTestModelsHelper.getValidTestGithubOrganization().getPublic_gists());
  }

  @Test
  public void createGithubOrganization_contains_public_members_url() throws Exception {
    assertEquals("https://github.com/public/members",
        GithubTestModelsHelper.getValidTestGithubOrganization().getPublic_members_url());
  }

  @Test
  public void createGithubOrganization_contains_public_repos() throws Exception {
    assertEquals("72", GithubTestModelsHelper.getValidTestGithubOrganization().getPublic_repos());
  }

  @Test
  public void createGithubOrganization_contains_repo_url() throws Exception {
    assertEquals("https://api.github.com/orgs/validOrganization/repos",
        GithubTestModelsHelper.getValidTestGithubOrganization().getRepos_url());
  }

  @Test
  public void createGithubOrganization_contains_type() throws Exception {
    assertEquals("Organization", GithubTestModelsHelper.getValidTestGithubOrganization().getType());
  }

  @Test
  public void createGithubOrganization_contains_updated_at() throws Exception {
    assertEquals("2020-06-16T07:34:00Z", GithubTestModelsHelper.getValidTestGithubOrganization().getUpdated_at());
  }

  @Test
  public void createGithubOrganization_contains_url() throws Exception {
    assertEquals("https://api.github.com/orgs/validOrganization",
        GithubTestModelsHelper.getValidTestGithubOrganization().getUrl());
  }
}
