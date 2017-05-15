package io.renren.course.entity;

import java.io.Serializable;



/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-05-11 23:02:53
 */
public class CourseQuitEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer quitCourseId;
	//
	private Integer courseId;
	//
	private Integer studentId;
	//
	private Integer financeId;
	//退课原因
	private String quitReason;

	/**
	 * 设置：
	 */
	public void setQuitCourseId(Integer quitCourseId) {
		this.quitCourseId = quitCourseId;
	}
	/**
	 * 获取：
	 */
	public Integer getQuitCourseId() {
		return quitCourseId;
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
	public void setFinanceId(Integer financeId) {
		this.financeId = financeId;
	}
	/**
	 * 获取：
	 */
	public Integer getFinanceId() {
		return financeId;
	}
	/**
	 * 设置：退课原因
	 */
	public void setQuitReason(String quitReason) {
		this.quitReason = quitReason;
	}
	/**
	 * 获取：退课原因
	 */
	public String getQuitReason() {
		return quitReason;
	}
}
