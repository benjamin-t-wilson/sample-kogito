package org.acme;

public class Applicant {

  private String name;
  private int creditScore;
  private boolean approved;

  public Applicant() {}

  public Applicant(String name, int creditScore) {
    super();
    this.name = name;
    this.creditScore = creditScore;
  }

  public int getCreditScore() {
    return this.creditScore;
  }

  public boolean isApproved() {
    return this.approved;
  }

  public String getName() {
    return this.name;
  }

  public void setCreditScore(int creditScore) {
    this.creditScore = creditScore;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setApproved(boolean approved) {
    this.approved = approved;
  }
}
