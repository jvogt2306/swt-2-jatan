package de.jatan.analysisapplication.Domain.Model;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class GithubRepository {
  private String language;
  private String name;
  private String description;
  private GithubOwner owner;
  private String url;
  private String Svn_url;
}
