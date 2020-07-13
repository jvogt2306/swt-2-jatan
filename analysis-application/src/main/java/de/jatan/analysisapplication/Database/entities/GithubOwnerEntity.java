package de.jatan.analysisapplication.Database.entities;

import java.util.Set;

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
  private String login;
  private String url;

  @OneToMany(mappedBy = "github_owner")
  private Set<RepositoryInformationEntity> repository_information;

  public void addRepository_information(RepositoryInformationEntity repository_information) {
    this.repository_information.add(repository_information);
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
