package de.jatan.analysisapplication.Database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
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

  @Column(name = "coverage")
  private String coverage;

  @Column(name = "ncloc")
  private String linesOfCode;

  @Column(name = "sqale_index")
  private String technicalDept;

  @Column(name = "BUGS_PER_LINE")
  private double bugsPerLine;

  @Column(name = "SMELLS_PER_LINE")
  private double codeSmellsPerLine;

  @Column(name = "COVERAGE_PER_LINE")
  private double coveragePerLine;

  @Column(name = "TECHNICAL_DEPT_PER_LINE")
  private double technicalDeptPerLine;

  public String getLogin() {
    return this.login;
  }

  public String getName() {
    return this.name;
  }

  public String getLanguage() {
    return this.language;
  }

  public String getBugs() {
    return this.bugs;
  }

  public String getCodeSmells() {
    return this.codeSmells;
  }

  public String getCoverage() {
    return this.coverage;
  }

  public String getLinesOfCode() {
    return this.linesOfCode;
  }

  public String getTechnicalDept() {
    return this.technicalDept;
  }

  public double getCodeSmellsPerLine() {
    return this.codeSmellsPerLine;
  }

  public double getCoveragePerLine() {
    return this.coveragePerLine;
  }

  public double getTechnicalDeptPerLine() {
    return this.technicalDeptPerLine;
  }

}
