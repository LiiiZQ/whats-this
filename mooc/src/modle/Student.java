package modle;

public class Student {
private String name;
private String password;
private String type;
public Student() {
	
}
public Student(String name,String password,String type) {
	this.name=name;
	this.password=password;
	this.type=type;
}
public String getStudentNum() {
	return name;
}
public void setStudentNum(String name) {
	this.name=name;
}
public String getLessonNum() {
	return password;
}
public void setLessonNum(String password) {
	this.password=password;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type=type;
}
}
