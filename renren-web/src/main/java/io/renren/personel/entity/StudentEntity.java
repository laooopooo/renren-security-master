package io.renren.personel.entity;

import java.io.Serializable;
import java.util.Date;



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
	private Date born;
	//性别
	private String sex;

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
