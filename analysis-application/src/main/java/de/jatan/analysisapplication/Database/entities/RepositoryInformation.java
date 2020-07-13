package de.jatan.analysisapplication.Database.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import de.jatan.analysisapplication.Domain.Model.GithubOwner;

@Entity
@Table(name = "repoinformation")
public class RepositoryInformation {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  private String description;
  private String url;
  private String name;

  /*
   * @OneToOne(cascade = CascadeType.ALL)
   *
   * @JoinColumn(name = "githubowner_id", referencedColumnName = "id") private
   * GithubOwnerEntity owner;
   */
  /*
   * @ManyToOne(fetch = FetchType.LAZY, optional = false)
   *
   * @JoinColumn(name = "", nullable = false) private OrganizationInformationEntry
   * ;
   */

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
   * @return String return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return String return the url
   */
  public String getUrl() {
    return url;
  }

  /**
   * @param url the url to set
   */
  public void setUrl(String url) {
    this.url = url;
  }

  /**
   * @return String return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

}
