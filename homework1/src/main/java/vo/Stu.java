package vo;

public class Stu {

 
  private Integer sssid, sssage;
  private String sssname, sssex;
  private Integer mm;


  public Stu() {
  }

  public Stu(Integer sssid) {
    this.sssid = sssid;
  }

  @Override
  public String toString() {
    return "Stu{" + "sssid=" + sssid + ", sssage=" + sssage + ", sssname='" + sssname + '\''
        + ", sssex='" + sssex + '\'' + ", mm=" + mm + '}';
  }

  public Integer getMm() {
    return mm;
  }

  public void setMm(Integer mm) {
    this.mm = mm;
  }

  public Stu(Integer sssid, Integer sssage, String sssname, String sssex) {
    this.sssid = sssid;
    this.sssage = sssage;
    this.sssname = sssname;
    this.sssex = sssex;
  }

  public Integer getSssid() {
    return sssid;
  }

  public Integer getSssage() {
    return sssage;
  }

  public String getSssname() {
    return sssname;
  }

  public String getSssex() {
    return sssex;
  }

  public void setSssid(Integer sssid) {
    this.sssid = sssid;
  }

  public void setSssage(Integer sssage) {
    this.sssage = sssage;
  }

  public void setSssname(String sssname) {
    this.sssname = sssname;
  }

  public void setSssex(String sssex) {
    this.sssex = sssex;
  }
}
