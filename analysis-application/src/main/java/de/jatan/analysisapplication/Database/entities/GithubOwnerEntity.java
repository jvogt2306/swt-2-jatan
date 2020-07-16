package de.jatan.analysisapplication.Database.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "github_owner")

public class GithubOwnerEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;
  private String url;
  private String login;

  @OneToMany(mappedBy = "github_owner", cascade = CascadeType.ALL)
  private List<GithubRepositoryEntity> repositories = new ArrayList<>();

  public void addRepository(GithubRepositoryEntity repository) {
    this.repositories.add(repository);
  }

  public String getLogin() {
    return this.login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

}
