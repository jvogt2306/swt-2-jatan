package de.jatan.analysisapplication.Domain.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class SonarResultsComponents {
    private SonarResultsMeasures[] measures;

    private String qualifier;

    private String name;

    private String id;

    private String key;

}
