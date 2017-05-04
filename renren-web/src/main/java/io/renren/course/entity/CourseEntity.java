package io.renren.course.entity;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-05-03 14:22:27
 */
public class CourseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer courseId;
	//班级名称
	private String courseName;
	//备注
	private String remarks;
	//班级-类别，小班，晚辅,一对一
	private String numberType;
	//
	private Date lastUpdate;
	//
	private Integer teacherId;
	
	private String teacherName;
	
	//课程状态
	private Integer status;
	
	private String statusName;
	//课次
	private Integer courseTime;
	//开课日期
	@JSONField (format="yyyy-MM-dd")
	private Date startDate;
	//结束日期
	@JSONField (format="yyyy-MM-dd")
	private Date endDate;

	private Float originalPrice;
	
	private Float actualPrice;
	
	
	public Float getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(Float originalPrice) {
		this.originalPrice = originalPrice;
	}
	public Float getActualPrice() {
		return actualPrice;
	}
	public void setActualPrice(Float actualPrice) {
		this.actualPrice = actualPrice;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
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
	
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
	/**
	 * 设置：班级名称
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	/**
	 * 获取：班级名称
	 */
	public String getCourseName() {
		return courseName;
	}
	/**
	 * 设置：备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：备注
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * 设置：班级-类别，小班，晚辅,一对一
	 */
	public void setNumberType(String numberType) {
		this.numberType = numberType;
	}
	/**
	 * 获取：班级-类别，小班，晚辅,一对一
	 */
	public String getNumberType() {
		return numberType;
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
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	/**
	 * 获取：
	 */
	public Integer getTeacherId() {
		return teacherId;
	}
	/**
	 * 设置：课程状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：课程状态
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：课次
	 */
	public void setCourseTime(Integer courseTime) {
		this.courseTime = courseTime;
	}
	/**
	 * 获取：课次
	 */
	public Integer getCourseTime() {
		return courseTime;
	}
	/**
	 * 设置：开课日期
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * 获取：开课日期
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * 设置：结束日期
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * 获取：结束日期
	 */
	public Date getEndDate() {
		return endDate;
	}
}