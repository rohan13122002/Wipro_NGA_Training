package com.jsp.hibernate.Hibernate02;

@Entity
public class Course {
	
	@Id
	private int courseId;
	private String courseName;
	private int courseFee;
	
	@ManyToMany(mappedBy="courses")
	private List<Student> students;
	
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getCourseFee() {
		return courseFee;
	}
	public void setCourseFee(int courseFee) {
		this.courseFee = courseFee;
	}
	
	

}
