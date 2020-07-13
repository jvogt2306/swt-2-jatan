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
@Table(name = "repository_information")
public class RepositoryInformationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  private String description;
  private String url;
  private String name;
  private String login;

  // @OneToOne(cascade = CascadeType.ALL)
  // @JoinColumn(name = "github_owner_login", referencedColumnName = "login")
  // private GithubOwnerEntity github_owner;

  /*
  @ManyToOne
  @JoinColumn(name = "github_owner_login", nullable = false)
  private GithubOwnerEntity github_owner; */

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

    /**
     * @return String return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return GithubOwnerEntity return the github_owner
     */
    /*
    public GithubOwnerEntity getGithub_owner() {
        return github_owner;
    }

    /* 
    public void setGithub_owner(GithubOwnerEntity github_owner) {
        this.github_owner = github_owner;
    }
*/
}
