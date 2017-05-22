package io.renren.course.entity;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;



/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-05-20 12:35:54
 */
public class ArrClassEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer arrClassId;
	//
	private Integer courseId;
	//
	private Integer classtimeId;
	
	private String classtimeName;
	
	@JSONField (format="HH:mm")
	private Date startTime;
	
	@JSONField (format="HH:mm")
	private Date endTime;
	//
	private Integer classroomId;
	//
	private Integer tenantId;
	//
	private Integer weekId;
	
	private Integer Mon;
	private Integer Tues;
	private Integer Wed;
	private Integer Thur;
	private Integer Fri;
	private Integer Sat;
	private Integer Sun;
	
	

	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getClasstimeName() {
		return classtimeName;
	}
	public void setClasstimeName(String classtimeName) {
		this.classtimeName = classtimeName;
	}
	public Integer getMon() {
		return Mon;
	}
	public void setMon(Integer mon) {
		Mon = mon;
	}
	public Integer getTues() {
		return Tues;
	}
	public void setTues(Integer tues) {
		Tues = tues;
	}
	public Integer getWed() {
		return Wed;
	}
	public void setWed(Integer wed) {
		Wed = wed;
	}
	public Integer getThur() {
		return Thur;
	}
	public void setThur(Integer thur) {
		Thur = thur;
	}
	public Integer getFri() {
		return Fri;
	}
	public void setFri(Integer fri) {
		Fri = fri;
	}
	public Integer getSat() {
		return Sat;
	}
	public void setSat(Integer sat) {
		Sat = sat;
	}
	public Integer getSun() {
		return Sun;
	}
	public void setSun(Integer sun) {
		Sun = sun;
	}
	/**
	 * 设置：
	 */
	public void setArrClassId(Integer arrClassId) {
		this.arrClassId = arrClassId;
	}
	/**
	 * 获取：
	 */
	public Integer getArrClassId() {
		return arrClassId;
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
	public void setClasstimeId(Integer classtimeId) {
		this.classtimeId = classtimeId;
	}
	/**
	 * 获取：
	 */
	public Integer getClasstimeId() {
		return classtimeId;
	}
	/**
	 * 设置：
	 */
	public void setClassroomId(Integer classroomId) {
		this.classroomId = classroomId;
	}
	/**
	 * 获取：
	 */
	public Integer getClassroomId() {
		return classroomId;
	}
	/**
	 * 设置：
	 */
	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}
	/**
	 * 获取：
	 */
	public Integer getTenantId() {
		return tenantId;
	}
	/**
	 * 设置：
	 */
	public void setWeekId(Integer weekId) {
		this.weekId = weekId;
	}
	/**
	 * 获取：
	 */
	public Integer getWeekId() {
		return weekId;
	}
}
