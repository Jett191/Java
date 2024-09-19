package vo;

public class Student {

  private Integer sid;
  private Integer age;
  private String sname, sex;

  public Student() {

  }

  public Student(Integer age, String sname, String sex) {
    super();
    this.age = age;
    this.sname = sname;
    this.sex = sex;
  }

  @Override
  public String toString() {
    return "Student [sid=" + sid + ",age=" + age + ",sname=" + sname + ",sex=" + sex + "]";
  }

  public Integer getSid() {
    return sid;
  }

  public void setSid(Integer sid) {
    this.sid = sid;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getSname() {
    return sname;
  }

  public void setSname(String sname) {
    this.sname = sname;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }
}

