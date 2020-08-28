package de.jatan.analysisapplication.Database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "jatan_analysis_details")
public class JatanAnalysisDetailsEntry {

  @Column(name = "login")
  private String login;
  @Id
  @Column(name = "name")
  private String name;

  @Column(name = "language")
  private String language;

  @Column(name = "bugs")
  private String bugs;

  @Column(name = "code_smells")
  private String codeSmells;

  @Column(name = "security_rating")
  private String securityRating;

  @Column(name = "duplicated_lines")
  private String duplicatedLines;

  @Column(name = "reliability_rating")
  private String reliabilityRating;

  @Column(name = "violations")
  private String violations;

  @Column(name = "vulnerabilities")
  private String vulnerabilities;

  @Column(name = "ncloc")
  private String linesOfCode;

  @Column(name = "sqale_index")
  private String technicalDept;

  @Column(name = "BUGS_PER_LINE")
  private double bugsPerLine;

  @Column(name = "SMELLS_PER_LINE")
  private double codeSmellsPerLine;

  @Column(name = "TECHNICAL_DEPT_PER_LINE")
  private double technicalDeptPerLine;
}
