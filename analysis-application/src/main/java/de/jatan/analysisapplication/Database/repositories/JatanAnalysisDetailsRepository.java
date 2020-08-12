package de.jatan.analysisapplication.Database.repositories;

import java.util.List;

import de.jatan.analysisapplication.Database.entities.JatanAnalysisDetailsEntry;
import de.jatan.analysisapplication.Database.repositories.Interfaces.ReadOnlyRepository;

public interface JatanAnalysisDetailsRepository extends ReadOnlyRepository<JatanAnalysisDetailsEntry, Integer> {
  List<JatanAnalysisDetailsEntry> findByLogin(String login);
}
