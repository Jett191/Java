package vo;

public class Student {

  private Integer id, age, java, android, javaee, total;
  private String name;

  public Student() {

  }

  public Student(Integer id, Integer age, Integer java, Integer android, Integer javaee,
      Integer total, String name) {
    super();
    this.id = id;
    this.age = age;
    this.java = java;
    this.android = android;
    this.javaee = javaee;
    this.total = total;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Integer getJava() {
    return java;
  }

  public void setJava(Integer java) {
    this.java = java;
  }

  public Integer getAndroid() {
    return android;
  }

  public void setAndroid(Integer android) {
    this.android = android;
  }

  public Integer getJavaee() {
    return javaee;
  }

  public void setJavaee(Integer javaee) {
    this.javaee = javaee;
  }

  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Student [ id=" + id + ", name=" + name + ", age=" + age + ", java=" + java
        + ", android=" + android
        + ", javaee=" + javaee + ", total=" + total + " ]";
  }

}



