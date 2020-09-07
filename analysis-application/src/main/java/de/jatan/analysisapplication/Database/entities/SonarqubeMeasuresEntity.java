package de.jatan.analysisapplication.Database.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "sonarqube_measures")
public class SonarqubeMeasuresEntity {

  @OneToOne
  @JoinColumn(name = "fk_repository")
  private GithubRepositoryEntity repository;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @CreationTimestamp
  private LocalDateTime createDateTime;

  @UpdateTimestamp
  private LocalDateTime updateDateTime;

  private String project;
  private String sqale_index;
  private String bugs;
  private String code_smells;
  private String ncloc;
  private String vulnerabilities;
  private String security_rating;
  private String duplicated_lines;
  private String complexity;
  private String violations;
  private String reliability_rating;
}
