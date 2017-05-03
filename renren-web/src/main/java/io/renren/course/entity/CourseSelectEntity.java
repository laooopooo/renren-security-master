package io.renren.course.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-05-03 14:22:27
 */
public class CourseSelectEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer courseSelectId;
	//
	private Date lastUpdate;
	//
	private Integer studentId;
	//
	private Integer courseId;

	/**
	 * 设置：
	 */
	public void setCourseSelectId(Integer courseSelectId) {
		this.courseSelectId = courseSelectId;
	}
	/**
	 * 获取：
	 */
	public Integer getCourseSelectId() {
		return courseSelectId;
	}
	/**
	 * 设置：
	 */
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	/**
	 * 获取：
	 */
	public Date getLastUpdate() {
		return lastUpdate;
	}
	/**
	 * 设置：
	 */
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	/**
	 * 获取：
	 */
	public Integer getStudentId() {
		return studentId;
	}
	/**
	 * 设置：
	 */
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	/**
	 * 获取：
	 */
	public Integer getCourseId() {
		return courseId;
	}
}
