package de.jatan.analysisapplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.jatan.analysisapplication.Database.entities.JatanAnalysisDetailsEntry;
import de.jatan.analysisapplication.Database.entities.JatanAnalysisEntry;
import de.jatan.analysisapplication.Database.repositories.JatanAnalysisDetailsRepository;
import de.jatan.analysisapplication.Database.repositories.JatanAnalysisRepository;

@Service
public class JatanAnalysisService {

  @Autowired
  private JatanAnalysisRepository jatanAnalysisRepository;
  @Autowired
  private JatanAnalysisDetailsRepository jatanAnalysisDetailsRepository;

  public Iterable<JatanAnalysisEntry> getAllCompaniesAnalysis() {
    return jatanAnalysisRepository.findAll();
  }

  public Iterable<JatanAnalysisDetailsEntry> getAllCompanyAnalysisDetails() {
    return jatanAnalysisDetailsRepository.findAll();
  }
}
