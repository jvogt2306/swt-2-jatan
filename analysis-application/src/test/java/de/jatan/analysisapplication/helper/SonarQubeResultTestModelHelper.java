package de.jatan.analysisapplication.helper;

import de.jatan.analysisapplication.Domain.Model.SonarResultsMeasures;

public class SonarQubeResultTestModelHelper {

  public final static SonarResultsMeasures[] getValidSonarResultsMeasures() {
    SonarResultsMeasures result[] = { new SonarResultsMeasures("code_smells", "10"),
        new SonarResultsMeasures("bugs", "20"), new SonarResultsMeasures("sqale_index", "30"),
        new SonarResultsMeasures("ncloc", "40"), new SonarResultsMeasures("reliability_rating", "1.0"),
        new SonarResultsMeasures("violations", "9"), new SonarResultsMeasures("complexity", "2000"),
        new SonarResultsMeasures("duplicated_lines", "10"), new SonarResultsMeasures("security_rating", "0.2"),
        new SonarResultsMeasures("vulnerabilities", "50") };
    return result;
  };
}
