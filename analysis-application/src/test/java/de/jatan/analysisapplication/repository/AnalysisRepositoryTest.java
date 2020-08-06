package de.jatan.analysisapplication.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AnalysisRepositoryTest {

  @MockBean
  private JatanAnalysisRepository mockJatanAnalysisRepository;

  @Before
  public void setUp() {
    JatanAnalysisEntry mockEntry = new JatanAnalysisEntry();
    mockEntry.setLogin("login");

    mockEntry.setBugs(22.22);
    mockEntry.setBugsPerLine(22.22);

    mockEntry.setCoverage(22.22);
    mockEntry.setCoveragePerLine(2);

    mockEntry.setCodeSmells(22.22);
    mockEntry.setCodeSmellsPerLine(2);

    mockEntry.setTechnicalDept(2);
    mockEntry.setTechnicalDeptPerLine(22.22);

    when(mockJatanAnalysisRepository.findById(1)).thenReturn(Optional.of(mockEntry));
  }

  @Test
  public void returnTrue() {
    assertEquals(mockJatanAnalysisRepository.findById(1).get().getBugs(), 22.22);
  }
}
