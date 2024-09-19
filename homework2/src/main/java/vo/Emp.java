package vo;

public class Emp {

  private Integer id;
  private String name;
  private String job;
  private Integer mid;
  private String date;
  private Integer salary;
  private Integer commission;
  private Integer did;

  public Emp() {
  }

  public Emp(Integer id, String name, String job, Integer mid, String date, Integer salary,
      Integer commission, Integer did) {
    super();
    this.id = id;
    this.name = name;
    this.job = job;
    this.mid = mid;
    this.date = date;
    this.salary = salary;
    this.commission = commission;
    this.did = did;
  }

  @Override
  public String toString() {
    return "Emp [ id=" + id + ", name=" + name + ", job=" + job + ", mid=" + mid + ", date=" + date
        + ", salary=" + salary + ", commission=" + commission + ", did=" + did + " ]";
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getJob() {
    return job;
  }

  public void setJob(String job) {
    this.job = job;
  }

  public Integer getMid() {
    return mid;
  }

  public void setMid(Integer mid) {
    this.mid = mid;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public Integer getSalary() {
    return salary;
  }

  public void setSalary(Integer salary) {
    this.salary = salary;
  }

  public Integer getCommission() {
    return commission;
  }

  public void setCommission(Integer commission) {
    this.commission = commission;
  }

  public Integer getDid() {
    return did;
  }

  public void setDid(Integer did) {
    this.did = did;
  }
}
