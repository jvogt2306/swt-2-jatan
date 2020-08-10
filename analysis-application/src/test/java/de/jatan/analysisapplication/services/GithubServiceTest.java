package de.jatan.analysisapplication.services;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
public class GithubServiceTest {

  @Mock
  private SonarQubeService sonarQubeService;

  @Before
  public void setUp() throws Exception {
  }
}
