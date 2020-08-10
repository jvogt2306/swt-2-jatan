package de.jatan.analysisapplication.repository;


import org.junit.Before;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.mock.mockito.MockBean;

@AutoConfigureTestDatabase(replace = Replace.NONE)
public class SonarQubeMeasuresRepositoryTest {

  @MockBean
  private SonarQubeMeasuresRepositoryTest sonarQubeMeasuresRepositoryTest;

  @Before
  public void setUp() {
  }
}
