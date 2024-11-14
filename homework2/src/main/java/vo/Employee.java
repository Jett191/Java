package vo;

public class Employee {

  private Integer id;
  private String name;
  private String job;
  private String date;
  private Integer salary;
  private Integer did;

  public Employee() {
  }

  public Employee(Integer id, String name, String job, Integer mid, String date, Integer salary,
      Integer commission, Integer did) {
    super();
    this.id = id;
    this.name = name;
    this.job = job;
    this.date = date;
    this.salary = salary;
    this.did = did;
  }

  @Override
  public String toString() {
    return "Employee [ id=" + id + ", name=" + name + ", job=" + job + ", date=" + date
        + ", salary=" + salary + ", did=" + did + " ]";
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

  public Integer getDid() {
    return did;
  }

  public void setDid(Integer did) {
    this.did = did;
  }
}
