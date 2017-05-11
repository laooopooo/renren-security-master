package io.renren.personel.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;



/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-04-11 13:30:01
 */
public class StudentEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer studentId;
	//
	private String name;
	//
	private String school;
	//
	private String parentTel;
	//
	private String address;
	//
	private Date lastUpdate;
	//
	@JSONField (format="yyyy-MM-dd")
	private Date born;
	//性别
	private String sex;
	
	private String parentName;
	
	private String remarks;
	
	private String kinskip;
	
	private String studyWay;
	
	/**
	 * 未结课班级个数
	 */
	private Integer courseNotEndNum;
	/**
	 * 已结课班级个数
	 */
	private Integer courseEndNum;

	
	public Integer getCourseNotEndNum() {
		return courseNotEndNum;
	}
	public void setCourseNotEndNum(Integer courseNotEndNum) {
		this.courseNotEndNum = courseNotEndNum;
	}
	public Integer getCourseEndNum() {
		return courseEndNum;
	}
	public void setCourseEndNum(Integer courseEndNum) {
		this.courseEndNum = courseEndNum;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getKinskip() {
		return kinskip;
	}
	public void setKinskip(String kinskip) {
		this.kinskip = kinskip;
	}
	public String getStudyWay() {
		return studyWay;
	}
	public void setStudyWay(String studyWay) {
		this.studyWay = studyWay;
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
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：
	 */
	public void setSchool(String school) {
		this.school = school;
	}
	/**
	 * 获取：
	 */
	public String getSchool() {
		return school;
	}
	/**
	 * 设置：
	 */
	public void setParentTel(String parentTel) {
		this.parentTel = parentTel;
	}
	/**
	 * 获取：
	 */
	public String getParentTel() {
		return parentTel;
	}
	/**
	 * 设置：
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：
	 */
	public String getAddress() {
		return address;
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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getLastUpdate() {
		return lastUpdate;
	}
	/**
	 * 设置：
	 */
	public void setBorn(Date born) {
		this.born = born;
	}
	/**
	 * 获取：
	 */
	public Date getBorn() {
		return born;
	}
	/**
	 * 设置：性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * 获取：性别
	 */
	public String getSex() {
		return sex;
	}
}
