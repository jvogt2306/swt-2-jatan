package de.jatan.analysisapplication.Domain.Model;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class GithubOwner {
  private String id;
  private String login;
  private String repos_url;
  private String url;
}
