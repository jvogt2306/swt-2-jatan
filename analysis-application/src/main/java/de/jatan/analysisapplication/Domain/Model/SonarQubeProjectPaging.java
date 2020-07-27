package de.jatan.analysisapplication.Domain.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@SuppressWarnings("unused")
public class SonarQubeProjectPaging {
  private String total;

  private String pageIndex;

  private String pageSize;
}
