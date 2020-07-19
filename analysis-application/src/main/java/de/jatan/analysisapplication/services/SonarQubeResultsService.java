package de.jatan.analysisapplication.services;

import de.jatan.analysisapplication.Domain.Model.SonarQubeResponse;
import de.jatan.analysisapplication.Domain.Model.SonarResults;
import de.jatan.analysisapplication.Domain.Model.SonarResultsMeasures;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SonarQubeResultsService {
    public SonarResults getResults(SonarQubeResponse sonarbody){
        String projectKey = sonarbody.getProject().getKey();

        RestTemplate restTemplate = new RestTemplate();
        String urlSonarResults = ("http://localhost:9000/api/measures/component?component="+projectKey);
        SonarResults sonarResults = restTemplate.getForObject(urlSonarResults+"&metricKeys=bugs,code_smells,sqale_index,coverage",
            SonarResults.class);
        return sonarResults;
    }
    public String getCode_smells(SonarResults sonarResults){
        SonarResultsMeasures[] measures = sonarResults.getComponent().getMeasures();
        String code_smells = new String(" ");
        for (int i = 0; i < measures.length; i++){
            if (measures[i].getMetric() == "code_smells"){
                code_smells = measures[i].getValue();
            }
        }
        return code_smells;
    }
    public String getBugs(SonarResults sonarResults){
        SonarResultsMeasures[] measures = sonarResults.getComponent().getMeasures();
        String bugs = new String(" ");
        for (int i = 0; i < measures.length; i++){
            if (measures[i].getMetric() == "bugs"){
                bugs = measures[i].getValue();
            }
        }
        return bugs;
    }
    public String getSqale_index(SonarResults sonarResults){
        SonarResultsMeasures[] measures = sonarResults.getComponent().getMeasures();
        String sqale_index = new String(" ");
        for (int i = 0; i < measures.length; i++){
            if (measures[i].getMetric() == "sqale_index"){
                sqale_index = measures[i].getValue();
            }
        }
        return sqale_index;
    }
    public String getCoverage(SonarResults sonarResults){
        SonarResultsMeasures[] measures = sonarResults.getComponent().getMeasures();
        String coverage = new String(" ");
        for (int i = 0; i < measures.length; i++){
            if (measures[i].getMetric() == "coverage"){
                coverage = measures[i].getValue();
            }
        }
        return coverage;
    }
}
