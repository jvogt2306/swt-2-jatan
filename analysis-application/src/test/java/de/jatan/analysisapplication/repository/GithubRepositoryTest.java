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
import de.jatan.analysisapplication.Database.repositories.GithubOrganizationRepository;
import de.jatan.analysisapplication.Database.repositories.GithubOwnerRepository;
import de.jatan.analysisapplication.Database.repositories.GithubRepositoryRepository;
import de.jatan.analysisapplication.helper.GithubTestModelsHelper;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GithubRepositoryTest {

  @MockBean
  private GithubRepositoryRepository mockGithubRepositoryRepository;

  @MockBean
  private GithubOwnerRepository mockGithubOwnerRepository;

  @MockBean
  private GithubOrganizationRepository mockGithubOrganizationRepository;

  @Before
  public void setUp() {
    when(mockGithubRepositoryRepository.findById(1))
        .thenReturn(Optional.of(GithubTestModelsHelper.getValidTestGithubRepositoryEntity()));
  }

  @Test
  public void whenId_thenRepositoryShouldBeFound() {
    assertEquals(mockGithubRepositoryRepository.findById(1).get().getDescription(), "Example Repository Description");
  }
}
