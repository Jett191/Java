package vo;

public class Grade {

  private Integer grade;
  private String gradeName;

  public Grade() {

  }

  public Grade(Integer grade, String gradeName) {
    super();
    this.grade = grade;
    this.gradeName = gradeName;
  }

  public Integer getGrade() {
    return grade;
  }

  public void setGrade(Integer grade) {
    this.grade = grade;
  }

  public String getGradeName() {
    return gradeName;
  }

  public void setGradeName(String gradeName) {
    this.gradeName = gradeName;
  }

}
