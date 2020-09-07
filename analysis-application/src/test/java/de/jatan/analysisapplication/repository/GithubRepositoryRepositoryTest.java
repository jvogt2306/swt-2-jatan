package de.jatan.analysisapplication.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import de.jatan.analysisapplication.Database.entities.GithubRepositoryEntity;
import de.jatan.analysisapplication.Database.repositories.GithubOrganizationRepository;
import de.jatan.analysisapplication.Database.repositories.GithubOwnerRepository;
import de.jatan.analysisapplication.Database.repositories.GithubRepositoryRepository;
import de.jatan.analysisapplication.helper.GithubTestModelsHelper;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GithubRepositoryRepositoryTest {

  @MockBean
  private GithubRepositoryRepository mockGithubRepositoryRepository;

  @MockBean
  private GithubOwnerRepository mockGithubOwnerRepository;

  @MockBean
  private GithubOrganizationRepository mockGithubOrganizationRepository;

  @Test
  public void should_return_RepositoryEntry_when_id_in_repository() {

    GithubRepositoryEntity mockResponse = GithubTestModelsHelper.getValidTestGithubRepositoryEntity();

    when(mockGithubRepositoryRepository.findById(1)).thenReturn(Optional.of(mockResponse));

    Optional<GithubRepositoryEntity> response = mockGithubRepositoryRepository.findById(1);

    assertEquals(response.get().getName(), mockResponse.getName());
    assertEquals(response.get().getCreateDateTime(), mockResponse.getCreateDateTime());
    assertEquals(response.get().getDescription(), mockResponse.getDescription());
    assertEquals(response.get().getGithub_organization(), mockResponse.getGithub_organization());
    assertEquals(response.get().getGithub_owner(), mockResponse.getGithub_owner());
    assertEquals(response.get().getId(), mockResponse.getId());
    assertEquals(response.get().getLanguage(), mockResponse.getLanguage());
    assertEquals(response.get().getUpdateDateTime(), mockResponse.getUpdateDateTime());
    assertEquals(response.get().getUrl(), mockResponse.getUrl());
  }
}
