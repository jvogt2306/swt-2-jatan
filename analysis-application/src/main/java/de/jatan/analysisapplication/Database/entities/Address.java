package de.jatan.analysisapplication.Database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;
  private String street;
  private String plz;

  @OneToOne(mappedBy = "address")
  private User user;

  public String getStreet() {
    return this.street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getPlz() {
    return this.plz;
  }

  public void setPlz(String plz) {
    this.plz = plz;
  }

}
