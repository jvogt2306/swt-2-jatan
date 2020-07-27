package de.jatan.analysisapplication.Domain.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@SuppressWarnings("unused")
public class SonarResultsComponents {
    private SonarResultsMeasures[] measures;

    private String qualifier;

    private String name;

    private String id;

    private String key;

}
