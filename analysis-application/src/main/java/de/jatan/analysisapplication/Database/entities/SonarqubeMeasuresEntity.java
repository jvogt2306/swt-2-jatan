package de.jatan.analysisapplication.Database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
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

  private String project;
  private String sqale_index;
  private String coverage;
  private String bugs;
  private String code_smells;
  private String ncloc;
public Object thenReturn(SonarqubeMeasuresEntity sonarqubeMeasuresEntity) {
	return null;
}
}
