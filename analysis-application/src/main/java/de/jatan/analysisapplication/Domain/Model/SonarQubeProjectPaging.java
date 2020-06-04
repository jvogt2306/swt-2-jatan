package de.jatan.analysisapplication.Domain.Model;

public class SonarQubeProjectPaging {
  private String total;

  private String pageIndex;

  private String pageSize;

  public String getTotal() {
    return total;
  }

  public void setTotal(String total) {
    this.total = total;
  }

  public String getPageIndex() {
    return pageIndex;
  }

  public void setPageIndex(String pageIndex) {
    this.pageIndex = pageIndex;
  }

  public String getPageSize() {
    return pageSize;
  }

  public void setPageSize(String pageSize) {
    this.pageSize = pageSize;
  }

  @Override
  public String toString() {
    return "ClassPojo [total = " + total + ", pageIndex = " + pageIndex + ", pageSize = " + pageSize + "]";
  }
}
