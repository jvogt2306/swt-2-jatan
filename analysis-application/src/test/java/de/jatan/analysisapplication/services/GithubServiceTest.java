package de.jatan.analysisapplication.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.web.client.RestTemplate;

import de.jatan.analysisapplication.Database.entities.GithubOrganizationEntry;
import de.jatan.analysisapplication.Database.entities.GithubOwnerEntity;
import de.jatan.analysisapplication.Database.entities.GithubRepositoryEntity;
import de.jatan.analysisapplication.Database.repositories.GithubOrganizationRepository;
import de.jatan.analysisapplication.Database.repositories.GithubOwnerRepository;
import de.jatan.analysisapplication.Database.repositories.GithubRepositoryRepository;
import de.jatan.analysisapplication.Domain.Model.GithubOwner;
import de.jatan.analysisapplication.helper.GithubTestModelsHelper;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
public class GithubServiceTest {

  @InjectMocks
  private GithubService githubService;

  @Mock
  private GithubOwnerRepository mockGithubOwnerRepository;

  @Mock
  private GithubRepositoryRepository mockGithubRepositoryRepository;

  @Mock
  private GithubOrganizationRepository mockGithubOrganizationRepository;

  @Mock
  private RestTemplate restTemplate;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void should_return_GithubOwnerEntitiy_when_insert_completly() throws Exception {
    GithubOwnerEntity githubOwnerEntity = GithubTestModelsHelper.getValidTestGithubOwnerEntity();
    when(mockGithubOwnerRepository.save(any(GithubOwnerEntity.class))).thenReturn(githubOwnerEntity);
    GithubOwner githubOwner = GithubTestModelsHelper.getValidTestGithubOwner();
    GithubOwnerEntity response = githubService.insertGithubOwner(githubOwner);
    assertEquals(response.getLogin(), githubOwnerEntity.getLogin());
  }

  @Test
  public void should_return_GithubOrganizationEntitiy_when_insert_completly() throws Exception {
    GithubOrganizationEntry githubOrganizationEntry = GithubTestModelsHelper.getValidTestGithubOrganizationEntity();
    when(mockGithubOrganizationRepository.save(any(GithubOrganizationEntry.class))).thenReturn(githubOrganizationEntry);

    GithubOrganizationEntry response = githubService
        .insertGithubOrganization(GithubTestModelsHelper.getValidTestGithubOrganization());
    assertEquals(response.getLogin(), githubOrganizationEntry.getLogin());
  }

  @Test
  public void should_return_GithubRepositoryEntitiy_when_insert_completly() throws Exception {
    GithubRepositoryEntity githubRepositoryEntity = GithubTestModelsHelper.getValidTestGithubRepositoryEntity();

    when(mockGithubOwnerRepository.findByLogin(anyString()))
        .thenReturn(GithubTestModelsHelper.getValidTestGithubOwnerEntity());
    when(mockGithubOrganizationRepository.findByLogin(anyString()))
        .thenReturn(GithubTestModelsHelper.getValidTestGithubOrganizationEntity());
    when(mockGithubRepositoryRepository.save(any(GithubRepositoryEntity.class))).thenReturn(githubRepositoryEntity);
    GithubRepositoryEntity response = githubService
        .insertRepository(GithubTestModelsHelper.getValidTestGithubRepositoryOne());
    assertEquals(response.getName(), githubRepositoryEntity.getName());
  }

  @Test
  public void modifyStringnWhenToForLongDatabase_return_same_string_when_size_smaller_equals_100() throws Exception {

    String notToLongForDatabase = "";

    while (notToLongForDatabase.length() < 99) {
      notToLongForDatabase += "a";
    }
    this.githubService.modifyStringnWhenToForLongDatabase(notToLongForDatabase);
    assertEquals(this.githubService.modifyStringnWhenToForLongDatabase(notToLongForDatabase).length(),
        notToLongForDatabase.length());
  }

  @Test
  public void modifyStringnWhenToForLongDatabase_returns_maximum_characters_when_size_bigger_100() throws Exception {
    String notToLongForDatabase = "";
    while (notToLongForDatabase.length() < 150) {
      notToLongForDatabase += "a";
    }
    this.githubService.modifyStringnWhenToForLongDatabase(notToLongForDatabase);
    assertEquals(this.githubService.modifyStringnWhenToForLongDatabase(notToLongForDatabase).length(), 100);
  }

}
