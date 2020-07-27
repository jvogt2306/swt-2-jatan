package de.jatan.analysisapplication.Domain.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GithubLicense {
  private String name;
  private String spdx_id;
  private String key;
  private String url;
  private String node_id;
}
