package de.jatan.analysisapplication.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
import static org.assertj.core.api.Assertions.assertThat;

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
    final GithubOwnerEntity githubOwnerEntity = GithubTestModelsHelper.getValidTestGithubOwnerEntity();
    when(mockGithubOwnerRepository.save(any(GithubOwnerEntity.class))).thenReturn(githubOwnerEntity);
    final GithubOwner githubOwner = GithubTestModelsHelper.getValidTestGithubOwner();
    final GithubOwnerEntity response = githubService.insertGithubOwner(githubOwner);
    assertEquals(response.getLogin(), githubOwnerEntity.getLogin());
  }

  @Test
  public void should_return_GithubOrganizationEntitiy_when_insert_completly() throws Exception {
    final GithubOrganizationEntry githubOrganizationEntry = GithubTestModelsHelper
        .getValidTestGithubOrganizationEntity();
    when(mockGithubOrganizationRepository.save(any(GithubOrganizationEntry.class))).thenReturn(githubOrganizationEntry);

    final GithubOrganizationEntry response = githubService
        .insertGithubOrganization(GithubTestModelsHelper.getValidTestGithubOrganization());
    assertEquals(response.getLogin(), githubOrganizationEntry.getLogin());
  }

  @Test
  public void should_return_GithubRepositoryEntitiy_when_insert_completly() throws Exception {
    final GithubRepositoryEntity githubRepositoryEntity = GithubTestModelsHelper.getValidTestGithubRepositoryEntity();

    when(mockGithubOwnerRepository.findByLogin(anyString()))
        .thenReturn(GithubTestModelsHelper.getValidTestGithubOwnerEntity());
    when(mockGithubOrganizationRepository.findByLogin(anyString()))
        .thenReturn(GithubTestModelsHelper.getValidTestGithubOrganizationEntity());
    when(mockGithubRepositoryRepository.save(any(GithubRepositoryEntity.class))).thenReturn(githubRepositoryEntity);
    final GithubRepositoryEntity response = githubService
        .insertRepository(GithubTestModelsHelper.getValidTestGithubRepositoryOne());
    assertEquals(response.getName(), githubRepositoryEntity.getName());
  }

  @Test
  public void modifyStringnWhenToForLongDatabase_return_same_string_when_size_smaller_equals_100() throws Exception {

    String notToLongForDatabase = "";

    while (notToLongForDatabase.length() < 99) {
      notToLongForDatabase += "a";
    }
    assertEquals(this.githubService.modifyStringnWhenToForLongDatabase(notToLongForDatabase).length(),
        notToLongForDatabase.length());
  }

  @Test
  public void modifyStringnWhenToForLongDatabase_returns_maximum_characters_when_size_bigger_100() throws Exception {
    String toLongForDatabase = "";
    while (toLongForDatabase.length() < 150) {
      toLongForDatabase += "a";
    }
    assertEquals(this.githubService.modifyStringnWhenToForLongDatabase(toLongForDatabase).length(), 100);
  }

  @Test
  public void modifyStringnWhenToForLongDatabase_returns_null_when_input_null() throws Exception {
    assertEquals(this.githubService.modifyStringnWhenToForLongDatabase(null), null);
  }

  @Test
  public void should_find_all_repositories() throws Exception {

    final GithubRepositoryEntity repoOne = GithubTestModelsHelper.getValidTestGithubRepositoryEntity();
    repoOne.setId(200L);
    repoOne.setName("Repository One");
    final GithubRepositoryEntity repoTwo = GithubTestModelsHelper.getValidTestGithubRepositoryEntity();
    repoTwo.setId(300L);
    repoOne.setName("Repository Two");

    final List<GithubRepositoryEntity> list = new ArrayList<GithubRepositoryEntity>();
    list.add(repoOne);
    list.add(repoTwo);

    final Iterable<GithubRepositoryEntity> iterable = list;
    when(mockGithubRepositoryRepository.findAll()).thenReturn(iterable);

    final Iterable<GithubRepositoryEntity> response = githubService.getAllRepositories();
    assertThat(response).hasSize(2).contains(repoOne, repoTwo);
  }

  @Test
  public void should_find_all_organizations() throws Exception {

    final GithubOrganizationEntry organizationOne = GithubTestModelsHelper.getValidTestGithubOrganizationEntity();
    organizationOne.setId(200L);
    organizationOne.setLogin("Organization One");
    final GithubOrganizationEntry organizationTwo = GithubTestModelsHelper.getValidTestGithubOrganizationEntity();
    organizationTwo.setId(300L);
    organizationTwo.setLogin("Organization Two");
    final GithubOrganizationEntry organizationThree = GithubTestModelsHelper.getValidTestGithubOrganizationEntity();
    organizationThree.setId(400L);
    organizationThree.setLogin("Organization Three");

    final List<GithubOrganizationEntry> list = new ArrayList<GithubOrganizationEntry>();
    list.add(organizationOne);
    list.add(organizationTwo);
    list.add(organizationThree);

    final Iterable<GithubOrganizationEntry> iterable = list;
    when(mockGithubOrganizationRepository.findAll()).thenReturn(iterable);

    final Iterable<GithubOrganizationEntry> response = githubService.getAllOrganization();
    assertThat(response).hasSize(3).contains(organizationOne, organizationTwo, organizationThree);
  }

  @Test
  public void should_return_true_when_Owner_exists_in_repository() throws Exception {
    when(mockGithubOwnerRepository.findByLogin(anyString()))
        .thenReturn(GithubTestModelsHelper.getValidTestGithubOwnerEntity());
    assertTrue(this.githubService.isOwnerExists(GithubTestModelsHelper.getValidTestGithubOwner()));
  }

  @Test
  public void should_return_false_when_Owner_not_exists_in_repository() throws Exception {
    when(mockGithubOwnerRepository.findByLogin(anyString())).thenReturn(null);
    assertFalse(this.githubService.isOwnerExists(GithubTestModelsHelper.getValidTestGithubOwner()));
  }

  @Test
  public void should_return_true_when_organization_exists_in_repository() throws Exception {
    when(mockGithubOrganizationRepository.findByLogin(anyString()))
        .thenReturn(GithubTestModelsHelper.getValidTestGithubOrganizationEntity());
    assertTrue(this.githubService.isOrganizationExists(GithubTestModelsHelper.getValidTestGithubOrganization()));
  }

  @Test
  public void should_return_false_when_organization_not_exists_in_repository() throws Exception {
    when(mockGithubOrganizationRepository.findByLogin(anyString())).thenReturn(null);
    assertFalse(this.githubService.isOrganizationExists(GithubTestModelsHelper.getValidTestGithubOrganization()));
  }

  @Test
  public void should_return_true_when_repository_exists_in_repository() throws Exception {
    when(mockGithubRepositoryRepository.findByName(anyString()))
        .thenReturn(GithubTestModelsHelper.getValidTestGithubRepositoryEntity());
    assertTrue(this.githubService.isRepositoryExists(GithubTestModelsHelper.getValidTestGithubRepositoryOne()));
  }

  @Test
  public void should_return_false_when_repository_not_exists_in_repository() throws Exception {
    when(mockGithubRepositoryRepository.findByName(anyString())).thenReturn(null);
    assertFalse(this.githubService.isRepositoryExists(GithubTestModelsHelper.getValidTestGithubRepositoryOne()));
  }

  @Test
  public void should_return_true_when_insertGithubOrganization_is_already_exists_in_repository() throws Exception {
    when(mockGithubOrganizationRepository.findByLogin(anyString()))
        .thenReturn(GithubTestModelsHelper.getValidTestGithubOrganizationEntity());
    assertTrue(
        this.githubService.insertGithubOrganizationIsNotExist(GithubTestModelsHelper.getValidTestGithubOrganization()));
  }

  @Test
  public void should_return_true_when_insertGithubOrganization_not_exists_in_repository_and_insert_correctly()
      throws Exception {
    when(mockGithubOrganizationRepository.save(any(GithubOrganizationEntry.class)))
        .thenReturn(GithubTestModelsHelper.getValidTestGithubOrganizationEntity());

    when(mockGithubOrganizationRepository.findByLogin(anyString())).thenReturn(null);
    assertTrue(
        this.githubService.insertGithubOrganizationIsNotExist(GithubTestModelsHelper.getValidTestGithubOrganization()));
  }

  @Test
  public void should_return_true_when_insertGithubOwner_is_already_exists_in_repository() throws Exception {
    when(mockGithubOwnerRepository.findByLogin(anyString()))
        .thenReturn(GithubTestModelsHelper.getValidTestGithubOwnerEntity());
    assertTrue(this.githubService.insertGithubOwnerIsNotExist(GithubTestModelsHelper.getValidTestGithubOwner()));
  }

  @Test
  public void should_return_true_when_insertGithubOwner_not_exists_in_repository_and_insert_correctly()
      throws Exception {
    when(mockGithubOwnerRepository.save(any(GithubOwnerEntity.class)))
        .thenReturn(GithubTestModelsHelper.getValidTestGithubOwnerEntity());

    when(mockGithubOwnerRepository.findByLogin(anyString())).thenReturn(null);
    assertTrue(this.githubService.insertGithubOwnerIsNotExist(GithubTestModelsHelper.getValidTestGithubOwner()));
  }

  @Test
  public void should_return_true_when_insertGithubRepository_is_already_exists_in_repository() throws Exception {
    when(mockGithubRepositoryRepository.findByName(anyString()))
        .thenReturn(GithubTestModelsHelper.getValidTestGithubRepositoryEntity());
    assertTrue(
        this.githubService.insertGithubRepositoryIsNotExist(GithubTestModelsHelper.getValidTestGithubRepositoryOne()));
  }

  @Test
  public void should_return_true_when_insertGithubRepository_not_exists_in_repository_and_insert_correctly()
      throws Exception {
    final GithubRepositoryEntity githubRepositoryEntity = GithubTestModelsHelper.getValidTestGithubRepositoryEntity();
    when(mockGithubOwnerRepository.findByLogin(anyString()))
        .thenReturn(GithubTestModelsHelper.getValidTestGithubOwnerEntity());
    when(mockGithubOrganizationRepository.findByLogin(anyString()))
        .thenReturn(GithubTestModelsHelper.getValidTestGithubOrganizationEntity());
    when(mockGithubRepositoryRepository.save(any(GithubRepositoryEntity.class))).thenReturn(githubRepositoryEntity);
    when(mockGithubRepositoryRepository.findByName(anyString())).thenReturn(null);

    assertTrue(
        this.githubService.insertGithubRepositoryIsNotExist(GithubTestModelsHelper.getValidTestGithubRepositoryOne()));
  }

}
