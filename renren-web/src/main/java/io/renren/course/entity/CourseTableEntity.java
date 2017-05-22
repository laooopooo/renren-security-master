package io.renren.course.entity;

import java.io.Serializable;

public class CourseTableEntity  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6271270439012687813L;
	
	private String courseName;
	
	private Integer weekId;
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Integer getWeekId() {
		return weekId;
	}
	public void setWeekId(Integer weekId) {
		this.weekId = weekId;
	}
	
}
