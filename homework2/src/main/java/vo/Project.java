package vo;

public class Project {

  private Integer id;
  private String name;
  private String date;
  private Integer budget;

  private Employee employee;


  public Project() {
  }

  public Project(Integer id, String name, String date, Integer budget) {
    super();
    this.id = id;
    this.name = name;
    this.date = date;
    this.budget = budget;
  }

  @Override
  public String toString() {
    return "Project [ id=" + id + ", name=" + name + ", date=" + date + ", budget=" + budget + " ]";
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

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public Integer getBudget() {
    return budget;
  }

  public void setBudget(Integer budget) {
    this.budget = budget;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }
}
