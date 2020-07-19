package de.jatan.analysisapplication.Database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sonarqube_measures")
public class SonarqubeMeasuresEntity {

  @OneToOne
  @JoinColumn(name = "fk_repository")
  private GithubRepositoryEntity repository;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  private String project;
  private String sqale_index;
  private String coverage;
  private String bugs;
  private String code_smells;
  private String ncloc;

  public String getSqale_index() {
    return this.sqale_index;
  }

  public void setSqale_index(String sqale_index) {
    this.sqale_index = sqale_index;
  }

  public String getCoverage() {
    return this.coverage;
  }

  public void setCoverage(String coverage) {
    this.coverage = coverage;
  }

  public String getBugs() {
    return this.bugs;
  }

  public void setBugs(String bugs) {
    this.bugs = bugs;
  }

  public String getCode_smells() {
    return this.code_smells;
  }

  public void setCode_smells(String code_smells) {
    this.code_smells = code_smells;
  }

  public String getNcloc() {
    return this.ncloc;
  }

  public void setNcloc(String ncloc) {
    this.ncloc = ncloc;
  }

  public GithubRepositoryEntity getRepository() {
    return this.repository;
  }

  public void setRepository(GithubRepositoryEntity repository) {
    this.repository = repository;
  }

  public String getProject() {
    return this.project;
  }

  public void setProject(String project) {
    this.project = project;
  }

}
