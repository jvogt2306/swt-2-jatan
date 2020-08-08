package de.jatan.analysisapplication.repository;


import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import de.jatan.analysisapplication.Database.entities.JatanAnalysisEntry;
import de.jatan.analysisapplication.Database.repositories.JatanAnalysisRepository;


@AutoConfigureTestDatabase(replace = Replace.NONE)
public class SonarQubeMeasuresRepositoryTest {

  @MockBean
  private SonarQubeMeasuresRepositoryTest sonarQubeMeasuresRepositoryTest;

  @Before
  public void setUp() {
  }
}
