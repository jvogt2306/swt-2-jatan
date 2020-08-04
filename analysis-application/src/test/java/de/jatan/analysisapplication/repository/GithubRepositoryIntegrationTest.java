package de.jatan.analysisapplication.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import de.jatan.analysisapplication.Database.entities.GithubOrganizationEntry;
import de.jatan.analysisapplication.Database.entities.GithubOwnerEntity;
import de.jatan.analysisapplication.Database.entities.GithubRepositoryEntity;
import de.jatan.analysisapplication.Database.repositories.GithubOrganizationRepository;
import de.jatan.analysisapplication.Database.repositories.GithubOwnerRepository;
import de.jatan.analysisapplication.Database.repositories.GithubRepositoryRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GithubRepositoryIntegrationTest {

  @MockBean
  private GithubRepositoryRepository mockGithubRepositoryRepository;

  @MockBean
  private GithubOwnerRepository mockGithubOwnerRepository;

  @MockBean
  private GithubOrganizationRepository mockGithubOrganizationRepository;

  @Before
  public void setUp() {
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
    when(mockGithubRepositoryRepository.findById(1)).thenReturn(Optional.of(githubRepositoryEntity));
  }

  @Test
  public void whenId_thenRepositoryShouldBeFound() {
    assertEquals(mockGithubRepositoryRepository.findById(1).get().getDescription(), "Example Repository Description");
  }
}
