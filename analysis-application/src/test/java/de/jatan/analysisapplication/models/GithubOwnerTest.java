package de.jatan.analysisapplication.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import de.jatan.analysisapplication.helper.GithubTestModelsHelper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GithubOwnerTest {

  @Test
  public void createGithubOwner_contains_id() throws Exception {
    assertEquals("1", GithubTestModelsHelper.getValidTestGithubOwner().getId());
  }

  @Test
  public void createGithubOwner_contains_login() throws Exception {
    assertEquals("OwnerLogin", GithubTestModelsHelper.getValidTestGithubOwner().getLogin());
  }

  @Test
  public void createGithubOwner_contains_repo_url() throws Exception {
    assertEquals("https://api.github.com/users/valid/repos",
        GithubTestModelsHelper.getValidTestGithubOwner().getRepos_url());
  }

  @Test
  public void createGithubOwner_contains_url() throws Exception {
    assertEquals("https://api.github.com/users/validOwner", GithubTestModelsHelper.getValidTestGithubOwner().getUrl());
  }
}
