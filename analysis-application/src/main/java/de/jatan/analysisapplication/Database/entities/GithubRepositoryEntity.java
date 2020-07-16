package de.jatan.analysisapplication.Database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "github_repository")
public class GithubRepositoryEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  private String description;
  private String url;
  private String name;

  @ManyToOne
  @JoinColumn(name = "fk_github_owner")
  private GithubOwnerEntity github_owner;

  @ManyToOne
  @JoinColumn(name = "fk_github_organization")
  private GithubOrganizationEntry github_organization;

  public void setGithub_organization(GithubOrganizationEntry github_organization) {
    this.github_organization = github_organization;
  };

  public void setGithub_owner(GithubOwnerEntity github_owner) {
    this.github_owner = github_owner;
  };

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return Long return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }
}
