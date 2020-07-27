package de.jatan.analysisapplication.Domain.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GithubOwner {
  private String gists_url;
  private String repos_url;
  private String following_url;
  private String starred_url;
  private String login;
  private String followers_url;
  private String type;
  private String url;
  private String subscriptions_url;
  private String received_events_url;
  private String avatar_url;
  private String events_url;
  private String html_url;
  private String site_admin;
  private String id;
  private String gravatar_id;
  private String node_id;
  private String organizations_url;
}
