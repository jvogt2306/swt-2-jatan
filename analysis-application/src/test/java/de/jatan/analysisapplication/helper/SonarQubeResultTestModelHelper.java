package de.jatan.analysisapplication.helper;

import org.springframework.web.util.UriComponentsBuilder;

import de.jatan.analysisapplication.Domain.Model.SonarQubeResponse;
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

  public final static UriComponentsBuilder getValidURIComponentBuilder() {
    final SonarQubeResponse sonarResponse = SonarQubeTestModelsHelper.getValidSonarQubeResponse();
    final String projectKey = sonarResponse.getProject().getKey();
    final String searchSonarProjectEndpoint = "http://192.168.1.32:9000/api/measures/component";
    return UriComponentsBuilder.fromHttpUrl(searchSonarProjectEndpoint).queryParam("component", projectKey).queryParam(
        "metricKeys",
        "bugs,code_smells,sqale_index,ncloc,vulnerabilities,security_rating,duplicated_lines,complexity,violations,reliability_rating");
  }

}
