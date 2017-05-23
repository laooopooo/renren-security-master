package io.renren.course.entity;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class CourseTableFormatEntity  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6271270439012687813L;
	
	private Integer classtimeId;
	
	private String classtimeName;
	
	@JSONField (format="HH:mm")
	private Date startTime;
	
	@JSONField (format="HH:mm")
	private Date endTime;
	
	private CourseTableEntity mon;
	
	private CourseTableEntity tues;
	
	private CourseTableEntity wed;
	
	private CourseTableEntity thur;
	
	private CourseTableEntity fri;
	
	private CourseTableEntity sat;
	
	private CourseTableEntity sun;
	
	

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

	public Integer getClasstimeId() {
		return classtimeId;
	}

	public void setClasstimeId(Integer classtimeId) {
		this.classtimeId = classtimeId;
	}

	public String getClasstimeName() {
		return classtimeName;
	}

	public void setClasstimeName(String classtimeName) {
		this.classtimeName = classtimeName;
	}

	public CourseTableEntity getMon() {
		return mon;
	}

	public void setMon(CourseTableEntity mon) {
		this.mon = mon;
	}

	public CourseTableEntity getTues() {
		return tues;
	}

	public void setTues(CourseTableEntity tues) {
		this.tues = tues;
	}

	public CourseTableEntity getWed() {
		return wed;
	}

	public void setWed(CourseTableEntity wed) {
		this.wed = wed;
	}

	public CourseTableEntity getThur() {
		return thur;
	}

	public void setThur(CourseTableEntity thur) {
		this.thur = thur;
	}

	public CourseTableEntity getFri() {
		return fri;
	}

	public void setFri(CourseTableEntity fri) {
		this.fri = fri;
	}

	public CourseTableEntity getSat() {
		return sat;
	}

	public void setSat(CourseTableEntity sat) {
		this.sat = sat;
	}

	public CourseTableEntity getSun() {
		return sun;
	}

	public void setSun(CourseTableEntity sun) {
		this.sun = sun;
	}
	
}
