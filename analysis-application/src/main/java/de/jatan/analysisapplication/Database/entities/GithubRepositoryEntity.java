package de.jatan.analysisapplication.Database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
  private String language;

  @ManyToOne
  @JoinColumn(name = "fk_github_owner")
  private GithubOwnerEntity github_owner;

  @ManyToOne
  @JoinColumn(name = "fk_github_organization")
  private GithubOrganizationEntry github_organization;
}
