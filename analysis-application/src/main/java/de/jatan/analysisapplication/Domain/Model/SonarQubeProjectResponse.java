package de.jatan.analysisapplication.Domain.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@SuppressWarnings("unused")
public class SonarQubeProjectResponse {

  private SonarQubeProjectComponents[] components;

  private SonarQubeProjectPaging paging;

}
