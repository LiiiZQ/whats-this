package modle;

public class courseInfo {
	public String teacherName="";
	public String courseName="";
	public String courseStyle="";
	public String courseDetail="";
	public String courseId="";
	public String homework="暂无";
	public courseInfo() {
		
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public courseInfo(String teacherName,String courseName,String courseStyle,String courseDetail,String courseId,String homework) {
		this.teacherName=teacherName;
		this.courseName=courseName;
		this.courseStyle=courseStyle;
		this.courseDetail=courseDetail;
		this.courseId=courseId;
		this.homework=homework;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName=teacherName;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName=courseName;
	}
	public String getCourseStyle() {
		return courseStyle;
	}
	public void setCourseStyle(String courseStyle) {
		this.courseStyle=courseStyle;
	}
	public String getCourseDetail() {
		return courseDetail;	
	}
	public void setCourseDetail(String courseDetail) {
		this.courseDetail=courseDetail;
	}
	public String getHomework() {
		return this.homework;	
	}
	public void setHomework(String homework) {
		this.homework=homework;
	}
}
