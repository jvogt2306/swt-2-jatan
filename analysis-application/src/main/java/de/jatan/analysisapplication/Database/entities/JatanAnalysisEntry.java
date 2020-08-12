package de.jatan.analysisapplication.Database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "jatan_analysis")
public class JatanAnalysisEntry {

  @Id
  @Column(name = "login")
  private String login;

  @Column(name = "AVG_ORG_BUGS")
  private double bugs;

  @Column(name = "AVG_ORG_TECHNICAL_DEPT")
  private double technicalDept;

  @Column(name = "AVG_ORG_CODE_SMELLS")
  private double codeSmells;

  @Column(name = "AVG_ORG_BUGS_PER_LINE")
  private double bugsPerLine;

  @Column(name = "AVG_ORG_CODE_SMELLS_PER_LINE")
  private double codeSmellsPerLine;

  @Column(name = "AVG_ORG_TECHNICAL_DEPT_PER_LINE")
  private double technicalDeptPerLine;

  public String getLogin() {
    return this.login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public double getBugs() {
    return this.bugs;
  }

  public void setBugs(double bugs) {
    this.bugs = bugs;
  }

  public double getTechnicalDept() {
    return this.technicalDept;
  }

  public void setTechnicalDept(double technicalDept) {
    this.technicalDept = technicalDept;
  }

  public double getCodeSmells() {
    return this.codeSmells;
  }

  public void setCodeSmells(double codeSmells) {
    this.codeSmells = codeSmells;
  }

  public double getBugsPerLine() {
    return this.bugsPerLine;
  }

  public void setBugsPerLine(double bugsPerLine) {
    this.bugsPerLine = bugsPerLine;
  }

  public double getCodeSmellsPerLine() {
    return this.codeSmellsPerLine;
  }

  public void setCodeSmellsPerLine(double codeSmellsPerLine) {
    this.codeSmellsPerLine = codeSmellsPerLine;
  }

  public double getTechnicalDeptPerLine() {
    return this.technicalDeptPerLine;
  }

  public void setTechnicalDeptPerLine(double technicalDeptPerLine) {
    this.technicalDeptPerLine = technicalDeptPerLine;
  }

}
