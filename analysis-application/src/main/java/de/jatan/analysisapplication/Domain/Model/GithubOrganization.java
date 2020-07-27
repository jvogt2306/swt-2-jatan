package de.jatan.analysisapplication.Domain.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GithubOrganization {
  private String login;
  private Long id;
  private String node_id;
  private String url;
  private String repos_url;
  private String events_url;
  private String hooks_url;
  private String issues_url;
  private String members_url;
  private String public_members_url;
  private String avatar_url;
  private String description;
  private String name;
  private String company;
  private String blog;
  private String location;
  private String email;
  private String is_verified;
  private String has_organization_projects;
  private String has_repository_projects;
  private String public_repos;
  private String public_gists;
  private String followers;
  private String following;
  private String html_url;
  private String created_at;
  private String updated_at;
  private String type;
}
