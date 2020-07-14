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

  @OneToMany(mappedBy = "github_owner", cascade = CascadeType.ALL)
  private List<RepositoryInformationEntity> repositories = new ArrayList<>();

  public void addRepository(RepositoryInformationEntity repository) {
    this.repositories.add(repository);
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

}
