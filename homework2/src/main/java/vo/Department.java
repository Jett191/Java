package vo;

import java.util.List;

public class Department {

  private Integer id;
  private String name;
  private String location;
  private List<Employee> Employees;


  public Department() {
  }

  public Department(Integer id, String name, String location) {
    super();
    this.id = id;
    this.name = name;
    this.location = location;
  }

  @Override
  public String toString() {
    return "Department [ id=" + id + ", name=" + name + ", location=" + location + " ]";
  }

  public int getId() {
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

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public List<Employee> getEmployees() {
    return Employees;
  }

  public void setEmployees(List<Employee> employees) {
    Employees = employees;
  }
}
